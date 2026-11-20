<%@ tag language="java" pageEncoding="UTF-8" description="GoNa Backoffice Update CMSComponent" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="tag" tagdir="/WEB-INF/tags" %>

<link rel="stylesheet" type="text/css" href="<c:url value='/css/cmscomponent-update.css' />" />
<script defer src="<c:url value='/js/cmscomponent-update.js' />"></script>

<c:set var="deleteUrl" value="${pageContext.request.contextPath}/delete_component/${editComponentData.id}" />
<c:set var="syncUrl" value="${pageContext.request.contextPath}/sync_component/${editComponentData.uid}" />
<c:set var="previewUrl" value="${storefrontUrl}/preview-page?componentUid=${editComponentData.uid}&componentTemplate=${editComponentData.template}&version=${editComponentData.version}"/>

<tag:alertmessage-container successMessage="${successMessage}" errorMessage="${errorMessage}" />

<div class="component-details-wrapper">
	<div class="component-title-row">
       <h1 class="component-name">${editComponentData.name}</h1>
       <div class="cmscomponent-actions">
       		<a href="${previewUrl}" target="_blank" class="btn">Preview</a>
	       <c:if test="${editComponentData.version == 'Draft'}">
	            <a onclick="editComponentModal()" class="btn">Edit Info</a>
	            <a href="${syncUrl}" class="btn">Synchronize</a>
           </c:if>
           <button class="btn-delete" onclick="deleteComponentModal()">Delete</button>
       </div>
    </div>
	
	<div class="component-row">
		<p>UID: <span id="compUid">${editComponentData.uid}</span></p>
		<p>Template: <span id="compUid">${editComponentData.template}</span></p>
		<p>
            <span class="component-label">Version:</span>
            <c:choose>
                <c:when test="${editComponentData.version == 'Online'}">
                    <span class="status-badge status-badge-green"><c:out value="${editComponentData.version}" /></span>
                </c:when>
                <c:otherwise>
                    <span class="status-badge status-badge-yellow"><c:out value="${editComponentData.version}" /></span>
                </c:otherwise>
            </c:choose>
        </p>
		<p>
			<span class="component-label">Status:</span>
	        <c:choose>
                <c:when test="${editComponentData.approvalStatus == 'Show'}">
                    <span class="status-badge status-badge-green"><c:out value="${editComponentData.approvalStatus}" /></span>
                </c:when>
                <c:when test="${editComponentData.approvalStatus == 'PendingApproval'}">
                    <span class="status-badge status-badge-yellow"><c:out value="${editComponentData.approvalStatus}" /></span>
                </c:when>
                <c:otherwise>
                    <span class="status-badge status-badge-red"><c:out value="${editComponentData.approvalStatus}" /></span>
                </c:otherwise>
            </c:choose>
	    </p>
		<p>Available Pages: 
			<c:if test="${not empty editComponentData.pages}">
				<c:forEach var="page" items="${editComponentData.pages}" varStatus="status">
					<c:out value="${page.name}" />
					<c:if test="${!status.last}">, </c:if>
				</c:forEach>
			</c:if>
		</p>
	</div>
</div>
	
<div id="captureSection" class="component-details-wrapper">
	<iframe src="${storefrontUrl}/preview-page?componentUid=${editComponentData.uid}&componentTemplate=${editComponentData.template}&version=${editComponentData.version}"
		></iframe>
</div>
	
<div id="editComponentModal" class="modal-overlay">
	<div class="modal-container">
		<div class="modal-header">
			<h3 class="modal-title">Edit ${editComponentData.name}</h3>
			<button type="button" class="modal-close-btn" onclick="closeEditComponentModal()" aria-label="Close modal">&times;</button>
		</div>
		<div class="modal-body">
			<jsp:include page="${componentFormPage}.jsp" />
		</div>
	</div>
</div>

<div id="removeComponentModal" class="modal-overlay">
	<div class="modal-container">
		<div class="modal-header">
			<h3 class="modal-title">Confirm Deletion</h3>
			<button type="button" class="modal-close-btn" onclick="closeDeleteComponentModal()" aria-label="Close modal">&times;</button>
		</div>
		<div class="modal-body">
			<c:choose>
				<c:when test="${editComponentData.version == 'Draft'}">
					<p>Deleting Draft version will also delete Online version of the component and cannot be undone. Please confirm if you want to delete ${editComponentData.name}.</p>
				</c:when>
				<c:otherwise>
					<p>Deleting Online version will not delete Draft version. Please confirm if you want to delete Online version of ${editComponentData.name}.</p>
				</c:otherwise>
			</c:choose>
			
		</div>
		<div class="modal-footer">
			<button type="button" class="btn-modal-reset" onclick="closeDeleteComponentModal()">Cancel</button>
			<a href="${deleteUrl}" class="btn-delete">Delete</a>
		</div>
	</div>
</div>
