<%@ tag language="java" pageEncoding="UTF-8" description="GoNa Backoffice Component Selection Card" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<%@ attribute name="componentType" type="java.lang.String" required="true" rtexprvalue="true" description="Badge text in top-right (e.g., SimpleHeaderComponent)" %>
<%@ attribute name="uid" type="java.lang.String" required="true" rtexprvalue="true" description="Component UID code (e.g., DEFAULT_SIMPLE_HEADER_COMPONENT)" %>
<%@ attribute name="title" type="java.lang.String" required="true" rtexprvalue="true" description="Display title of the component" %>
<%@ attribute name="imageUrl" type="java.lang.String" required="false" rtexprvalue="true" description="Optional preview image URL" %>
<%@ attribute name="actionUrl" type="java.lang.String" required="true" rtexprvalue="true" description="Target URL for the Create button" %>
<%@ attribute name="buttonText" type="java.lang.String" required="false" rtexprvalue="true" description="Optional custom button text (defaults to 'Create ' + componentType)" %>

<!-- Externalized Component Card Stylesheet -->
<link rel="stylesheet" type="text/css" href="<c:url value='/css/component_card.css' />" />

<c:set var="resolvedButtonText" value="${not empty buttonText ? buttonText : 'Create '.concat(componentType)}" />

<div class="gona-component-card">
    <!-- Top Preview Area -->
    <div class="component-card-preview">
        <span class="component-type-badge"><c:out value="${componentType}" /></span>

        <c:choose>
            <c:when test="${not empty imageUrl}">
                <img src="<c:url value='${imageUrl}' />" alt="<c:out value='${title}' />" />
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
        <h3 class="component-card-title"><c:out value="${title}" /></h3>

        <hr class="component-card-divider" />

        <a href="<c:url value='${actionUrl}' />" class="component-create-btn">
            <c:out value="${resolvedButtonText}" />
        </a>
    </div>
</div>