package com.gona.app.data;

import java.util.List;

import com.gona.app.form.ComponentForm;
import com.gona.app.model.ApprovalStatus;

public class EditComponentData {
	
	private String name;
	
	private String uid;

    private String template;
    
    private ApprovalStatus approvalStatus;
    
    private List<PageData> pages;
    
    private ComponentForm componentForm;
    
    public List<PageData> getPages() {
		return pages;
	}

	public void setPages(List<PageData> pages) {
		this.pages = pages;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getUid() {
		return uid;
	}

	public void setUid(String uid) {
		this.uid = uid;
	}

	public String getTemplate() {
		return template;
	}

	public void setTemplate(String template) {
		this.template = template;
	}

	public ApprovalStatus getApprovalStatus() {
		return approvalStatus;
	}

	public void setApprovalStatus(ApprovalStatus approvalStatus) {
		this.approvalStatus = approvalStatus;
	}

	public ComponentForm getComponentForm() {
		return componentForm;
	}

	public void setComponentForm(ComponentForm componentForm) {
		this.componentForm = componentForm;
	}
	
}
