package com.gona.app.data;

import java.util.List;

import com.gona.app.form.ComponentForm;
import com.gona.app.model.ApprovalStatus;
import com.gona.app.model.CMSVersion;

public class EditComponentData {
	
	private String name;
	
	private Long id;
	
	private String uid;

    private String template;
    
    private ApprovalStatus approvalStatus;
    
    private List<PageData> pages;
    
    private CMSVersion version;
    
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

	public CMSVersion getVersion() {
		return version;
	}

	public void setVersion(CMSVersion version) {
		this.version = version;
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}
	
}