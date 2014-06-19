<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ page isELIgnored="false" %>
<%
	out.println();
	out.println(request.getParameter("name"));
%>

<html>
<head>
	<meta http-equiv="Content-Type" content="text/html; charset=utf-8">
</head>
<body>
<a href="Login.jsp">登录</a>
<h2>My first test maven web program</h2>
${sessionScope.user}
${name}<br>
${password}<br>
</body>
</html>
