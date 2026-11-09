<%@ page trimDirectiveWhitespaces="true" %>
<%@ taglib prefix="tag" tagdir="/WEB-INF/tags" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html>
	<head>
	    <meta charset="UTF-8">
	    <title>Backoffice - Page Details</title>
	    <link rel="stylesheet" type="text/css" href="<c:url value='/css/main.css'/>"/>
	</head>
	<body>
		<div class="app-wrapper">
			<tag:header/>
			<div class="app-body">
				<div class="app-navbar">
					<tag:nav-sidebar activeTab="cmspages"/>
				</div>
				
				<div class="app-content">
					<tag:cmspage-detail/>
				</div>
			</div>
		</div>
		
	</body>
</html>