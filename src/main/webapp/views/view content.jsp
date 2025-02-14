
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
			String checkerr = request.getParameter("id");
			if (checkerr == null) {
				response.sendRedirect("/logout");
			}
			checkerr = request.getParameter("name");
			if (checkerr == null) {
				response.sendRedirect("/logout");}
			int id = Integer.parseInt(request.getParameter("id"));		
			
			String checker = request.getParameter("Page");
			if (checker == null) {
				response.sendRedirect("/viewContentForm");
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
					<td style="text-align: center; display:flex;  align-content: space-between;">
					<div>
						<form action="updateContentForm" method="post">
							<input type="hidden" name="idC"
								value="${content.id}" > <input
								type="hidden" name="authorId"
								value=${cookie['cookiename'].getValue()} />
							<button type="submit" name="type" value="Edit" class="btn-edit">
								<c:out value="Edit" />
							</button>							
		<input type="hidden" id="id" name="id" value="<%= id %>">
				<input type="hidden" id="name" name="name" value="<%= checkerr %>">
							</form>
							</div>
							<div>
							<form action="deleteContent" method="post">
							<button Onclick="return ConfirmDelete();"  type="submit" name="type" value="Delete"
								class="btn-delete">
								<c:out value="Delete" />
							</button>
							<input type="hidden" name="idC"
								value="${content.id}" >
								<input type="hidden" id="id" name="id" value="<%= id %>">
				<input type="hidden" id="name" name="name" value="<%= checkerr %>">
						</form>
						</div>
					</td>
				</tr>
			</c:forEach>
			<!-- } -->
	</table>
	<p>
	<form action="viewContentPage"
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
		<input type="hidden" id="id" name="id" value="<%= id %>">
				<input type="hidden" id="name" name="name" value="<%= checkerr %>">
	</form>
	<p>
</div>
 <script>
            function ConfirmDelete()
            {
              var x = confirm("Are you sure you want to delete?");
              if (x)
                  return true;
              else
                return false;
            }
            </script>
