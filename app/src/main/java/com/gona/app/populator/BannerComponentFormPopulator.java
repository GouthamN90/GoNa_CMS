package com.gona.app.populator;

import org.springframework.stereotype.Component;

import com.gona.app.form.BannerComponentForm;
import com.gona.app.form.ComponentForm;
import com.gona.app.model.cms.BannerComponent;
import com.gona.app.model.cms.CMSComponent;

@Component
public class BannerComponentFormPopulator implements ComponentFormPopulator<BannerComponent, BannerComponentForm> {
	
	@Override
	public BannerComponentForm populate(final CMSComponent component) {
		final BannerComponentForm bannerComponentForm = new BannerComponentForm();
		final BannerComponent bannerComponent = (BannerComponent) component;
		populate(bannerComponent, bannerComponentForm);
		bannerComponentForm.setImageUrl(bannerComponent.getImageUrl());
		bannerComponentForm.setCtaLink(bannerComponent.getCtaLink());
		bannerComponentForm.setBannerText(bannerComponent.getBannerText());
		return bannerComponentForm;
	}
	
	@Override
	public void populate(final ComponentForm componentForm, final CMSComponent component) {
		final BannerComponent bannerComponent = (BannerComponent) component;
		final BannerComponentForm bannerComponentForm = (BannerComponentForm) componentForm;
		bannerComponent.setImageUrl(bannerComponentForm.getImageUrl());
		bannerComponent.setCtaLink(bannerComponentForm.getCtaLink());
		bannerComponent.setBannerText(bannerComponentForm.getBannerText());
		bannerComponent.setApprovalStatus(bannerComponentForm.getApprovalStatus());
	}

}
