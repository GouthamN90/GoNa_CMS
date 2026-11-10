<%@ tag language="java" pageEncoding="UTF-8" description="GoNa Backoffice Component Selection Card" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<%@ attribute name="template" type="java.lang.String" required="true" rtexprvalue="true" description="Badge text in top-right (e.g., SimpleHeaderComponent)" %>
<%@ attribute name="uid" type="java.lang.String" required="true" rtexprvalue="true" description="Component UID code (e.g., DEFAULT_SIMPLE_HEADER_COMPONENT)" %>
<%@ attribute name="name" type="java.lang.String" required="true" rtexprvalue="true" description="Display name of the component" %>
<%@ attribute name="imageUrl" type="java.lang.String" required="false" rtexprvalue="true" description="Optional preview image URL" %>
<%@ attribute name="itemId" type="java.lang.String" required="true" rtexprvalue="true" description="Target URL for the Create button" %>

<!-- Externalized Component Card Stylesheet -->
<link rel="stylesheet" type="text/css" href="<c:url value='/css/component_card.css' />" />
<script defer src="<c:url value='/js/cmscomponent-card.js' />"></script>

<c:choose>
	<c:when test="${defaultTemplateListFlag == true}">
		<c:set var="resolvedButtonText" value="Create ${template }"/>
	</c:when>
	<c:otherwise>
		<c:set var="resolvedButtonText" value=""/>
	</c:otherwise>
</c:choose>

<c:set var="resolvedButtonText" value="${not empty buttonText ? buttonText : 'Create '.concat(template)}" />

<div class="gona-component-card">
    <!-- Top Preview Area -->
    <div class="component-card-preview">
        <span class="component-type-badge"><c:out value="${template}" /></span>

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
        <span class="component-uid-tag"><c:out value="${uid}" /></span>
        <h3 class="component-card-title"><c:out value="${name}" /></h3>

        <hr class="component-card-divider" />
        
        <c:choose>
			<c:when test="${defaultTemplateListFlag == true}">
				<a href="javascript:void(0)" onclick="openComponentModal(${itemId})" class="component-create-btn">
		            Create ${template }
		        </a>
			</c:when>
			<c:otherwise>
				<a href="<c:url value='/update_component/${itemId}' />" class="component-create-btn">
		            Edit Component
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