<html>
<body>
<% Cookie cookie = new Cookie("userId","1");
cookie.setMaxAge(60*60*24);response.addCookie(cookie);
%>

<a href="${pageContext.request.contextPath}/contentView">view content</a>
</body>
</html>
