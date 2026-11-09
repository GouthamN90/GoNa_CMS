<%@ page trimDirectiveWhitespaces="true" %>
<%@ taglib prefix="tag" tagdir="/WEB-INF/tags" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html>
	<head>
	    <meta charset="UTF-8">
	    <title>Backoffice - List Components</title>
	    <link rel="stylesheet" type="text/css" href="<c:url value='/css/main.css'/>"/>
	</head>
	<body>
		<c:choose>
			<c:when test="${defaultTemplateListFlag == true}">
				<c:set var="activeTab" value="createcomponent"/>
			</c:when>
			<c:otherwise>
				<c:set var="activeTab" value="componentslisting"/>
			</c:otherwise>
		</c:choose>
	
	
		<div class="app-wrapper">
			<tag:header/>
			<div class="app-body">
				<div class="app-navbar">
					<tag:nav-sidebar activeTab="${activeTab }"/>
				</div>
				
				<div class="app-content">
					<tag:cmscomponent-listing-tag/>
				</div>
			</div>
		</div>
		
	</body>
</html>