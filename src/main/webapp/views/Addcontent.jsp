
	<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/sql" prefix="sql"%>
<link rel="stylesheet"
	href="${pageContext.request.contextPath}/style/editprofile_style.css" />

<jsp:useBean id="content" class="model.Content" />
		<%
			String checker = request.getParameter("id");
			if (checker == null) {
				response.sendRedirect("/logout");
			}
			checker = request.getParameter("name");
			if (checker == null) {
				response.sendRedirect("/logout");}
			int id = Integer.parseInt(request.getParameter("id"));
			int idC=0;
			int sort =0;
			String title="";
			String brief="";
			String contentn="";			
			int check = Integer.parseInt(request.getParameter("static"));
			if(check==0 )
			{
				title = request.getParameter("title"); 
				brief = request.getParameter("brief");
				brief = brief.trim();
				contentn = request.getParameter("contentn");
				contentn= contentn.trim();
				idC = Integer.parseInt(request.getParameter("idC"));
				sort = Integer.parseInt(request.getParameter("sort")); 
			}
			
			%>
	<div class="mid">
		<div class="title">
			<h2>
			<%  
					if(check==1){
				%>
			Add Content
			<%}		
		%>
		<%  
					if(check==0){
				%>
			Update Content
			<%}		
		%>
			</h2>
			<hr width="98%" align="center" color="#D5D5D5" size="2px" />
		</div>		
		<%  
					if(check==1){
				%>
		<form method="post"  onsubmit="return validate()" action="AddContent">
		<%}		
		%>
		<%  
					if(check==0){
				%>
		<form method="post" onsubmit="return validate()" action="updateContent">
		<%}		
		%>
		<div class="personal-details">
			<p>Content Form Elements</p>
					<center>	 <a class="text-danger">${mess}</a></center>
			
			<div class="col-6">			
			<span class="error" id="tilesid" class="text-danger"></span>
				<div class="inputs">
					<label><b>Title</b></label> <input  id ="tiles" name ="tiles" class="form-control" type="text"
						placeholder="Enter the Title"  value="<%= title %>" onblur="validate1()" onkeyup="tile()" >
				</div>
				<%  
					if(check==0){
				%>
				<div class="inputs">
					<label><b>Sort</b></label> <input min = "0" id ="sort" name ="sort" class="form-control" type="number"
						placeholder="Enter the sort"  value="<%= sort %>" required>
				</div>
			<%}		
		%>
			
			</div>
			<div class="row">
				<div class="inputs">
					<label><b>Brief</b></label>
					<textarea id = "brief2" name ="brief2" class="form-control" type="text"
						placeholder="Enter self-description" required><%=brief%></textarea>
				</div>
				<div class="inputs">
					<label><b>Content</b></label>
					<textarea id = "content2" name ="content2" class="form-controls" type="text"
						placeholder="Enter self-description"  required><%=contentn %></textarea>
				</div>
			</div>
			
			<div class="justify-content-end">
				<button onclick="myFunction()" type="submit" class="btn">Submit Button</button>
				<button type="reset" class="btn">Reset Button</button></a>
				
			</div>
				<input type="hidden" id="id" name="id" value="<%= id %>">
				<input type="hidden" id="name" name="name" value="<%= checker %>">
				<input type="hidden" id="idC" name="idC" value="<%= idC %>">
				<input type="hidden" id="brief" name ="brief"></input>
				<input type="hidden" id="content" name="content"></input>			
			</div>
			</form>
		</div>
		<script>
		 
function myFunction() {
  var x = document.getElementById("brief2").value;
  document.getElementById("brief").value = x;
  var y = document.getElementById("content2").value;
  document.getElementById("content").value = y;
}

const first = document.querySelector("#tiles");
function tile(){
	 var regName = /^([A-Za-z]{3,30})$/;
	 var tile = document.getElementById('tiles').value;
	    if(!regName.test(tile)){
	    	first.style.borderColor = "#e74c3c";
           first.style.background = "#fceae9";
	        return true;
	    }else{
	    	first.style.borderColor = "#27ae60";
           first.style.background = "#eafaf1";
	        return false;
	    }
}
function validate1(){
	 
	 if (first.value == ""){
		 first.style.borderColor = "#e74c3c";
        first.style.background = "#fceae9";
	 }
}
</script>
