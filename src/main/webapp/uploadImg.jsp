<%--
  Description:这是上传图片的页面
  User: Darlen liu
  Date: 14-6-23
  Time: 下午10:19
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
    <meta content="text/html; charset=utf-8" http-equiv="content-type">
    <title>图片上传</title>
    <style type="text/css">
        @charset "UTF-8";
        body {
            margin: 0;
            padding: 0;
            /*color: #fff;*/
        }
    </style>
    <script type="text/javascript"></script>
</head>
<body>
    <form action="${pageContext.request.contextPath }/uploadImg.do" method="post" enctype="multipart/form-data">

    username:<input type="text" name="username"><br>
    password:<input type="password"  name="password"><br>

    file1:<input type="file" name="file1"><br>

    file2:<input type="file" name="file2"><br>


    <input type="submit" name="submit" value=" submit ">
</form>
</body>
</html>