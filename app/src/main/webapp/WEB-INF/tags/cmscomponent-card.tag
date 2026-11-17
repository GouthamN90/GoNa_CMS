<%@ tag language="java" pageEncoding="UTF-8" description="GoNa Backoffice Component Selection Card" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<%@ attribute name="template" type="java.lang.String" required="true" rtexprvalue="true" description="Badge text in top-right (e.g., SimpleHeaderComponent)" %>
<%@ attribute name="uid" type="java.lang.String" required="true" rtexprvalue="true" description="Component UID code (e.g., DEFAULT_SIMPLE_HEADER_COMPONENT)" %>
<%@ attribute name="name" type="java.lang.String" required="true" rtexprvalue="true" description="Display name of the component" %>
<%@ attribute name="imageUrl" type="java.lang.String" required="false" rtexprvalue="true" description="Optional preview image URL" %>
<%@ attribute name="itemId" type="java.lang.String" required="true" rtexprvalue="true" description="Target URL for the Create button" %>
<%@ attribute name="version" type="java.lang.String" required="false" rtexprvalue="true" description="Version of the component" %>
<%@ attribute name="status" type="java.lang.String" required="false" rtexprvalue="true" description="Status of the component" %>
<%@ attribute name="page" type="java.lang.String" required="false" rtexprvalue="true" description="Status of the component" %>

<!-- Externalized Component Card Stylesheet -->
<link rel="stylesheet" type="text/css" href="<c:url value='/css/component_card.css' />" />
<script defer src="<c:url value='/js/cmscomponent-card.js' />"></script>

<c:choose>
	<c:when test="${status == 'Show'}">
		<c:set var="statusBadgeClass" value="status-badge-green"/>
	</c:when>
	<c:when test="${status == 'PendingApproval'}">
		<c:set var="statusBadgeClass" value="status-badge-yellow"/>
	</c:when>
	<c:otherwise>
		<c:set var="statusBadgeClass" value="status-badge-red"/>
	</c:otherwise>
</c:choose>

<c:choose>
	<c:when test="${version == 'Draft'}">
		<c:set var="versionBadgeClass" value="status-badge-yellow"/>
		<c:set var="resolvedButtonText" value="Edit ${template}"/>
	</c:when>
	<c:otherwise>
		<c:set var="versionBadgeClass" value="status-badge-green"/>
		<c:set var="resolvedButtonText" value="View ${template}"/>
	</c:otherwise>
</c:choose>

<div class="gona-component-card">
    <!-- Top Preview Area -->
    <div class="component-card-preview">
        <span class="component-type-versionbadge ${versionBadgeClass}"><c:out value="${version}" /></span>
        <span class="component-type-badge ${statusBadgeClass}"><c:out value="${status}" /></span>

        <c:choose>
            <c:when test="${not empty imageUrl}">
                <img src="<c:url value='${imageUrl}' />" alt="<c:out value='${name}' />" />
            </c:when>
            <c:otherwise>
                <div class="component-no-image">
                    <svg viewBox="0 0 24 24" fill="none" stroke="currentColor" stroke-width="1.8" stroke-linecap="round" stroke-linejoin="round">
                        <rect x="3" y="3" width="18" height="18" rx="2" ry="2"></rect>
                        <circle cx="8.5" cy="8.5" r="1.5"></circle>
                        <polyline points="21 15 16 10 5 21"></polyline>
                    </svg>
                    <span>No Image</span>
                </div>
            </c:otherwise>
        </c:choose>
    </div>

    <!-- Bottom Details & Action Area -->
    <div class="component-card-body">
        
        <h3 class="component-card-title"><c:out value="${name}" /></h3>
        <span class="component-uid-tag"><c:out value="${uid}" /></span>
        <span class="component-uid-tag"><c:out value="${template}" /></span>

        <hr class="component-card-divider" />
        
        <c:choose>
			<c:when test="${defaultTemplateListFlag == true}">
				<a href="javascript:void(0)" onclick="openComponentModal(${itemId})" class="component-create-btn">
		            Create ${template }
		        </a>
			</c:when>
			<c:when test="${page == 'AssignPage' }">
				<a href="<c:url value='/add_component_to_page/${pageId}/${itemId}' />" class="component-create-btn">
		            Add to Page
		        </a>
			</c:when>
			<c:otherwise>
				<a href="<c:url value='/update_component/${itemId}' />" class="component-create-btn">
		            ${resolvedButtonText}
		        </a>
			</c:otherwise>
		</c:choose>

        
    </div>
</div>

<div id="createComponentModal" class="modal-overlay">
	<div class="modal-container">
        
        <!-- Modal Header -->
        <div class="modal-header">
            <h2 class="modal-title">Create Component</h2>
            <button type="button" class="modal-close-btn" onclick="closeComponentModal()" aria-label="Close modal">&times;</button>
        </div>

        <form id="cloneComponentForm" action="<c:url value='/clone_component' />" method="post">
            <!-- Modal Body -->
            <div class="modal-body">
            	<div class="modal-form-grid">
					<input type="hidden" id="modalDefaultCompId" name="defaultCompId" value=""/>
                     
                     <!-- Component UID -->
			<!-- TODO check if component uid already exisits -->
                     <div class="modal-form-group">
                         <label for="modalNewCompUid">Component UID</label>
                         <input type="text" id="modalNewCompUid" name="newCompUid" placeholder="Provide unique component uid" required />
                     </div>
			
			<!-- Component Name -->
                     <div class="modal-form-group">
                         <label for="modalNewCompName">Component Name</label>
                         <input type="text" id="modalNewCompName" name="newCompName" placeholder="Provide new component name" required />
                     </div>

                 </div>
             </div>

            <!-- Modal Footer -->
            <div class="modal-footer">
                <button type="submit" class="btn-modal-save">Confirm</button>
            </div>
        </form>

    </div>
</div>