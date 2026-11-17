<%@ tag language="java" pageEncoding="UTF-8" description="GoNa Backoffice Page Component assignment" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="tag" tagdir="/WEB-INF/tags" %>

<link rel="stylesheet" type="text/css" href="<c:url value='/css/cmspage-component-assignment.css' />" />
<script defer src="<c:url value='/js/cmspage-component-assignment.js' />"></script>

<c:if test="${pageData.version == 'Draft'}">
<div class="pg-comp-assign-warapper">
	<h2>Search and Add Components</h2>
	<div class="search-group">
        <span class="search-icon">
        	<svg xmlns="http://www.w3.org/2000/svg" fill="none" viewBox="0 0 24 24" stroke-width="1.5" stroke="currentColor" class="search-icon" style="width: 20px; height: 20px;">
        		<path stroke-linecap="round" stroke-linejoin="round" d="m21 21-5.197-5.197m0 0A7.5 7.5 0 1 0 5.196 5.196a7.5 7.5 0 0 0 10.607 10.607Z" />
        	</svg>
		
		</span>
        <input value="${searchQuery }" id="searchInput" type="text" name="searchQuery" placeholder="${empty searchPlaceHolder ? 'Search...' : searchPlaceHolder}" class="filter-input-text" />
        
        
        <div class="horizontal-scroll-grid" id="componentsGrid" data-pageid="${pageData.id}">
		            
		</div>
	</div>
</div>
</c:if>

<div class="pg-comp-assign-warapper">		
	<div class="detail-group">
	    <div class="bocomponent-header-row">
	        <h2>Associated Components</h2>
	    </div>
	
	    <!-- Empty state -->
	    <c:if test="${empty pageData.components}">
	        <p class="empty-components-msg">No components attached to this page.</p>
	    </c:if>
	
	    <!-- Draggable component cards -->
	    <c:if test="${not empty pageData.components}">
	        <c:set var="resolvedReorderUrl" value="" />
	        <c:if test="${not empty reorderUrl}">
	            <c:url var="resolvedReorderUrl" value="${reorderUrl}" />
	        </c:if>
	
	        <div id="page-components-container" 
	             data-pageid="${pageData.id}">
	
	            <c:forEach var="component" items="${pageData.components}" varStatus="iterStat">
	                <div class="page-component-card" draggable="true" data-index="${iterStat.count}" 
	                     data-compid="${component.id}">
	                    
	                    <div class="card-top-row">
	                    	<div class="card-top-info">
	                        <h4 class="component-name"><c:out value="${component.name}" /></h4>
	                        
	                        <c:choose>
				                <c:when test="${component.version == 'Online'}">
				                    <span class="status-badge status-badge-green"><c:out value="${component.version}" /></span>
				                </c:when>
				                <c:otherwise>
				                    <span class="status-badge status-badge-yellow"><c:out value="${component.version}" /></span>
				                </c:otherwise>
				            </c:choose>
	
	                        <c:choose>
				                <c:when test="${component.approvalStatus == 'Show'}">
				                    <span class="status-badge status-badge-green"><c:out value="${component.approvalStatus}" /></span>
				                </c:when>
				                <c:when test="${component.approvalStatus == 'PendingApproval'}">
				                    <span class="status-badge status-badge-yellow"><c:out value="${component.approvalStatus}" /></span>
				                </c:when>
				                <c:otherwise>
				                    <span class="status-badge status-badge-red"><c:out value="${component.approvalStatus}" /></span>
				                </c:otherwise>
				            </c:choose>
				            </div>
				            <c:if test="${pageData.version == 'Draft'}">
				            <div class="component-card-actions">
	                            <a href="<c:url value='/remove_component_from_page/${pageData.id}/${component.id}' />" class="a-btn-delete">Remove</a>
	                            <a href="<c:url value='/update_component/${component.id}' />" class="btn-edit-info">Edit</a>
	                        </div>
	                        </c:if>
	                    </div>
	
                        <div class="component-template-text">
                        	<div><span class="bopage-detail-label">Template:</span>${component.template}</div>
                            <div><span class="bopage-detail-label">UID:</span>${component.uid}</div>
                        </div>
	
	                </div>
	            </c:forEach>
	        </div>
	    </c:if>
	</div>
   
</div>