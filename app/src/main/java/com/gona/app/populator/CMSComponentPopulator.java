package com.gona.app.populator;

import java.util.ArrayList;
import java.util.List;

import org.apache.commons.collections4.CollectionUtils;
import org.springframework.stereotype.Component;

import com.gona.app.data.ComponentData;
import com.gona.app.data.PageData;
import com.gona.app.model.cms.CMSComponent;
import com.gona.app.model.cms.CMSPage;

@Component
public class CMSComponentPopulator {

	public List<ComponentData> populate(final List<CMSComponent> components) {
		final List<ComponentData> componentDataList = new ArrayList<>();
		if (CollectionUtils.isNotEmpty(components)) {
			components.forEach(component -> componentDataList.add(populate(component)));
		}
		return componentDataList;
	}

	public ComponentData populate(final CMSComponent component) {
		final ComponentData componentData = new ComponentData();
		componentData.setName(component.getName());
		componentData.setTemplate(component.getTemplate());
		componentData.setPreviewImgUrl(component.getPreviewImgUrl());
		componentData.setDefaultTemplate(component.isDefaultTemplate());
		componentData.setApprovalStatus(component.getApprovalStatus());
		componentData.setUid(component.getUid());
		componentData.setId(component.getId());
		if (CollectionUtils.isNotEmpty(component.getPages())) {
			List<PageData> pageDataList = new ArrayList<>();
			for (CMSPage page : component.getPages()) {
				final PageData pageData = new PageData();
				pageData.setId(page.getId());
				pageData.setName(page.getName());
				pageDataList.add(pageData);
			}
			componentData.setPages(pageDataList);
		}
		return componentData;
	}
}
