<%--
  Created by IntelliJ IDEA.
  User: admin
  Date: 2021/9/8
  Time: 13:32
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Title</title>
</head>
<body>
<form action="http://localhost:8080/Cookie_Session/loginServlet" method="get">
    用户名：<input type="text" name="username" value="${cookie.username.value}">
    密码：<input type="text" name="password">
    <input type="submit" value="登入">
</form>
</body>
</html>
