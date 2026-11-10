<%@ tag language="java" pageEncoding="UTF-8" %>
<%@ taglib prefix="c" uri="jakarta.tags.core" %>

<%-- Optional Attributes for configuration --%>
<%@ attribute name="actionUrl" required="true" type="java.lang.String" %>
<%@ attribute name="searchPlaceholder" required="false" type="java.lang.String" %>

<link rel="stylesheet" type="text/css" href="<c:url value='/css/searchbar.css' />" />

<form action="${pageContext.request.contextPath}/${actionUrl}" method="GET" class="filter-wrapper">
    
    <!-- Search Input Group -->
    <div class="search-group">
        <span class="search-icon">
        	<svg xmlns="http://www.w3.org/2000/svg" fill="none" viewBox="0 0 24 24" stroke-width="1.5" stroke="currentColor" class="search-icon" style="width: 20px; height: 20px;">
        		<path stroke-linecap="round" stroke-linejoin="round" d="m21 21-5.197-5.197m0 0A7.5 7.5 0 1 0 5.196 5.196a7.5 7.5 0 0 0 10.607 10.607Z" />
        	</svg>
		
		</span>
        <input value="${searchQuery }" type="text" name="searchQuery" placeholder="${empty searchPlaceholder ? 'Search...' : searchPlaceholder}" class="filter-input-text" />
    </div>

    <!-- Dropdown: Statuses -->
    <c:if test="${not empty approvalStatus}">
	    <select name="status" class="filter-select">
	        <option value="">All Statuses</option>
	        <c:forEach items="${approvalStatus}" var="stat">
	        	<option value="${stat}" <c:if test="${stat == statusFilter}">selected</c:if>>${stat}</option>
	        </c:forEach>
	    </select>
    </c:if>
    
    <!-- Dropdown: Templates -->
    <c:if test="${not empty templates}">
	    <select name="selectedTemplate" class="filter-select">
	        <option value="">All Templates</option>
	        <c:forEach items="${templates}" var="temp">
	        	<option value="${temp}" <c:if test="${temp == selectedTemplate}">selected</c:if>>${temp}</option>
	        </c:forEach>
	    </select>
	</c:if>

    <!-- Dropdown: Sort Column -->
    <select name="sortBy" class="filter-select">
    	<option value="">Sort By</option>
    	<c:forEach items="${sortBy}" var="sortB">
        	<option value="${sortB}" <c:if test="${sortB == pagination.sortBy}">selected</c:if>>${sortB}</option>
        </c:forEach>
    </select>

    <!-- Dropdown: Sort Direction -->
    <select name="sortDir" class="filter-select">
    	<option value="">Sort Order</option>
        <c:forEach items="${sortOrder}" var="sortOrd">
        	<option value="${sortOrd}" <c:if test="${sortOrd == pagination.sortDir}">selected</c:if>>${sortOrd}</option>
        </c:forEach>
    </select>

    <!-- Action Buttons -->
    <button type="submit" class="btn btn-apply">Apply</button>
    <a href='<c:url value='/${actionUrl}' />' type="reset" class="btn btn-reset">Reset</a>

</form>
