<%@ tag language="java" pageEncoding="UTF-8" description="GoNa Backoffice Update CMSComponent" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="tag" tagdir="/WEB-INF/tags" %>


<div class="component-details-wrapper">
	<div class="component-title-row">
       <h1 class="component-name">${editComponentData.name}</h1>
    </div>
	
	<div class="component-row">
		<p>UID: <span id="compUid">${editComponentData.uid}</span></p>
		<div>
			Status:
	        <c:choose>
                <c:when test="${editComponentData.approvalStatus == 'Show'}">
                    <span class="status-badge status-badge-green"><c:out value="${editComponentData.approvalStatus}" /></span>
                </c:when>
                <c:when test="${editComponentData.approvalStatus == 'PendingApproval'}">
                    <span class="status-badge status-badge-yellow"><c:out value="${editComponentData.approvalStatus}" /></span>
                </c:when>
                <c:otherwise>
                    <span class="status-badge status-badge-red"><c:out value="${editComponentData.approvalStatus}" /></span>
                </c:otherwise>
            </c:choose>
	    </div>
		<p>Available Pages: 
			<c:if test="${not empty editComponentData.pages}">
				<c:forEach var="page" items="${editComponentData.pages}" varStatus="status">
					<c:out value="${page.name}" />
					<c:if test="${!status.last}">, </c:if>
				</c:forEach>
			</c:if>
		</p>
	</div>
</div>
	
<div id="captureSection"></div>
	
<tag:alertmessage-container successMessage="${successMessage}" errorMessage="${errorMessage}" />
<jsp:include page="${componentFormPage}.jsp" />
