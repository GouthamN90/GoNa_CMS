<%@ tag language="java" pageEncoding="UTF-8" description="GoNa Backoffice Page Detail View Card" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="tag" tagdir="/WEB-INF/tags" %>

<!-- Externalized CSS Stylesheet -->
<link rel="stylesheet" type="text/css" href="<c:url value='/css/cmspage-detail.css' />" />
<script defer src="<c:url value='/js/cmspage_detail.js' />"></script>

<c:set var="editUrl" value="${pageContext.request.contextPath}/edit_page/${pageData.id}" />
<c:set var="deleteUrl" value="${pageContext.request.contextPath}/delete_page/${pageData.id}" />
<c:set var="syncUrl" value="${pageContext.request.contextPath}/sync_page/${pageData.uid}" />

<tag:alertmessage-container successMessage="${successMessage}" errorMessage="${errorMessage}" />

<div class="bopage-card">
    <!-- Header: Title and Action Buttons -->
    <div class="bopage-card-header">
        <h1 class="bopage-title"><c:out value="${pageData.name}" /></h1>
	        <div class="bopage-actions">
	        	<c:if test="${pageData.version == 'Draft'}">
		            <a href="${editUrl}" class="btn-edit-info">Edit Info</a>
		            <a href="${syncUrl}" class="btn-sync">Synchronize</a>
	            </c:if>
	            <button class="btn-delete" onclick="deletePageModal()">Delete</button>
	        </div>
    </div>

    <!-- Page Details List -->
    <div class="bopage-details">
        <div class="bopage-detail-row">
            <span class="bopage-detail-label">UID:</span>
            <span class="bopage-detail-value"><c:out value="${pageData.uid}" /></span>
        </div>
        <div class="bopage-detail-row">
            <span class="bopage-detail-label">Version:</span>
            <c:choose>
                <c:when test="${pageData.version == 'Online'}">
                    <span class="status-badge status-badge-green"><c:out value="${pageData.version}" /></span>
                </c:when>
                <c:otherwise>
                    <span class="status-badge status-badge-yellow"><c:out value="${pageData.version}" /></span>
                </c:otherwise>
            </c:choose>
        </div>

        <div class="bopage-detail-row">
            <span class="bopage-detail-label">Status:</span>
            <c:choose>
                <c:when test="${pageData.approvalStatus == 'Show'}">
                    <span class="status-badge status-badge-green"><c:out value="${pageData.approvalStatus}" /></span>
                </c:when>
                <c:when test="${pageData.approvalStatus == 'PendingApproval'}">
                    <span class="status-badge status-badge-yellow"><c:out value="${pageData.approvalStatus}" /></span>
                </c:when>
                <c:otherwise>
                    <span class="status-badge status-badge-red"><c:out value="${pageData.approvalStatus}" /></span>
                </c:otherwise>
            </c:choose>
        </div>

        <div class="bopage-detail-row">
            <span class="bopage-detail-label">Title:</span>
            <span class="bopage-detail-value"><c:out value="${pageData.title}" /></span>
        </div>

        <div class="bopage-detail-row">
            <span class="bopage-detail-label">Metadata:</span>
            <span class="bopage-detail-value"><c:out value="${pageData.metadata}" /></span>
        </div>
    </div>
</div>

<div id="removePageModal" class="modal-overlay">
	<div class="modal-container">
		<div class="modal-header">
			<h3 class="modal-title">Confirm Deletion</h3>
			<button type="button" class="modal-close-btn" onclick="closeDeletePageModal()" aria-label="Close modal">&times;</button>
		</div>
		<div class="modal-body">
			<c:choose>
				<c:when test="${pageData.version == 'Draft'}">
					<p>Deleting Draft version will also delete Online version of the page and cannot be undone. Please confirm if you want to delete ${pageData.name}.</p>
				</c:when>
				<c:otherwise>
					<p>Deleting Online version will not delete Draft version. Please confirm if you want to delete Online version of ${pageData.name}.</p>
				</c:otherwise>
			</c:choose>
			
		</div>
		<div class="modal-footer">
			<button type="button" class="btn-modal-reset" onclick="closeDeletePageModal()">Cancel</button>
			<a href="${deleteUrl}" class="btn-delete">Delete</a>
		</div>
	</div>
</div>