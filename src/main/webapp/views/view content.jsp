
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
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
		</tr>
		<tr>
			<td>10</td>
			<td>Maria Anders</td>
			<td>Germany</td>
			<td>Germany</td>
			<td style="text-align: center;"><form action="edit_user.jsp"
					method="post">
					<input type="hidden" name="id" value="1">
					<button type="submit" name="type" value="Edit" class="btn-edit">
						<c:out value="Edit" />
					</button>

					<button type="submit" name="type" value="Delete" class="btn-delete">
						<c:out value="Delete" />
					</button>
				</form></td>
		</tr>
	</table>
	<p>
</div>

