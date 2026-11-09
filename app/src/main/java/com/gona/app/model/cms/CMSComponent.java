package com.gona.app.model.cms;

import java.util.ArrayList;
import java.util.List;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.gona.app.model.ApprovalStatus;

import jakarta.persistence.Column;
import jakarta.persistence.DiscriminatorColumn;
import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Inheritance;
import jakarta.persistence.InheritanceType;
import jakarta.persistence.ManyToMany;
import jakarta.persistence.Table;

@Entity
@Table(name = "cmscomponents")
@Inheritance(strategy = InheritanceType.JOINED)
@DiscriminatorColumn(name = "component_type")
public abstract class CMSComponent {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    
    @Column(unique = true, nullable = false)
    private String uid;

    private String name;

    private String template;
    
    @JsonIgnore
    private String previewImgUrl;
    
    @JsonIgnore
    private boolean defaultTemplate;

    @Enumerated(EnumType.STRING)
    private ApprovalStatus approvalStatus;

    @JsonIgnore
    @ManyToMany(mappedBy = "components")
    private List<CMSPage> pages = new ArrayList<>();

    // Default Constructor
    public CMSComponent() {
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

    public List<CMSPage> getPages() {
        return pages;
    }

    public void setPages(List<CMSPage> pages) {
        this.pages = pages;
    }

	public boolean isDefaultTemplate() {
		return defaultTemplate;
	}

	public void setDefaultTemplate(boolean defaultTemplate) {
		this.defaultTemplate = defaultTemplate;
	}

	public String getPreviewImgUrl() {
		return previewImgUrl;
	}

	public void setPreviewImgUrl(String previewImgUrl) {
		this.previewImgUrl = previewImgUrl;
	}
	
}
