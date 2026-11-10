<%@ tag language="java" pageEncoding="UTF-8" description="GoNa Backoffice Pagination Bar Component" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<%@ attribute name="pagination" type="java.lang.Object" required="true" rtexprvalue="true" description="Pagination object containing currentPage, totalPages, pageSize, totalItems" %>
<%@ attribute name="searchQuery" type="java.lang.String" required="false" rtexprvalue="true" description="Search query string parameter" %>
<%@ attribute name="statusFilter" type="java.lang.String" required="false" rtexprvalue="true" description="Status filter parameter" %>
<%@ attribute name="sortBy" type="java.lang.String" required="false" rtexprvalue="true" description="Sort field parameter" %>
<%@ attribute name="sortDir" type="java.lang.String" required="false" rtexprvalue="true" description="Sort direction parameter" %>
<%@ attribute name="actionUrl" type="java.lang.String" required="false" rtexprvalue="true" description="Target URL parameter" %>
<%@ attribute name="selectedTemplate" type="java.lang.String" required="false" rtexprvalue="true" description="Selected template filter parameter" %>

<!-- Externalized Pagination Stylesheet -->
<link rel="stylesheet" type="text/css" href="<c:url value='/css/pagination.css' />" />

<c:set var="targetUrl" value="${pageContext.request.contextPath}/${actionUrl }" />

<!-- Calculate Entry Bounds -->
<c:set var="startEntry" value="${(pagination.currentPage * pagination.pageSize) + 1}" />
<c:set var="endEntry" value="${(pagination.currentPage + 1) * pagination.pageSize}" />
<c:if test="${endEntry > pagination.totalItems}">
    <c:set var="endEntry" value="${pagination.totalItems}" />
</c:if>
<c:if test="${pagination.totalItems == 0}">
    <c:set var="startEntry" value="0" />
    <c:set var="endEntry" value="0" />
</c:if>

<div class="gona-pagination-bar">
    <!-- Info label on the left -->
    <div class="gona-pagination-info">
        Showing <strong><c:out value="${startEntry}" /></strong> to <strong><c:out value="${endEntry}" /></strong> of <strong><c:out value="${pagination.totalItems}" /></strong> entries
    </div>

    <!-- Controls group on the right -->
    <c:if test="${pagination.totalPages > 1}">
        <div class="gona-pagination-nav" aria-label="Pagination Navigation">
            
            <!-- Previous Button -->
            <c:url var="prevUrl" value="${targetUrl}">
                <c:param name="pageNum" value="${pagination.currentPage - 1}" />
                <c:param name="pageSize" value="${pagination.pageSize}" />
                <c:if test="${not empty searchQuery}"><c:param name="searchQuery" value="${searchQuery}" /></c:if>
                <c:if test="${not empty statusFilter}"><c:param name="status" value="${statusFilter}" /></c:if>
                <c:if test="${not empty sortBy}"><c:param name="sortBy" value="${sortBy}" /></c:if>
                <c:if test="${not empty sortDir}"><c:param name="sortDir" value="${sortDir}" /></c:if>
                <c:if test="${not empty selectedTemplate}"><c:param name="selectedTemplate" value="${selectedTemplate}" /></c:if>
            </c:url>
            <a href="${prevUrl}" 
               class="gona-pagination-btn ${pagination.currentPage <= 0 ? 'disabled' : ''}" 
               aria-label="Previous page">
                <svg class="arrow-icon" viewBox="0 0 24 24" stroke-linecap="round" stroke-linejoin="round">
                    <polyline points="15 18 9 12 15 6"></polyline>
                </svg>
            </a>

            <!-- Page Number Buttons -->
            <c:forEach var="pageIdx" begin="0" end="${pagination.totalPages - 1}">
                <c:url var="pageUrl" value="${targetUrl}">
                    <c:param name="pageNum" value="${pageIdx}" />
                    <c:param name="pageSize" value="${pagination.pageSize}" />
                    <c:if test="${not empty searchQuery}"><c:param name="searchQuery" value="${searchQuery}" /></c:if>
                    <c:if test="${not empty statusFilter}"><c:param name="status" value="${statusFilter}" /></c:if>
                    <c:if test="${not empty sortBy}"><c:param name="sortBy" value="${sortBy}" /></c:if>
                    <c:if test="${not empty sortDir}"><c:param name="sortDir" value="${sortDir}" /></c:if>
                    <c:if test="${not empty selectedTemplate}"><c:param name="selectedTemplate" value="${selectedTemplate}" /></c:if>
                </c:url>
                <a href="${pageUrl}" 
                   class="gona-pagination-btn ${pageIdx == pagination.currentPage ? 'active' : ''}">
                    <c:out value="${pageIdx + 1}" />
                </a>
            </c:forEach>

            <!-- Next Button -->
            <c:url var="nextUrl" value="${targetUrl}">
                <c:param name="pageNum" value="${pagination.currentPage + 1}" />
                <c:param name="pageSize" value="${pagination.pageSize}" />
                <c:if test="${not empty searchQuery}"><c:param name="searchQuery" value="${searchQuery}" /></c:if>
                <c:if test="${not empty statusFilter}"><c:param name="status" value="${statusFilter}" /></c:if>
                <c:if test="${not empty sortBy}"><c:param name="sortBy" value="${sortBy}" /></c:if>
                <c:if test="${not empty sortDir}"><c:param name="sortDir" value="${sortDir}" /></c:if>
                <c:if test="${not empty selectedTemplate}"><c:param name="selectedTemplate" value="${selectedTemplate}" /></c:if>
            </c:url>
            <a href="${nextUrl}" 
               class="gona-pagination-btn ${pagination.currentPage >= pagination.totalPages - 1 ? 'disabled' : ''}" 
               aria-label="Next page">
                <svg class="arrow-icon" viewBox="0 0 24 24" stroke-linecap="round" stroke-linejoin="round">
                    <polyline points="9 18 15 12 9 6"></polyline>
                </svg>
            </a>

        </div>
    </c:if>
</div>