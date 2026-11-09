package com.gona.app.form;

import com.gona.app.model.ApprovalStatus;

public class ComponentForm {
	
	private Long id;
    
    private String uid;

    private String name;

    private String template;
    
    private String previewImgUrl;
    
    private boolean defaultTemplate;

    private ApprovalStatus approvalStatus;
    
    private String screenshotString;

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getUid() {
		return uid;
	}

	public void setUid(String uid) {
		this.uid = uid;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getTemplate() {
		return template;
	}

	public void setTemplate(String template) {
		this.template = template;
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

	public String getScreenshotString() {
		return screenshotString;
	}

	public void setScreenshotString(String screenshotString) {
		this.screenshotString = screenshotString;
	}
    
}
