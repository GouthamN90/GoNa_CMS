package com.gona.app.facade;

import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.Base64;
import java.util.List;
import java.util.Optional;

import org.apache.commons.collections4.CollectionUtils;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Component;

import com.gona.app.constant.GoNaConstants;
import com.gona.app.data.ComponentData;
import com.gona.app.populator.DelegateComponentFormPopulator;
import com.gona.app.data.EditComponentData;
import com.gona.app.data.PageData;
import com.gona.app.data.PageableComponentData;
import com.gona.app.data.PageableData;
import com.gona.app.data.SortBy;
import com.gona.app.data.SortOrder;
import com.gona.app.form.CloneComponentForm;
import com.gona.app.form.ComponentForm;
import com.gona.app.model.ApprovalStatus;
import com.gona.app.model.cms.CMSComponent;
import com.gona.app.model.cms.CMSPage;
import com.gona.app.populator.CMSComponentPopulator;
import com.gona.app.services.CMSComponentService;

import tools.jackson.databind.ObjectMapper;

@Component
public class CMSComponentFacade {
	
	@Autowired
	private CMSComponentService cmsComponentService;
	
	@Autowired
	private CMSComponentPopulator cmsComponentPopulator;
	
	@Autowired
	private DelegateComponentFormPopulator delegateComponentFormPopulator;
	
	@Autowired
	private ObjectMapper objectMapper;
	
	public PageableComponentData getComponentListingData(int pageNum, int pageSize, String sortBy, String sortDir, 
			String searchQuery, String selectedTemplate, boolean defaultTemplate) {
		Sort sort = sortDir.equalsIgnoreCase(Sort.Direction.ASC.name()) 
                ? Sort.by(sortBy).ascending() 
                : Sort.by(sortBy).descending();
    
		final Pageable pageable = PageRequest.of(pageNum, pageSize, sort);
		Page<CMSComponent> componentPageable = null;
		if (StringUtils.isNotBlank(searchQuery) || StringUtils.isNotBlank(selectedTemplate)) {
			componentPageable = cmsComponentService.getAllComponents(pageable, searchQuery, selectedTemplate, defaultTemplate);
		} else {
			componentPageable = cmsComponentService.getAllComponents(pageable, defaultTemplate);
		}
		final List<ComponentData> componentList = cmsComponentPopulator.populate(componentPageable.getContent());
		final PageableData pageableData = new PageableData(pageNum, componentPageable.getTotalPages(), componentPageable.getTotalElements(), pageSize, SortBy.valueOf(sortBy), SortOrder.valueOf(sortDir));
		return new PageableComponentData(componentList, pageableData);
	}

	public Long cloneComponent(final CloneComponentForm form) {
		return cmsComponentService.cloneComponent(form);
	}
	
	public EditComponentData getComponentForEdit(Long id) {
		final Optional<CMSComponent> componentOpt = cmsComponentService.getByIdAndDefaultTemplate(id, false);
		if (componentOpt.isPresent()) {
			final CMSComponent component = componentOpt.get();
			
			final ComponentForm componentForm = delegateComponentFormPopulator.getPopulator(component.getTemplate()).populate(component);
			
			final EditComponentData editComponentData = new EditComponentData();
			populateEditComponentData(editComponentData, componentForm, component);
			return editComponentData;
		}
		return null; // Placeholder return statement
	}

	private void populateEditComponentData(EditComponentData editComponentData, ComponentForm componentForm,
			CMSComponent component) {
		editComponentData.setName(component.getName());
		editComponentData.setUid(component.getUid());
		editComponentData.setTemplate(component.getTemplate());
		editComponentData.setApprovalStatus(component.getApprovalStatus());
		editComponentData.setComponentForm(componentForm);
		
		if (CollectionUtils.isNotEmpty(component.getPages())) {
			List<PageData> pageDataList = new ArrayList<>();
			for (CMSPage page : component.getPages()) {
				final PageData pageData = new PageData();
				pageData.setId(page.getId());
				pageData.setName(page.getName());
				pageDataList.add(pageData);
			}
			editComponentData.setPages(pageDataList);
		}
		
	}

	public boolean saveComponent(ComponentForm form) {
		Optional<CMSComponent> componentOpt = cmsComponentService.getByIdAndDefaultTemplate(form.getId(), false);
		if (componentOpt.isPresent()) {
			CMSComponent component = componentOpt.get();
			delegateComponentFormPopulator.getPopulator(component.getTemplate()).populate(form, component);
			savePreviewImage(form, component);
			cmsComponentService.saveComponent(component);
			
			return true;
		}
		return false;
	}

	private void savePreviewImage(ComponentForm form, CMSComponent component) {
		if (StringUtils.isNotBlank(form.getScreenshotString())) {
			String base64Image = form.getScreenshotString().split(",")[1];
			if (StringUtils.isNotBlank(base64Image)) {
				byte[] imageBytes = Base64.getDecoder().decode(base64Image);

	            // Define target storage path
	            String fileName = component.getUid() + ".png";
	            Path uploadPath = Paths.get(GoNaConstants.IMAGE_UPLOAD_FOLDER);
	            
	            try {
		            if (!Files.exists(uploadPath)) {
		                Files.createDirectories(uploadPath);
		            }
		            
		            Path filePath = uploadPath.resolve(fileName);
		            Files.write(filePath, imageBytes);
		            component.setPreviewImgUrl(GoNaConstants.IMAGE_UPLOAD_URL + fileName);
	            } catch (Exception e) {
	            	//TODO Logger
	            	e.printStackTrace();
	            }
			}
		}
		
	}
	
	public String getComponentData(final String uid) {
		final CMSComponent component = cmsComponentService.getComponentForUidAndApprovalStatus(uid, false, ApprovalStatus.Show);
		return convertComponentToJsonData(component);
	}
	
	private String convertComponentToJsonData(final CMSComponent component) {
		if (component != null) {
			try {
				return objectMapper.writeValueAsString(component);
			} catch (Exception e) {
				//TODO add proper logging
				e.printStackTrace();
			}
		}
		return StringUtils.EMPTY;
	}
	
}
