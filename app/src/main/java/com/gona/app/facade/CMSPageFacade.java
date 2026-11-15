package com.gona.app.facade;

import java.util.List;
import java.util.Optional;

import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Component;

import com.gona.app.data.PageData;
import com.gona.app.data.PageableData;
import com.gona.app.data.PageablePageData;
import com.gona.app.data.SortBy;
import com.gona.app.data.SortOrder;
import com.gona.app.form.PageForm;
import com.gona.app.model.ApprovalStatus;
import com.gona.app.model.CMSVersion;
import com.gona.app.model.cms.CMSComponent;
import com.gona.app.model.cms.CMSPage;
import com.gona.app.populator.PageFormPopulator;
import com.gona.app.populator.CMSPagePopulator;
import com.gona.app.services.CMSComponentService;
import com.gona.app.services.CMSPageService;

@Component
public class CMSPageFacade {
	
	@Autowired
	private CMSPageService cmsPageService;
	
	@Autowired
	private CMSComponentService cmsComponentService;
	
	@Autowired
	private CMSPagePopulator cmsPagePopulator;
	
	@Autowired
	private PageFormPopulator pageFormPopulator;
	
	public PageablePageData getPageListingData(int pageNum, int pageSize, String sortBy, String sortDir, 
			String searchQuery, String status, String version) {
		Sort sort = sortDir.equalsIgnoreCase(Sort.Direction.ASC.name()) 
                ? Sort.by(sortBy).ascending() 
                : Sort.by(sortBy).descending();
    
		final Pageable pageable = PageRequest.of(pageNum, pageSize, sort);
		Page<CMSPage> pagePageable = null;
		if (StringUtils.isNotBlank(searchQuery) || StringUtils.isNotBlank(status) || StringUtils.isNotBlank(version)) {
			pagePageable = cmsPageService.getPages(pageable, searchQuery, status, version);
		} else {
			pagePageable = cmsPageService.getPages(pageable);
		}
		final List<PageData> pageList = cmsPagePopulator.populate(pagePageable.getContent());
		final PageableData pageableData = new PageableData(pageNum, pagePageable.getTotalPages(), pagePageable.getTotalElements(), pageSize, SortBy.valueOf(sortBy), SortOrder.valueOf(sortDir));
		return new PageablePageData(pageList, pageableData);
	}


	public PageForm createPageFormFromPage(Long id) {
		Optional<CMSPage> pageOpt = cmsPageService.getPageForId(id);
		if (pageOpt.isPresent()) {
			PageForm pageForm = new PageForm();
			pageFormPopulator.populate(pageOpt.get(), pageForm);
			return pageForm;
		}
		return null;
	}


	public long savePage(final PageForm pageForm) {
		CMSPage page = null;
		if (pageForm.getId() != null) {
			Optional<CMSPage> pageOpt = cmsPageService.getPageForId(pageForm.getId());
			if (pageOpt.isPresent()) {
				page = pageOpt.get();
			}
		} else {
			page = new CMSPage();
		}
		
		if (page == null) {
			throw new IllegalArgumentException("Product not found for id: " + pageForm.getId());
		}
		
		pageFormPopulator.populate(pageForm, page);
		page = cmsPageService.savePage(page);
		return page.getId();
	}
	
	public PageData getBOPageData(final Long id) {
		Optional<CMSPage> pageOpt = cmsPageService.getPageForId(id);
		if (pageOpt.isPresent()) {
			return cmsPagePopulator.populate(pageOpt.get());
		}
		return null;
	}


	public void addComponentToPage(Long pageId, Long componentId) {
		Optional<CMSPage> pageOpt = cmsPageService.getPageForId(pageId);
		Optional<CMSComponent> componentOpt = cmsComponentService.getByIdAndDefaultTemplate(componentId, false);
		if (pageOpt.isPresent() && componentOpt.isPresent()) {
			CMSPage page = pageOpt.get();
			CMSComponent component = componentOpt.get();
			page.addComponent(component);
			cmsPageService.savePage(page);
		} else {
			throw new IllegalArgumentException("Page/Component not found for id: " + pageId + "/" + componentId);
		}
		
	}
	
	public void reorderPageComponent(Long pageId, int fromIndex, int toIndex) {
		cmsPageService.reorderPageComponent(pageId, fromIndex, toIndex);
	}


	public void removeComponentFromPage(Long pageId, Long componentId) {
		Optional<CMSPage> pageOpt = cmsPageService.getPageForId(pageId);
		Optional<CMSComponent> componentOpt = cmsComponentService.getByIdAndDefaultTemplate(componentId, false);
		if (pageOpt.isPresent() && componentOpt.isPresent()) {
			CMSPage page = pageOpt.get();
			CMSComponent component = componentOpt.get();
			if (page.getComponents().contains(component)) {
				page.removeComponent(component);
			} else {
				throw new IllegalArgumentException("Component with id: " + componentId + " is not associated with Page with id: " + pageId);
			}
			cmsPageService.savePage(page);
		} else {
			throw new IllegalArgumentException("Page/Component not found for id: " + pageId + "/" + componentId);
		}
		
	}


	public void deletePage(Long id) {
		cmsPageService.deletePage(id);
	}
	
	public PageData getPageData(final String uid) {
		final Optional<CMSPage> pageOpt = cmsPageService.getPageForUidVersionAndApprovalStatus(uid, CMSVersion.Online ,ApprovalStatus.Show);
		if (pageOpt.isPresent()) {
			return cmsPagePopulator.populate(pageOpt.get());
		}
		return null;
	}
	
	public PageData getPageDataPreviewMode(final String uid) {
		final Optional<CMSPage> pageOpt = cmsPageService.getPageForUidAndVersion(uid, CMSVersion.Draft);
		if (pageOpt.isPresent()) {
			return cmsPagePopulator.populate(pageOpt.get());
		}
		return null;
	}


	public Long syncPage(String uid) {
		return cmsPageService.syncPage(uid);
	}

}
