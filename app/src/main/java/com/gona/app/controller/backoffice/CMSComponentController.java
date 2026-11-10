package com.gona.app.controller.backoffice;

import java.util.List;

import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.gona.app.facade.CMSComponentFacade;
import com.gona.app.constant.GoNaConstants;
import com.gona.app.data.ComponentData;
import com.gona.app.data.ComponentTemplate;
import com.gona.app.data.EditComponentData;
import com.gona.app.data.PageableComponentData;
import com.gona.app.data.SortBy;
import com.gona.app.data.SortOrder;
import com.gona.app.form.BannerComponentForm;
import com.gona.app.form.CloneComponentForm;
import com.gona.app.form.ComponentForm;
import com.gona.app.model.ApprovalStatus;

import jakarta.validation.Valid;

@Controller
public class CMSComponentController {
	
	@Autowired
	private CMSComponentFacade cmsComponentFacade;
	
	@GetMapping("/select_default_component")
	public String viewDefaultComponents(Model model, @RequestParam(defaultValue = "0") int pageNum,
            @RequestParam(defaultValue = "9") int pageSize,
            @RequestParam(defaultValue = "name") String sortBy,
            @RequestParam(defaultValue = "DSC") String sortDir, 
            @RequestParam(defaultValue = "") String searchQuery,
            @RequestParam(defaultValue = "") String selectedTemplate) {
		final PageableComponentData pageableComponentData = cmsComponentFacade.getComponentListingData(
				pageNum, pageSize, sortBy, sortDir, searchQuery, selectedTemplate, true);
		model.addAttribute("defaultTemplateListFlag",true);
		populateModelForComponentsListing(model, pageableComponentData, searchQuery, selectedTemplate);
        return GoNaConstants.CMSCOMPONENT_LISTING_PAGE;
	}
	
	private void populateModelForComponentsListing(Model model, PageableComponentData pageableComponentData, String searchQuery, String selectedTemplate) {
		model.addAttribute("listComponents", pageableComponentData.getComponentList());
		model.addAttribute("pagination", pageableComponentData.getPageableData());
		model.addAttribute("sortBy", List.of(SortBy.name, SortBy.uid));
		model.addAttribute("sortOrder", SortOrder.values());
		model.addAttribute("searchQuery", searchQuery);
		model.addAttribute("templates", ComponentTemplate.values());
		model.addAttribute("selectedTemplate", StringUtils.isNotBlank(selectedTemplate) ? Enum.valueOf(ComponentTemplate.class, selectedTemplate) : null);
	}
	
	@GetMapping("/components_listing")
	public String viewComponentsListing(Model model, @RequestParam(defaultValue = "0") int pageNum,
            @RequestParam(defaultValue = "9") int pageSize,
            @RequestParam(defaultValue = "name") String sortBy,
            @RequestParam(defaultValue = "DSC") String sortDir, 
            @RequestParam(defaultValue = "") String searchQuery,
            @RequestParam(defaultValue = "") String selectedTemplate) {
		final PageableComponentData pageableComponentData = cmsComponentFacade.getComponentListingData(
				pageNum, pageSize, sortBy, sortDir, searchQuery, selectedTemplate, false);
		model.addAttribute("defaultTemplateListFlag",false);
		populateModelForComponentsListing(model, pageableComponentData, searchQuery, selectedTemplate);
        return GoNaConstants.CMSCOMPONENT_LISTING_PAGE;
	}
	
	@GetMapping("/api/components")
	@ResponseBody
	List<ComponentData> getComponents(@RequestParam(defaultValue = "") String searchQuery) {
		final PageableComponentData pageableComponentData = cmsComponentFacade.getComponentListingData(
				0, 10, "name", "ASC", searchQuery, StringUtils.EMPTY, false);
		return pageableComponentData.getComponentList();
	}
	
	
	@PostMapping("/clone_component")
	public String cloneDefaultComponent(
            @Valid @ModelAttribute("cloneComponentForm") CloneComponentForm form,
            BindingResult bindingResult,
            RedirectAttributes redirectAttributes) {
		
		// 1. Handle validation errors (e.g., missing required fields)
        if (bindingResult.hasErrors()) {
            redirectAttributes.addFlashAttribute("errorMessage", "Failed to create component.");
            return "redirect:/select_default_component";
        }
        
        try {
        	Long newCompId = cmsComponentFacade.cloneComponent(form);
        	if (newCompId == null) {
        		redirectAttributes.addFlashAttribute("errorMessage", "Failed to create component");
				return "redirect:/select_default_component";
        	}
            redirectAttributes.addFlashAttribute("successMessage", "Created " + form.getNewCompName() + "!");
            return "redirect:/update_component/" + newCompId;
        } catch (Exception e) {
            redirectAttributes.addFlashAttribute("errorMessage", "An error occurred while saving the component: " + e.getMessage());
            return "redirect:/select_default_component";
        }
		
	}
	
	@GetMapping("/update_component/{id}")
	public String updateComponentPage(@PathVariable Long id, Model model) {
		EditComponentData editComponentData = cmsComponentFacade.getComponentForEdit(id);
		if (editComponentData == null) {
			model.addAttribute("errorMessage", "Component not found.");
			return "redirect:/list_component";
		}
		model.addAttribute("editComponentData", editComponentData);
		model.addAttribute("componentForm", editComponentData.getComponentForm());
		model.addAttribute("componentFormPage", GoNaConstants.COMPONENT_TEMPLATE_FORM_MAP.get(editComponentData.getTemplate()));
		model.addAttribute("approvalStatuses", ApprovalStatus.values());
		return GoNaConstants.CMSCOMPONENT_FORM_PAGE;
	}
	
	@ModelAttribute("componentForm")
    public ComponentForm createComponentFromTemplate(@RequestParam(value = "template", required = false) String template) {
        if ("BannerComponent".equalsIgnoreCase(template)) {
            return new BannerComponentForm();
        }
        return null;
    }
	
	@PostMapping("/save_component")
	public String saveComponent(@ModelAttribute("componentForm") ComponentForm componentForm, BindingResult bindingResult, 
			RedirectAttributes redirectAttributes) {
		final boolean success = cmsComponentFacade.saveComponent(componentForm);
		if (success) {
			redirectAttributes.addFlashAttribute("successMessage", "Component saved successfully.");
		} else {
			redirectAttributes.addFlashAttribute("errorMessage", "Failed to save component.");
		}
		return "redirect:/update_component/" + componentForm.getId();
		
	}

}
