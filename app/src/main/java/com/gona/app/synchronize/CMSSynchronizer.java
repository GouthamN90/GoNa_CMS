package com.gona.app.synchronize;

import org.apache.commons.collections4.CollectionUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import com.gona.app.model.CMSVersion;
import com.gona.app.model.cms.CMSComponent;
import com.gona.app.model.cms.CMSPage;
import com.gona.app.repository.CMSComponentRepository;
import com.gona.app.repository.CMSPageRepository;

import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;

@Component
public class CMSSynchronizer {
	
	@Autowired
	private CMSPageRepository cmsPageRepository;
	
	@Autowired
	private CMSComponentRepository cmsComponentRepository;
	
	@PersistenceContext
	private EntityManager entityManager;
	
	@Transactional
	public CMSPage synchronizePage(CMSPage draftPage, CMSPage onlinePage) {
		onlinePage.setVersion(CMSVersion.Online);
		onlinePage.setUid(draftPage.getUid());
		onlinePage.setName(draftPage.getName());
		onlinePage.setTitle(draftPage.getTitle());
		onlinePage.setApprovalStatus(draftPage.getApprovalStatus());
		onlinePage.setMetadata(draftPage.getMetadata());
		if (CollectionUtils.isNotEmpty(draftPage.getComponents())) {
			draftPage.getComponents().forEach(component -> {
				final CMSComponent onlineComponent = synchronizePageComponent(component);
				onlinePage.addComponent(onlineComponent);
			});
		}
		cmsPageRepository.save(onlinePage);
		return onlinePage;
	}

	private CMSComponent synchronizePageComponent(CMSComponent component) {
		CMSComponent onlineComponent = cmsComponentRepository.findByUidAndVersion(component.getUid(), CMSVersion.Online);
		if (onlineComponent == null) {
			onlineComponent = component;
			entityManager.detach(onlineComponent);
			onlineComponent.setId(null);
			onlineComponent.setVersion(CMSVersion.Online);
			cmsComponentRepository.save(onlineComponent);
		}
		return onlineComponent;
		
	}

}
