<%--
  Created by IntelliJ IDEA.
  User: darlenliu
  Date: 14-6-18
  Time: 下午4:14
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<% String context = request.getContextPath();
	String basePath = request.getScheme() + "://"+ request.getServerName()
			+ ":" + request.getServerPort()	+ context + "/";
%>
<html>
<head>
	<title></title>
	<script type="text/javascript" src="<%=context%>/js/jquery-1.7.1.js"></script>
	<script type="text/javascript" >
		$(function(){

		})

		function send(){
			$.ajax({
				url:"<%=context%>/testLogin.do?action=login",
				type:"POST",
				dataType:"json",
				data:{
					"name":$("input[name=name]").val(),
					"password":$("input[name=password]").val()
				},
				contentType:"application/x-www-form-urlencoded; charset=UTF-8",
				success:function(data) {
					if(data.success)
						alert(data.user);
					else{
						alert("fail");
					}
				}
			})

		}
	</script>
</head>
<body>
	<form action="testLogin.do?action=login" method="post">
		Name:<input type="text" name="name" />
		Password:<input type="password" name="password" />
		<input type="button" onclick="send()" value="send">
		<input type="submit" value="登录" />
	</form>

</body>
</html>