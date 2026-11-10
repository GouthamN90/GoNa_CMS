<%@ page trimDirectiveWhitespaces="true" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>

<link rel="stylesheet" type="text/css" href="<c:url value='/css/banner-component-form.css' />" />

<div class="component-form-card">
    <form:form action="${pageContext.request.contextPath}/save_component" modelAttribute="componentForm" method="post">
        <form:hidden path="id" value='${componentForm.id }'/>
        <form:hidden path="template" value='${componentForm.template }' />
        <form:hidden path="screenshotString" value='${componentForm.screenshotString }'/>

        <div class="component-form-grid">
            <!-- Banner Image Url -->
            <div class="component-form-group">
                <label for="imageUrl" class="component-form-label">Banner Image Url</label>
                <form:input path="imageUrl" id="imageUrl" cssClass="component-form-control" placeholder="https://res.cloudinary.com/..." />
                <form:errors path="imageUrl" cssClass="component-form-error" />
            </div>

            <!-- Banner text -->
            <div class="component-form-group">
                <label for="bannerText" class="component-form-label">Banner text</label>
                <form:input path="bannerText" id="bannerText" cssClass="component-form-control" placeholder="Enter banner text" />
                <form:errors path="bannerText" cssClass="component-form-error" />
            </div>

            <!-- Redirect to -->
            <div class="component-form-group">
                <label for="ctaLink" class="component-form-label">Redirect to</label>
                <form:input path="ctaLink" id="ctaLink" cssClass="component-form-control" placeholder="https://example.com/cta" />
                <form:errors path="ctaLink" cssClass="component-form-error" />
            </div>

            <!-- Approval Status -->
            <div class="component-form-group">
                <label for="approvalStatus" class="component-form-label">Approval Status</label>
                <form:select path="approvalStatus" id="approvalStatus" cssClass="component-form-control">
                    <c:forEach var="status" items="${approvalStatuses}">
                        <form:option value="${status}" label="${status}" />
                    </c:forEach>
                </form:select>
                <form:errors path="approvalStatus" cssClass="component-form-error" />
            </div>
        </div>

        <!-- Action Buttons -->
        <div class="component-form-actions">
            <button type="button" class="btn-preview-component" onclick="previewComponent()">Preview Component</button>
            <button type="submit" class="btn-save-component">Save Component</button>
        </div>
    </form:form>
</div>
