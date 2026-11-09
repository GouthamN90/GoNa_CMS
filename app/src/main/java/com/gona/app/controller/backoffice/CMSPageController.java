package com.gona.app.controller.backoffice;

import java.util.List;

import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.gona.app.constant.GoNaConstants;
import com.gona.app.data.PageData;
import com.gona.app.data.PageablePageData;
import com.gona.app.data.SortBy;
import com.gona.app.data.SortOrder;
import com.gona.app.facade.CMSPageFacade;
import com.gona.app.form.PageForm;
import com.gona.app.model.ApprovalStatus;

import jakarta.validation.Valid;

@Controller
public class CMSPageController {
	
	@Autowired
	private CMSPageFacade cmsPageFacade;
	
	@GetMapping("/new_page")
	public String createNewPage(Model model) {
		model.addAttribute("pageForm", new PageForm());
		model.addAttribute("approvalStatuses", ApprovalStatus.values());
		return GoNaConstants.CMSPAGE_CREATE_EDIT_PAGE;
	}
	
	@GetMapping("/edit_page/{id}")
	public String editPage(@PathVariable Long id, Model model) {
		PageForm pageForm = cmsPageFacade.createPageFormFromPage(id);
		if (pageForm == null) {
			model.addAttribute("errorMessage", "Page not found");
			return GoNaConstants.ERROR_404_PAGE;
		}
		model.addAttribute("pageForm", pageForm);
		model.addAttribute("approvalStatuses", ApprovalStatus.values());
		return GoNaConstants.CMSPAGE_CREATE_EDIT_PAGE;
	}
	
	@PostMapping("/page/save")
	public String savePageForm(@Valid @ModelAttribute("pageForm") PageForm pageForm, 
	                              BindingResult result, 
	                              RedirectAttributes redirectAttributes) {
	    if (result.hasErrors()) {
	    	redirectAttributes.addFlashAttribute("pageForm", pageForm);
	    	redirectAttributes.addFlashAttribute("errorMessage", "Validation errors occurred");
	    	redirectAttributes.addFlashAttribute("approvalStatuses", ApprovalStatus.values());
	    	if (pageForm.getId() != null) {
	    		return "redirect:/edit_page/" + pageForm.getId();
	    	}
	        return "redirect:/new_page";
	    }
	    
	    long id = 0;
	    try {
	        id = cmsPageFacade.savePage(pageForm);
	        redirectAttributes.addFlashAttribute("successMessage", "Saved Page Successfully ");
	    } catch (Exception e) {
	    	redirectAttributes.addFlashAttribute("errorMessage", "Error saving Page: " + e.getMessage());
	    	redirectAttributes.addFlashAttribute("pageForm", pageForm);
	    	redirectAttributes.addFlashAttribute("approvalStatuses", ApprovalStatus.values());
	    	if (pageForm.getId() != null) {
	    		return "redirect:/edit_page/" + pageForm.getId();
	    	}
	        return "redirect:/new_page";
	    }
	    return "redirect:/bopage_detail/" + id;
	}
	
	@GetMapping("/bopage_list")
	public String viewPages(Model model, @RequestParam(defaultValue = "0") int pageNum,
            @RequestParam(defaultValue = "10") int pageSize,
            @RequestParam(defaultValue = "name") String sortBy,
            @RequestParam(defaultValue = "DSC") String sortDir, 
            @RequestParam(defaultValue = "") String searchQuery,
            @RequestParam(defaultValue = "") String status) {
		final PageablePageData pageablePageData = cmsPageFacade.getPageListingData(
				pageNum, pageSize, sortBy, sortDir, searchQuery, status);
		model.addAttribute("listPages", pageablePageData.getPageList());
		model.addAttribute("pagination", pageablePageData.getPageableData());
		model.addAttribute("sortBy", List.of(SortBy.name, SortBy.uid));
		model.addAttribute("sortOrder", SortOrder.values());
		model.addAttribute("approvalStatus", ApprovalStatus.values());
		model.addAttribute("statusFilter", StringUtils.isNotBlank(status) ? Enum.valueOf(ApprovalStatus.class, status) : null);
		model.addAttribute("searchQuery", searchQuery);
        return GoNaConstants.CMSPAGE_LISTING_PAGE;
	}
	
	@GetMapping("/bopage_detail/{id}")
	public String viewPage(Model model, @PathVariable Long id) {
		PageData pageData = cmsPageFacade.getBOPageData(id);
		if (pageData == null) {
			model.addAttribute("errorMessage", "Page not found");
			return GoNaConstants.ERROR_404_PAGE;
		}
		model.addAttribute("pageData", pageData);
        return GoNaConstants.BOPAGEDETAIL_PAGE;
	}

	@GetMapping("/delete_page/{id}")
	public String deletePage(@PathVariable Long id, RedirectAttributes redirectAttributes) {
		try {
			cmsPageFacade.deletePage(id);
		} catch (Exception e) {
			redirectAttributes.addFlashAttribute("errorMessage", "Error deleting page: " + e.getMessage());
		}
		return "redirect:/bopage_list";
	}
	
	@GetMapping("/add_component_to_page/{pageId}/{componentId}")
	public String addComponentToPage(@PathVariable Long pageId, @PathVariable Long componentId, RedirectAttributes redirectAttributes) {
		try {
			cmsPageFacade.addComponentToPage(pageId, componentId);
		} catch (Exception e) {
			redirectAttributes.addFlashAttribute("errorMessage", "Error adding component to page: " + e.getMessage());
		}
		return "redirect:/bopage_detail/" + pageId;
	}
	
	@GetMapping("/remove_component_from_page/{pageId}/{componentId}")
	public String removeComponentToPage(@PathVariable Long pageId, @PathVariable Long componentId, RedirectAttributes redirectAttributes) {
		try {
			cmsPageFacade.removeComponentFromPage(pageId, componentId);
		} catch (Exception e) {
			redirectAttributes.addFlashAttribute("errorMessage", "Error adding component to page: " + e.getMessage());
		}
		return "redirect:/bopage_detail/" + pageId;
	}
	
	@PatchMapping("/reorder_components/{pageId}")
    public ResponseEntity<Void> reorderSingle(
            @PathVariable Long pageId,
            @RequestParam int from,
            @RequestParam int to) {

		cmsPageFacade.reorderPageComponent(pageId, (from-1), (to-1)); // Adjusting for 0-based index
        return ResponseEntity.ok().build();
    }

}
