package com.gona.app.services;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.gona.app.model.ApprovalStatus;
import com.gona.app.model.cms.CMSComponent;
import com.gona.app.model.cms.CMSPage;
import com.gona.app.repository.CMSPageRepository;

import io.micrometer.common.util.StringUtils;
import jakarta.persistence.EntityNotFoundException;

@Service
public class CMSPageService {
	
	@Autowired
	private CMSPageRepository cmsPageRepository;
	
    public List<CMSPage> getAllPages() {
        return cmsPageRepository.findAll();
    }

    public Optional<CMSPage> getPageForId(Long id) {
        return cmsPageRepository.findById(id);
    }
    
    public Optional<CMSPage> getPageForUidAndApprovalStatus(String uid, ApprovalStatus approvalStatus) {
        return cmsPageRepository.findByUidAndApprovalStatus(uid, approvalStatus);
    }

    public CMSPage savePage(CMSPage page) {
        return cmsPageRepository.save(page);
    }
    
    public org.springframework.data.domain.Page<CMSPage> getPages(Pageable pageable) {
		return cmsPageRepository.findAll(pageable);
	}

	public org.springframework.data.domain.Page<CMSPage> getPages(Pageable pageable, String searchQuery, String status) {
		return cmsPageRepository.findCMSPages(pageable, searchQuery, StringUtils.isNotBlank(status) ? Enum.valueOf(ApprovalStatus.class, status) : null);
	}
	
	@Transactional
	public void reorderPageComponent(Long pageId, int fromIndex, int toIndex) {
        CMSPage page = cmsPageRepository.findById(pageId)
                .orElseThrow(() -> new EntityNotFoundException("Page not found"));

        List<CMSComponent> components = page.getComponents();

        if (fromIndex < 0 || fromIndex >= components.size() || 
            toIndex < 0 || toIndex >= components.size()) {
            throw new IllegalArgumentException("Index out of bounds");
        }

        // Remove and insert at the new position
        CMSComponent movedComponent = components.remove(fromIndex);
        components.add(toIndex, movedComponent);

        // No explicit repository.save() needed if inside @Transactional,
        // Hibernate dirty checking detects the list mutation and updates component_order.
    }

	public void deletePage(Long id) {
		cmsPageRepository.deleteById(id);
	}

}
