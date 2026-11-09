<%@ page trimDirectiveWhitespaces="true" %>
<%@ taglib prefix="tag" tagdir="/WEB-INF/tags" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html>
	<head>
	    <meta charset="UTF-8">
	    <title>Backoffice - Dashbaord</title>
	    <link rel="stylesheet" type="text/css" href="<c:url value='/css/main.css'/>"/>
	</head>
	<body>
		<div class="app-wrapper">
			<tag:header/>
			<div class="app-body">
				<div class="app-navbar">
					<tag:nav-sidebar activeTab="dashboard"/>
				</div>
				
				<div class="app-content">
					Hello, welcome to the backoffice dashboard page. Here you can manage your settings, view reports, 
					and monitor system performance. Please navigate through the menu to access different features and tools available for your account. 
					If you need assistance, feel free to reach out to our support team.
				</div>
			</div>
		</div>
		
	</body>
</html>