<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<!DOCTYPE html>
<html>
<meta name="viewport" content="width=device-width, initial-scale=1">
<head>
<meta charset="ISO-8859-1">
<title>Insert title here</title>
<link rel="stylesheet" type="text/css"
	href="${pageContext.request.contextPath}/res/style.css">
<link rel="stylesheet"
	href="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/4.7.0/css/font-awesome.min.css">
</head>
<body>
	<div class="topnav">
		<a href="home-page.tiles"
			class="home"><i class="fa fa-home"></i> Home page</a> <a
			href="add-employee.tiles"
			class="gray">Add an employee</a> <a
			href="list-employee.tiles"
			class="gray">List Employee</a>

		<div class="topnav-right">
			<a href="home-page.tiles"
				class="yellow"><i class="fa fa-user-circle-o"></i> java</a> <a
				href="${pageContext.request.contextPath}/login.tiles"
				class="gray"><i class="fa fa-user-times"></i> Logout</a>
		</div>
	</div>


</body>
</html>