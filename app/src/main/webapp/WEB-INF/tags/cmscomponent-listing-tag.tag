<%@ tag language="java" pageEncoding="UTF-8" description="GoNa Backoffice List CMSComponent" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="tag" tagdir="/WEB-INF/tags" %>

<link rel="stylesheet" type="text/css" href="<c:url value='/css/cmscomponent-listing.css' />" />

<c:choose>
	<c:when test="${defaultTemplateListFlag == true}">
		<c:set var="pageHeader" value="Create Component"/>
		<c:set var="pageDescription" value="Select a template to create component"/>
		<c:set var="searchActionUrl" value="select_default_component" />
		<c:set var="searchPlaceHolder" value="Search default templates..." />
	</c:when>
	<c:otherwise>
		<c:set var="pageHeader" value="Component Catalog"/>
		<c:set var="pageDescription" value="Search and manage your cmscomponents."/>
		<c:set var="searchActionUrl" value="components_listing" />
		<c:set var="searchPlaceHolder" value="Search cmscomponents..." />
	</c:otherwise>
</c:choose>

<div class="gona-cmscomponentList max-w-7xl mx-auto space-y-6">
    <!-- Header Actions Row -->
    <div class="flex flex-col md:flex-row md:items-center md:justify-between gap-4">
        <div>
            <h1 class="text-2xl font-bold text-gray-900 tracking-tight">${pageHeader}</h1>
            <c:if test="${defaultTemplateListFlag == true}">
            	
            </c:if>
            <p class="text-sm text-gray-500 mt-1">${pageDescription}</p>
        </div>
    </div>
    
    <tag:searchbar actionUrl="${searchActionUrl}" searchPlaceholder="${searchPlaceHolder }" />
    
    <div class="table-body">
        <c:choose>
            <c:when test="${not empty listComponents}">
                
                <c:forEach var="item" items="${listComponents}">
                    <tag:cmscomponent-card 
						template="${item.template}" 
						uid="${item.uid}" 
						name="${item.name}" 
						imageUrl="${item.previewImgUrl}" 
						itemId="${item.id}"/>
                </c:forEach>
            </c:when>
            
            <c:otherwise>
                <tag:empty-list-tag />
            </c:otherwise>
        </c:choose>
    </div>
    
    <tag:pagination actionUrl="${searchActionUrl}" pagination="${pagination}" searchQuery="${searchQuery}" 
    	statusFilter="${statusFilter}" sortBy="${pagination.sortBy}" sortDir="${pagination.sortDir}" selectedTemplate="${selectedTemplate }"/>
</div>