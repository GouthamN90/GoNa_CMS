package com.gona.app.populator;

import java.util.ArrayList;
import java.util.List;

import org.apache.commons.collections4.CollectionUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.gona.app.data.ComponentData;
import com.gona.app.data.PageData;
import com.gona.app.model.cms.CMSComponent;
import com.gona.app.model.cms.CMSPage;

@Component
public class CMSPagePopulator {
	
	@Autowired
	private CMSComponentPopulator componentPopulator;

	public List<PageData> populate(final List<CMSPage> pages) {
		List<PageData> pageDataList = new ArrayList<>();
		if (CollectionUtils.isNotEmpty(pages)) {
			pages.forEach(page -> pageDataList.add(populate(page)));
		}
		return pageDataList;
	}
	
	public PageData populate(final CMSPage page) {
		final PageData pageData = new PageData();
		pageData.setId(page.getId());
		pageData.setUid(page.getUid());
		pageData.setName(page.getName());
		pageData.setTitle(page.getTitle());
		pageData.setMetadata(page.getMetadata());
		pageData.setApprovalStatus(page.getApprovalStatus());
		pageData.setComponents(populateComponents(page.getComponents()));
		return pageData;
	}

	private List<ComponentData> populateComponents(List<CMSComponent> components) {
		return componentPopulator.populate(components);
	}

}
