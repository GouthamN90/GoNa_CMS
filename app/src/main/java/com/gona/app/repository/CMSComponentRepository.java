package com.gona.app.repository;

import java.util.Optional;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.gona.app.model.ApprovalStatus;
import com.gona.app.model.CMSVersion;
import com.gona.app.model.cms.CMSComponent;

@Repository
public interface CMSComponentRepository extends JpaRepository<CMSComponent, Long> {
    
	@Query("SELECT c FROM CMSComponent c WHERE c.defaultTemplate = :defaultTemplate AND" +
				" (:search IS NULL OR :search = '' OR " +
	           " LOWER(c.name) LIKE LOWER(CONCAT('%', :search, '%')) OR " +
	           " LOWER(c.template) LIKE LOWER(CONCAT('%', :search, '%')) OR " +
	           " LOWER(c.uid) LIKE LOWER(CONCAT('%', :search, '%'))) "
	           + "AND (:selectedTemplate IS NULL OR :selectedTemplate = '' OR c.template = :selectedTemplate)")
    Page<CMSComponent> fetchAllCMSComponents(final Pageable pageable,
            @Param("search") final String search,
            @Param("selectedTemplate") final String selectedTemplate,
            @Param("defaultTemplate") final boolean defaultTemplate
    );
	
	@Query("SELECT c FROM CMSComponent c WHERE c.defaultTemplate = :defaultTemplate")
    Page<CMSComponent> fetchAllCMSComponents(final Pageable pageable, final boolean defaultTemplate);

	Optional<CMSComponent> findByIdAndDefaultTemplate(final Long id, final boolean defaultTemplate);

	Optional<CMSComponent> findByUidAndDefaultTemplateAndApprovalStatus(final String uid,
			final boolean defaultTemplate, final ApprovalStatus status);

	CMSComponent findByUidAndVersion(String uid, CMSVersion online);
}
