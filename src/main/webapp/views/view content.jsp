
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/sql" prefix="sql"%>
<h1>VIEW CONTENT</h1>
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
			<jsp:useBean id="Bean1" class="model.ContentBean" />
			<%
			String checker = request.getParameter("Page");
			if (checker == null) {
				response.sendRedirect("/contentView");
			}
			checker = request.getParameter("numId");
			if (checker == null) {
				response.sendRedirect("/contentView");
			}
			int row_num = 1;
			int NumP = Integer.parseInt(request.getParameter("Page"));
			%>
			<%
			while (row_num <= Integer.parseInt(request.getParameter("numId"))) {
			%>
		</tr>
		<tr>
			<jsp:setProperty name="Bean1" property="id"
				value="<%=Integer.toString(NumP * 10 + row_num - 1)%>" />
			<jsp:setProperty name="Bean1" property="titles"
				value="<%=Integer.toString(NumP * 10 + row_num - 1)%>" />
			<jsp:setProperty name="Bean1" property="brief"
				value="<%=Integer.toString(NumP * 10 + row_num - 1)%>" />
			<jsp:setProperty name="Bean1" property="createdDate"
				value="<%=Integer.toString(NumP * 10 + row_num - 1)%>" />
			<td><jsp:getProperty name="Bean1" property="id" /></td>
			<td><jsp:getProperty name="Bean1" property="titles" /></td>
			<td><jsp:getProperty name="Bean1" property="brief" /></td>
			<td><jsp:getProperty name="Bean1" property="createdDate" /></td>

			<td style="text-align: center;">


				<form action="edit_user.jsp" method="post">
					<input type="hidden" name="id"
						value=<jsp:getProperty name="Bean1" property="id" />> <input
						type="hidden" name="authorId"
						value=${cookie['cookiename'].getValue()} />
					<button type="submit" name="type" value="Edit" class="btn-edit">
						<c:out value="Edit" />
					</button>

					<button type="submit" name="type" value="Delete" class="btn-delete">
						<c:out value="Delete" />
					</button>
				</form>
			</td>
		</tr>
		<%
		row_num = row_num + 1;
		}
		%>
	</table>
	<p>
	<form action="${pageContext.request.contextPath}/contentView"
		method="post" class="button">
		<%
		int break_while = 0;
		int buttoncheck = 6;
		while (buttoncheck >= 0) {
			String button = request.getParameter("Bt" + buttoncheck);
			if (button != null) {
				if (Integer.parseInt(button) < NumP) {
		%>
		<input type="submit" name="Page" class="btn-Page" value="<%=button%>"
			name="submit">
		<%
		}
		}
		buttoncheck--;
		}
		%>
		<input type="submit" name="Page" class="btn-Page"
			value="<%=request.getParameter("Page")%>" name="submit">
		<%
		buttoncheck = 1;
		while (buttoncheck <= 6) {
			String button = request.getParameter("Bt" + buttoncheck);
			if (button != null) {
				if (Integer.parseInt(button) > NumP) {
		%>
		<input type="submit" name="Page" class="btn-Page" value="<%=button%>"
			name="submit">
		<%
		}
		}
		buttoncheck++;
		}
		%>
	</form>
	<p>
		<%

		%>
	
</div>

