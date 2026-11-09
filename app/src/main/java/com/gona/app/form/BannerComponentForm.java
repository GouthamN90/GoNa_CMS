package com.gona.app.form;

public class BannerComponentForm extends ComponentForm {
	
	private String imageUrl;
    private String ctaLink;
    private String bannerText;
    
	public String getImageUrl() {
		return imageUrl;
	}
	public void setImageUrl(String imageUrl) {
		this.imageUrl = imageUrl;
	}
	public String getCtaLink() {
		return ctaLink;
	}
	public void setCtaLink(String ctaLink) {
		this.ctaLink = ctaLink;
	}
	public String getBannerText() {
		return bannerText;
	}
	public void setBannerText(String bannerText) {
		this.bannerText = bannerText;
	}
    
}
