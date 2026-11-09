package com.gona.app.form;

import jakarta.validation.constraints.NotNull;

public class CloneComponentForm {
	
	@NotNull(message = "Default Component ID cannot be null")
	private Long defaultCompId;
	@NotNull(message = "Component UID cannot be null")
	private String newCompUid;
	@NotNull(message = "Component Name cannot be null")
	private String newCompName;
	
	public Long getDefaultCompId() {
		return defaultCompId;
	}
	public void setDefaultCompId(Long defaultCompId) {
		this.defaultCompId = defaultCompId;
	}
	public String getNewCompUid() {
		return newCompUid;
	}
	public void setNewCompUid(String newCompUid) {
		this.newCompUid = newCompUid;
	}
	public String getNewCompName() {
		return newCompName;
	}
	public void setNewCompName(String newCompName) {
		this.newCompName = newCompName;
	}
}
