package com.gona.app.form;

import com.gona.app.model.ApprovalStatus;

import jakarta.validation.constraints.NotBlank;

public class PageForm {
	
	private Long id;
	
	@NotBlank(message = "UID is required")
	private String uid;

	@NotBlank(message = "name is required")
    private String name;

    private String title;

    private String metadata;

    private ApprovalStatus approvalStatus;

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

	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public String getMetadata() {
		return metadata;
	}

	public void setMetadata(String metadata) {
		this.metadata = metadata;
	}

	public ApprovalStatus getApprovalStatus() {
		return approvalStatus;
	}

	public void setApprovalStatus(ApprovalStatus approvalStatus) {
		this.approvalStatus = approvalStatus;
	}

}
