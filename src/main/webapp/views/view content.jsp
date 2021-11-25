
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/sql" prefix="sql"%>
<br>
<h1>View Content</h1>
<hr width="90%" size="1px" align="center" />
<div class="headDiv">&emsp; View Content List</div>
<div class="borderDiv">
	<p>
	<table>
		<tr>
			<th style="width: 1%;">#</th>
			<th style="width: 20%;">Title</th>
			<th style="width: 45%;">Brief</th>
			<th style="width: 15%;">Created Date</th>
			<th>Action</th>
			<%
			String checker = request.getParameter("Page");
			if (checker == null) {
				response.sendRedirect("/contentView");
			}
			int row_num = 1;
			int NumP = Integer.parseInt(request.getParameter("Page"));
			%>
			<!--   for (Todo todo: todos) {  -->
			<c:forEach var="content" items="${listContent}">
				<tr>
					<td><c:out value="${content.id}" /></td>
					<td><c:out value="${content.titles}" /></td>
					<td><c:out value="${content.brief}" /></td>
					<td><c:out value="${content.createdDate}" /></td>
					<td style="text-align: center;">
						<form action="edit_user.jsp" method="post">
							<input type="hidden" name="id"
								value="${content.id}" > <input
								type="hidden" name="authorId"
								value=${cookie['cookiename'].getValue()} />
							<button type="submit" name="type" value="Edit" class="btn-edit">
								<c:out value="Edit" />
							</button>

							<button type="submit" name="type" value="Delete"
								class="btn-delete">
								<c:out value="Delete" />
							</button>
						</form>
					</td>
				</tr>
			</c:forEach>
			<!-- } -->
	</table>
	<p>
	<form action="${pageContext.request.contextPath}/contentView"
		method="post" class="button">
		<%
		String button = "";
		button = request.getParameter("BtFirst");
		if (button != null) {
		%>
		<input type="submit" name="Page" class="btn-Page" value="<%=button%>"
			name="submit">
		<font class="btn-Page">...</font>
		<%
		}
		int break_while = 0;
		int buttoncheck = 5;
		while (buttoncheck >= 0) {
		button = request.getParameter("Bt" + buttoncheck);
		if (button != null) {
		%>
		<input type="submit" name="Page" class="btn-Page" value="<%=button%>"
			name="submit">
		<%
		}
		buttoncheck--;
		}
		%>
		<input type="submit" name="Page" class="btn-Page"
			value="<%=request.getParameter("Page")%>" name="submit"
			style="background: blue; color: white;">
		<%
		buttoncheck = 6;
		while (buttoncheck <= 10) {
			button = request.getParameter("Bt" + buttoncheck);
			if (button != null) {
		%>
		<input type="submit" name="Page" class="btn-Page" value="<%=button%>"
			name="submit">
		<%
		}
		buttoncheck++;
		}
		button = request.getParameter("BtLast");
		if (button != null) {
		%>
		<input type="submit" name="Page" class="btn-Page" value="<%=button%>"
			name="submit">
		<%
		}
		%>
	</form>
	<p>
</div>

