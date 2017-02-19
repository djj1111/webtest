<%--
  Created by IntelliJ IDEA.
  User: djj
  Date: 2017/2/19
  Time: 19:28
  To change this template use File | Settings | File Templates.
--%>
<%@ taglib prefix='security' uri='http://www.springframework.org/security/tags' %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>

<html>
<head>
    <title>Title</title>
</head>
<body>
<form name="myform" method="post" action="/file/addfile">
    <input type="text" name="mid">
    <input type="text" name="path">
    <security:csrfInput/>
    <input type="submit">
</body>
</html>
