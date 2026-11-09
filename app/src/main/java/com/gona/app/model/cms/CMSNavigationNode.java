package com.gona.app.model.cms;

import java.util.ArrayList;
import java.util.List;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.gona.app.model.ApprovalStatus;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.FetchType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;

@Entity
@Table(name = "cmsnavnodes")
public class CMSNavigationNode {
	
	@Id
	@Column(unique = true, nullable = false)
	private String uid;
	
	@Column(nullable = false)
	private String name;
	
	@Column(nullable = false)
	private String link;
	
	@Enumerated(EnumType.STRING)
    private ApprovalStatus approvalStatus;
	
	@ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "navbar_uid")
	@JsonIgnore
	private CMSNavigationBar navbar;
	
	@ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "parent_navnode_uid")
	@JsonIgnore
    private CMSNavigationNode parentNavNode;

    @OneToMany(mappedBy = "parentNavNode", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<CMSNavigationNode> childNavNodes = new ArrayList<>();

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

	public String getLink() {
		return link;
	}

	public void setLink(String link) {
		this.link = link;
	}

	public CMSNavigationBar getNavbar() {
		return navbar;
	}

	public void setNavbar(CMSNavigationBar navbar) {
		this.navbar = navbar;
	}

	public CMSNavigationNode getParentNavNode() {
		return parentNavNode;
	}

	public void setParentNavNode(CMSNavigationNode parentNavNode) {
		this.parentNavNode = parentNavNode;
	}

	public List<CMSNavigationNode> getChildNavNodes() {
		return childNavNodes;
	}

	public void setChildNavNodes(List<CMSNavigationNode> childNavNodes) {
		this.childNavNodes = childNavNodes;
	}
    
	public void addChildNavNode(CMSNavigationNode child) {
		child.setParentNavNode(this);
		this.childNavNodes.add(child);
	}
	
	public void removeChildNavNode(CMSNavigationNode child) {
		child.setParentNavNode(null);
		this.childNavNodes.remove(child);
	}
}
