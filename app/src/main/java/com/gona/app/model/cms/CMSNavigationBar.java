package com.gona.app.model.cms;

import java.util.ArrayList;
import java.util.List;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;

@Entity
@Table(name = "cmsnavbars")
public class CMSNavigationBar {
	
	@Id
	@Column(unique = true, nullable = false)
	private String uid;
	
	private String name;
	
	@OneToMany(mappedBy = "navbar", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<CMSNavigationNode> navnodes = new ArrayList<>();
	
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

	public List<CMSNavigationNode> getNavnodes() {
		return navnodes;
	}

	public void setNavnodes(List<CMSNavigationNode> navnodes) {
		this.navnodes = navnodes;
	}

}
