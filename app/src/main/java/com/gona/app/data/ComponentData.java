package com.gona.app.data;

import java.util.List;

import com.gona.app.model.ApprovalStatus;
import com.gona.app.model.CMSVersion;

public class ComponentData {
	
	private Long id;
	
	private String name;
	
	private String uid;
	
	private CMSVersion version;

    private String template;
    
    private String previewImgUrl;
    
    private boolean defaultTemplate;
    
    private ApprovalStatus approvalStatus;
    
    private List<PageData> pages;

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

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getPreviewImgUrl() {
		return previewImgUrl;
	}

	public void setPreviewImgUrl(String previewImgUrl) {
		this.previewImgUrl = previewImgUrl;
	}

	public boolean isDefaultTemplate() {
		return defaultTemplate;
	}

	public void setDefaultTemplate(boolean defaultTemplate) {
		this.defaultTemplate = defaultTemplate;
	}

	public ApprovalStatus getApprovalStatus() {
		return approvalStatus;
	}

	public void setApprovalStatus(ApprovalStatus approvalStatus) {
		this.approvalStatus = approvalStatus;
	}

	public List<PageData> getPages() {
		return pages;
	}

	public void setPages(List<PageData> pages) {
		this.pages = pages;
	}

	public CMSVersion getVersion() {
		return version;
	}

	public void setVersion(CMSVersion version) {
		this.version = version;
	}
	
}
