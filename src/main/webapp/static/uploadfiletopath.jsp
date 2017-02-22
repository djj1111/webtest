<%--
  Created by IntelliJ IDEA.
  User: djj
  Date: 2017/2/19
  Time: 22:10
  To change this template use File | Settings | File Templates.
--%>
<%@ taglib prefix='security' uri='http://www.springframework.org/security/tags' %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Title</title>
</head>
<body>
<form name="myform" method="post" enctype="multipart/form-data" action="/uploadfiletopath">
    <input type="file" name="files" multiple>
    <security:csrfInput/>
    <input type="submit">
</form>
</body>
</html>
