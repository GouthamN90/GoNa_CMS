package com.gona.app.model.cms;

import java.util.ArrayList;
import java.util.List;

import com.gona.app.model.ApprovalStatus;
import com.gona.app.model.CMSVersion;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinTable;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToMany;
import jakarta.persistence.OrderColumn;
import jakarta.persistence.Table;
import jakarta.persistence.UniqueConstraint;

@Entity
@Table(name = "cmspages", uniqueConstraints = @UniqueConstraint(columnNames = { "uid", "version" }))
public class CMSPage {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    
    @Column(nullable = false)
    private String uid;
    
    @Column(nullable = false)
    @Enumerated(EnumType.STRING)
    private CMSVersion version;

    private String name;

    private String title;

    @Column(columnDefinition = "TEXT")
    private String metadata;

    @Enumerated(EnumType.STRING)
    private ApprovalStatus approvalStatus;

    @ManyToMany
    @JoinTable(
        name = "cmspage_cmscomponent",
        joinColumns = @JoinColumn(name = "page_id"),
        inverseJoinColumns = @JoinColumn(name = "component_id")
    )
    @OrderColumn(name = "component_order")
    private List<CMSComponent> components = new ArrayList<>();

    // Default Constructor
    public CMSPage() {
    }

    // Getters and Setters
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

    public List<CMSComponent> getComponents() {
        return components;
    }

    public void setComponents(List<CMSComponent> components) {
        this.components = components;
    }
    
    public CMSVersion getVersion() {
		return version;
	}

	public void setVersion(CMSVersion version) {
		this.version = version;
	}

	/**
     * Helper method to add a component and sync both sides of the ManyToMany relationship.
     */
    public void addComponent(CMSComponent component) {
        if (component != null && !this.components.contains(component)) {
            this.components.add(component);
            component.getPages().add(this);
        }
    }

    /**
     * Helper method to remove a component and sync both sides of the ManyToMany relationship.
     */
    public void removeComponent(CMSComponent component) {
        if (component != null && this.components.contains(component)) {
            this.components.remove(component);
            component.getPages().remove(this);
        }
    }
}
