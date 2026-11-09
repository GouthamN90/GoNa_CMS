package com.gona.app.services;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import com.gona.app.model.ApprovalStatus;
import com.gona.app.model.cms.CMSComponent;
import com.gona.app.repository.CMSComponentRepository;
import com.gona.app.form.CloneComponentForm;

import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;

@Service
public class CMSComponentService {
	
	@Autowired
	private CMSComponentRepository cmsComponentRepository;
	
	@PersistenceContext
	private EntityManager entityManager;
	
	public Page<CMSComponent> getAllComponents(final Pageable pageable, final boolean defaultTemplate) {
		return cmsComponentRepository.fetchAllCMSComponents(pageable, defaultTemplate);
	}

	public Page<CMSComponent> getAllComponents(final Pageable pageable, final String searchQuery, final String selectedTemplate, final boolean defaultTemplate) {
		return cmsComponentRepository.fetchAllCMSComponents(pageable, searchQuery, selectedTemplate, defaultTemplate);
	}
	
	public Optional<CMSComponent> getByIdAndDefaultTemplate(final Long id, final boolean defaultTemplate) {
		return cmsComponentRepository.findByIdAndDefaultTemplate(id, defaultTemplate);
	}
	
	public CMSComponent saveComponent(final CMSComponent component) {
		return cmsComponentRepository.save(component);
	}

	public Long cloneComponent(final CloneComponentForm form) {
		final Optional<CMSComponent> componentOpt = getByIdAndDefaultTemplate(form.getDefaultCompId(), true);
		if (componentOpt.isPresent()) {
			final CMSComponent defaultComponent = componentOpt.get();
			entityManager.detach(defaultComponent);
			defaultComponent.setId(null);
			defaultComponent.setUid(form.getNewCompUid());
			defaultComponent.setName(form.getNewCompName());
			defaultComponent.setDefaultTemplate(false);
			final CMSComponent clonedComponent = saveComponent(defaultComponent);
			return clonedComponent.getId();
		}
		return null;
	}

	public CMSComponent getComponentForUidAndApprovalStatus(final String uid, final boolean defaultTemplate,
			final ApprovalStatus status) {
		final Optional<CMSComponent> componentOpt = cmsComponentRepository.findByUidAndDefaultTemplateAndApprovalStatus(uid, defaultTemplate, status);
		return componentOpt.orElse(null);
	}

}
