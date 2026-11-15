package com.gona.app.services;

import java.util.List;
import java.util.Optional;

import org.apache.commons.collections4.CollectionUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.gona.app.model.ApprovalStatus;
import com.gona.app.model.CMSVersion;
import com.gona.app.model.cms.CMSComponent;
import com.gona.app.model.cms.CMSPage;
import com.gona.app.repository.CMSPageRepository;
import com.gona.app.synchronize.CMSSynchronizer;

import io.micrometer.common.util.StringUtils;
import jakarta.persistence.EntityNotFoundException;

@Service
public class CMSPageService {
	
	@Autowired
	private CMSPageRepository cmsPageRepository;
	
	@Autowired
	private CMSSynchronizer cmsSynchronizer;
	
    public List<CMSPage> getAllPages() {
        return cmsPageRepository.findAll();
    }

    public Optional<CMSPage> getPageForId(Long id) {
        return cmsPageRepository.findById(id);
    }
    
    public Optional<CMSPage> getPageForUidVersionAndApprovalStatus(String uid, CMSVersion version, ApprovalStatus approvalStatus) {
        return cmsPageRepository.findByUidAndVersionAndApprovalStatus(uid,version,  approvalStatus);
    }
    
    public Optional<CMSPage> getPageForUidAndVersion(String uid, CMSVersion version) {
        return cmsPageRepository.findByUidAndVersion(uid, version);
    }

    public CMSPage savePage(CMSPage page) {
        return cmsPageRepository.save(page);
    }
    
    public Page<CMSPage> getPages(Pageable pageable) {
		return cmsPageRepository.findAll(pageable);
	}

	public Page<CMSPage> getPages(Pageable pageable, String searchQuery, String status, String version) {
		return cmsPageRepository.findCMSPages(pageable, searchQuery, StringUtils.isNotBlank(status) ? Enum.valueOf(ApprovalStatus.class, status) : null,
				StringUtils.isNotBlank(version) ? Enum.valueOf(CMSVersion.class, version) : null);
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
		CMSPage page = cmsPageRepository.findById(id)
                .orElseThrow(() -> new EntityNotFoundException("Page not found"));
		if (page.getVersion().equals(CMSVersion.Online)) {
			cmsPageRepository.delete(page);
		} else {
			final List<CMSPage> pages = cmsPageRepository.findByUid(page.getUid());
			cmsPageRepository.deleteAll(pages);
		}
		
	}

	public Long syncPage(String uid) {
		final List<CMSPage> pages = cmsPageRepository.findByUid(uid);
		if (CollectionUtils.isEmpty(pages)) {
			throw new EntityNotFoundException("No page found with uid: " + uid);
		}
		final Optional<CMSPage> draftPageOpt = pages.stream()
				.filter(page -> CMSVersion.Draft.equals(page.getVersion()))
				.findFirst();
		if (draftPageOpt.isEmpty()) {
			throw new EntityNotFoundException("No draft page found with uid: " + uid);
		}
		final CMSPage draftPage = draftPageOpt.get();
		final Optional<CMSPage> onlinePageOpt = pages.stream()
				.filter(page -> CMSVersion.Online.equals(page.getVersion()))
				.findFirst();
		CMSPage onlinePage = onlinePageOpt.orElseGet(() -> {
			return new CMSPage();
		});
		cmsSynchronizer.synchronizePage(draftPage, onlinePage);
		return onlinePage.getId();
	}

}
