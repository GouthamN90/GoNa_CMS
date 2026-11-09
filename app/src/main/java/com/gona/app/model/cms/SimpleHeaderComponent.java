package com.gona.app.model.cms;

import jakarta.persistence.DiscriminatorValue;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;

@Entity
@Table(name = "simpleheader_components")
@DiscriminatorValue("SIMPLEHEADER")
public class SimpleHeaderComponent extends CMSComponent {

	private String imageUrl;
    private String tagLine;
    
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "navbar_uid")
    private CMSNavigationBar navbar;
    

	public SimpleHeaderComponent() {
		super();
	}
	
    /**
     * Copy constructor: copies properties but reuses the navbar reference.
     */
    public SimpleHeaderComponent(SimpleHeaderComponent source, String newUid) {
        super();
        this.setUid(newUid);
        this.imageUrl = source.getImageUrl();
        this.tagLine = source.getTagLine();
        
        // Shallow reference: points to the SAME navbar record in the database
        this.navbar = source.getNavbar(); 
    }


	public String getImageUrl() {
		return imageUrl;
	}


	public void setImageUrl(String imageUrl) {
		this.imageUrl = imageUrl;
	}


	public String getTagLine() {
		return tagLine;
	}


	public void setTagLine(String tagLine) {
		this.tagLine = tagLine;
	}


	public CMSNavigationBar getNavbar() {
		return navbar;
	}


	public void setNavbar(CMSNavigationBar navbar) {
		this.navbar = navbar;
	}
	
}
