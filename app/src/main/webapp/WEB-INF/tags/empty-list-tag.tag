<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<link rel="stylesheet" type="text/css" href="<c:url value='/css/empty-list.css'/>" />

<div class="empty-state-wrapper">
    <!-- Clean inline SVG representation of the sad face icon -->
    <svg class="empty-icon" xmlns="http://w3.org" viewBox="0 0 24 24" fill="none" stroke="currentColor" stroke-width="1.5">
        <circle cx="12" cy="12" r="10"/>
        <path d="M8 14s1.5-2 4-2 4 2 4 2"/>
        <line x1="9" y1="9" x2="9.01" y2="9"/>
        <line x1="15" y1="9" x2="15.01" y2="9"/>
    </svg>
    <h3 class="empty-title">No item found</h3>
    <p class="empty-subtitle">Try refining your filters or search keywords.</p>
</div>