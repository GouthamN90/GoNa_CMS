<%@ tag language="java" pageEncoding="UTF-8" description="Alert and notification messages component" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<%@ attribute name="successMessage" type="java.lang.String" required="false" rtexprvalue="true" description="Success message text" %>
<%@ attribute name="errorMessage" type="java.lang.String" required="false" rtexprvalue="true" description="Error message text" %>

<!-- Optional external stylesheet -->
<link rel="stylesheet" type="text/css" href="<c:url value='/css/alertmessage-container.css' />" />

<c:if test="${not empty successMessage or not empty errorMessage}">
    <div class="gona-alert-container">
        <c:if test="${not empty successMessage}">
            <span class="success-msg"><c:out value="${successMessage}" /></span>
        </c:if>
        <c:if test="${not empty errorMessage}">
            <span class="error-msg"><c:out value="${errorMessage}" /></span>
        </c:if>
    </div>
</c:if>