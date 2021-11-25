
<link rel="stylesheet" type="text/css"
	href="${pageContext.request.contextPath}/style/style.css">

<div class="sidenav">
	<div style="border-bottom: 1px solid #eaeaea;">
		<form class="search" action="">

			<%
			String checker = request.getParameter("search");
			if (checker != null) {
			%>
			<input type="text" placeholder="Search.." name="search"
				value="${param.search}">
			<%
			} else {
			%>
			<input type="text" placeholder="Search.." name="search">
			<%
			}
			%>
			<button type="submit">
				<i class="fa fa-search"></i>
			</button>
		</form>
		<br> <br>
	</div>
	<a href="contentView"><i class="fa fa-calendar"></i> View Content</a> <a
		href="#services"><i class="fa fa-edit"></i> Form Content</a>
</div>
