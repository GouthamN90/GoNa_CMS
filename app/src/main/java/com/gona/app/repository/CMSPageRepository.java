package com.gona.app.repository;

import java.util.Optional;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.gona.app.model.ApprovalStatus;
import com.gona.app.model.cms.CMSPage;

@Repository
public interface CMSPageRepository extends JpaRepository<CMSPage, Long> {
	
	@Query("SELECT p FROM CMSPage p WHERE " +
	           "(:search IS NULL OR :search = '' OR " +
	           " LOWER(p.name) LIKE LOWER(CONCAT('%', :search, '%')) OR " +
	           " LOWER(p.uid) LIKE LOWER(CONCAT('%', :search, '%')) OR " +
	           " LOWER(p.title) LIKE LOWER(CONCAT('%', :search, '%')))  " +
	           "AND (:status IS NULL OR p.approvalStatus = :status)")
	Page<CMSPage> findCMSPages(final Pageable pageable,
	            @Param("search") final String search,
	            @Param("status") final ApprovalStatus status
	    );

	Optional<CMSPage> findByUidAndApprovalStatus(final String uid, final ApprovalStatus approvalStatus);

}
