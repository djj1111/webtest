<%--
  Created by IntelliJ IDEA.
  User: djj
  Date: 2017/2/25
  Time: 22:15
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix='security' uri='http://www.springframework.org/security/tags' %>
<html>
<head>
    <title>Title</title>
</head>
<body>
<h1>登 录</h1>

<form action="/login" method="post">
    <p> 用户名:<input type="text" name="username">
    <p> 密 码:<input type="password" name="password">
        <security:csrfInput/>
    <p><input type="submit">
</form>
</body>
</html>
