<%@ tag language="java" pageEncoding="UTF-8" description="GoNa Backoffice Page Catalog Listing Component" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="tag" tagdir="/WEB-INF/tags" %>
<%@ attribute name="listPages" type="java.util.Collection" required="false" rtexprvalue="true" description="List of bopage entities" %>
<%@ attribute name="approvalStatus" type="java.util.Collection" required="false" rtexprvalue="true" description="List of approval statuses for filtering" %>
<%@ attribute name="statusFilter" type="java.lang.String" required="false" rtexprvalue="true" description="Currently selected status filter" %>
<%@ attribute name="sortBy" type="java.util.Collection" required="false" rtexprvalue="true" description="Sort by field options" %>
<%@ attribute name="sortOrder" type="java.util.Collection" required="false" rtexprvalue="true" description="Sort direction options" %>
<%@ attribute name="searchQuery" type="java.lang.String" required="false" rtexprvalue="true" description="Current search keyword query" %>
<%@ attribute name="pagination" type="java.lang.Object" required="false" rtexprvalue="true" description="Pagination metadata object" %>

<link rel="stylesheet" type="text/css" href="<c:url value='/css/cmspage-listing.css' />" />

<div class="gona-bopageList max-w-7xl mx-auto space-y-6">
    <!-- Header Actions Row -->
    <div class="flex flex-col md:flex-row md:items-center md:justify-between gap-4">
        <div>
            <h1 class="text-2xl font-bold text-gray-900 tracking-tight">Page Catalog</h1>
            <p class="text-sm text-gray-500 mt-1">Search and manage your storefront pages.</p>
        </div>
    </div>

    <!-- Search & Filter Card -->
    <c:set var="searchActionUrl" value="bopage_list" />
    <tag:searchbar actionUrl="${searchActionUrl}" searchPlaceholder="Search pages..." />
    

    <!-- Catalog List Table Card -->
    <div class="listview-wrapper">
    	<div class="table-card">
		    <!-- 1. Table Header -->
		    <div class="table-header">
		        <div class="col-name">Name</div>
		        <div class="col-uid">UID</div>
		        <div class="col-status">Status</div>
		    </div>
		
		    <!-- 2. Table Body Content Area -->
		    <div class="table-body">
		        <c:choose>
		            <c:when test="${not empty listPages}">
		                <%-- If data exists, loop over items --%>
		                
		                <c:forEach var="item" items="${listPages}">
		                    <div class="table-row">
		                        <div class="col-name">
		                        	<a href="<c:url value='/bopage_detail/${item.id}' />">
		                        		<span class="span-col-name">${item.name}</span>
		                        	</a>
		                        </div>
		                        <div class="col-uid">${item.uid}</div>
		                        <div class="col-status">
		                        	<c:choose>
	                                    <c:when test="${item.approvalStatus == 'Show'}">
	                                        <c:set var="badgeClass" value="status-badge-green" />
	                                    </c:when>
	                                    <c:when test="${item.approvalStatus == 'PendingApproval'}">
	                                        <c:set var="badgeClass" value="status-badge-yellow" />
	                                    </c:when>
	                                    <c:otherwise>
	                                        <c:set var="badgeClass" value="status-badge-red" />
	                                    </c:otherwise>
	                                </c:choose>
		                            <span class="status-badge ${badgeClass}">${item.approvalStatus}</span>
		                        </div>
		                    </div>
		                </c:forEach>
		            </c:when>
		            
		            <c:otherwise>
		                <tag:empty-list-tag />
		            </c:otherwise>
		        </c:choose>
		    </div>
		</div>
    </div>
    <!-- Pagination Controller Bar -->
    <tag:pagination actionUrl="${searchActionUrl}" pagination="${pagination}" searchQuery="${searchQuery}" statusFilter="${statusFilter}" 
    	sortBy="${pagination.sortBy}" sortDir="${pagination.sortDir}" />
</div>