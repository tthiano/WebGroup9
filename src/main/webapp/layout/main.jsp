<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<%@ taglib prefix="tiles" uri="http://tiles.apache.org/tags-tiles"%>
<!DOCTYPE html>
<html>
<head>
<title><tiles:getAsString name="title" /></title>
<link rel="stylesheet" type="text/css"
	href="${pageContext.request.contextPath}/style/style.css">
</head>
<body>
	<header>
		<tiles:insertAttribute name="header" />
	</header>
			<tiles:insertAttribute name="side" />
	<div class = "main">
			<tiles:insertAttribute name="main" />
			</div>
</body>
</html>