package com.gona.app.populator;

import org.springframework.stereotype.Component;

import com.gona.app.form.PageForm;
import com.gona.app.model.cms.CMSPage;

@Component
public class PageFormPopulator {

	public void populate(PageForm pageForm, CMSPage page) {
		page.setId(pageForm.getId());
		page.setUid(pageForm.getUid());
		page.setName(pageForm.getName());
		page.setTitle(pageForm.getTitle());
		page.setMetadata(pageForm.getMetadata());
		page.setApprovalStatus(pageForm.getApprovalStatus());
	}
	
	public void populate(CMSPage page, PageForm pageForm) {
		pageForm.setId(page.getId());
		pageForm.setUid(page.getUid());
		pageForm.setName(page.getName());
		pageForm.setTitle(page.getTitle());
		pageForm.setMetadata(page.getMetadata());
		pageForm.setApprovalStatus(page.getApprovalStatus());
	}
}
