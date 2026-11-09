<%@ tag language="java" pageEncoding="UTF-8" description="GoNa Backoffice Page Form Component" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ attribute name="pageForm" type="java.lang.Object" required="false" rtexprvalue="true" description="Page model attribute backing the form" %>
<%@ attribute name="approvalStatuses" type="java.util.Collection" required="false" rtexprvalue="true" description="Available approval statuses" %>
<%@ attribute name="errorMessage" type="java.lang.String" required="false" rtexprvalue="true" description="General error message banner" %>

<link rel="stylesheet" type="text/css" href="<c:url value='/css/cmspage-form.css' />" />

<div class="gona-cmspage-form">
    <div class="form-container">
        <h2>
            <c:choose>
                <c:when test="${empty pageForm.id}">Create New Page</c:when>
                <c:otherwise>Edit Page</c:otherwise>
            </c:choose>
        </h2>

        <c:if test="${not empty errorMessage}">
            <span class="error-msg"><c:out value="${errorMessage}" /></span>
        </c:if>

        <form:form action="${pageContext.request.contextPath}/page/save" modelAttribute="pageForm" method="post">
            <form:hidden path="id" />

            <div class="form-grid">
                <div class="form-group">
                    <label for="name">Page Name *</label>
                    <form:input path="name" id="name" placeholder="e.g., Homepage" />
                    <form:errors path="name" cssClass="error-msg" />
                </div>

                <div class="form-group">
                    <label for="uid">Page UID *</label>
                    <form:input path="uid" id="uid" placeholder="e.g., homepage" />
                    <form:errors path="uid" cssClass="error-msg" />
                </div>

                <div class="form-group">
                    <label for="title">Page title</label>
                    <form:input path="title" id="title" placeholder="Enter page title" />
                    <form:errors path="title" cssClass="error-msg" />
                </div>

                <div class="form-group full-width">
                    <label for="metadata">Metadata</label>
                    <form:textarea path="metadata" id="metadata" placeholder="Provide metadata..." />
                    <form:errors path="metadata" cssClass="error-msg" />
                </div>

                <div class="form-group">
                    <label for="approvalStatus">Approval Status</label>
                    <form:select path="approvalStatus" id="approvalStatus">
                        <form:option value="" label="-- Select Status --" />
                        <c:forEach var="stat" items="${approvalStatuses}">
                            <form:option value="${stat}" label="${stat}" />
                        </c:forEach>
                    </form:select>
                    <form:errors path="approvalStatus" cssClass="error-msg" />
                </div>
            </div>

            <div class="btn-container">
                <button type="submit" class="btn-submit">Save Page</button>
            </div>
        </form:form>
    </div>
</div>