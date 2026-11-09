<%@ tag language="java" pageEncoding="UTF-8" description="GoNa Backoffice Page Detail View Card" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="tag" tagdir="/WEB-INF/tags" %>

<!-- Externalized CSS Stylesheet -->
<link rel="stylesheet" type="text/css" href="<c:url value='/css/cmspage-detail.css' />" />
<script defer src="<c:url value='/js/cmspage_detail.js' />"></script>

<c:set var="editUrl" value="${pageContext.request.contextPath}/edit_page/${pageData.id}" />
<c:set var="deleteUrl" value="${pageContext.request.contextPath}/delete_page/${pageData.id}" />

<tag:alertmessage-container successMessage="${successMessage}" errorMessage="${errorMessage}" />

<div class="bopage-card">
    <!-- Header: Title and Action Buttons -->
    <div class="bopage-card-header">
        <h1 class="bopage-title"><c:out value="${pageData.name}" /></h1>
        <div class="bopage-actions">
            <a href="${editUrl}" class="btn-edit-info">Edit Info</a>
            <button class="btn-delete" onclick="deletePageModal()">Delete</button>
            <%-- <form action="<c:url value='${resolvedDeleteUrl}' />" method="post" style="display:inline; margin:0;" onsubmit="return confirm('Are you sure you want to delete this page?');">
                <button type="submit" class="btn-delete">Delete</button>
            </form> --%>
        </div>
    </div>

    <!-- Page Details List -->
    <div class="bopage-details">
        <div class="bopage-detail-row">
            <span class="bopage-detail-label">UID:</span>
            <span class="bopage-detail-value"><c:out value="${pageData.uid}" /></span>
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
			<p>Once deleted page cannot be retrieved. Are you sure you want to delete this page?</p>
		</div>
		<div class="modal-footer">
			<button type="button" class="btn-modal-reset" onclick="closeDeletePageModal()">Cancel</button>
			<a href="${deleteUrl}" class="btn-delete">Delete</a>
		</div>
	</div>
</div>