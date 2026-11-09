package com.gona.app.data;

import java.util.List;

import com.gona.app.model.ApprovalStatus;

public class PageData {
	
	private Long id;
	
	private String uid;
	
	private String name;

    private String title;

    private String metadata;

	private List<ComponentData> components;
	
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

	public List<ComponentData> getComponents() {
		return components;
	}

	public void setComponents(List<ComponentData> components) {
		this.components = components;
	}

	public ApprovalStatus getApprovalStatus() {
		return approvalStatus;
	}

	public void setApprovalStatus(ApprovalStatus approvalStatus) {
		this.approvalStatus = approvalStatus;
	}
	
}
