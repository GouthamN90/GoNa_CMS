package com.gona.app.populator;

import com.gona.app.form.ComponentForm;
import com.gona.app.model.cms.CMSComponent;

public interface ComponentFormPopulator<S extends CMSComponent, T extends ComponentForm> {
	
	default void populate(final CMSComponent component, final ComponentForm componentForm) {
		componentForm.setId(component.getId());
		componentForm.setUid(component.getUid());
		componentForm.setName(component.getName());
		componentForm.setTemplate(component.getTemplate());
		componentForm.setPreviewImgUrl(component.getPreviewImgUrl());
		componentForm.setDefaultTemplate(component.isDefaultTemplate());
		componentForm.setApprovalStatus(component.getApprovalStatus());
	}

	public T populate(CMSComponent component);
	
	public void populate(ComponentForm componentForm, CMSComponent component);

}
