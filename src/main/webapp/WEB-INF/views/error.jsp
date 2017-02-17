<%--
  Created by IntelliJ IDEA.
  User: djj
  Date: 2017/2/17
  Time: 11:49
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>错误页面</title>
</head>
<body>
<h1>所有的演示例子</h1>
<h3>[url=./dao.do?id=1]Dao正常错误[/url]</h3>
<h3>[url=./dao.do?id=10]Dao参数错误[/url]</h3>
<h3>[url=./dao.do?id=]Dao未知错误[/url]</h3>
%ex%


<h3>[url=./service.do?id=1]Service正常错误[/url]</h3>
<h3>[url=./service.do?id=10]Service参数错误[/url]</h3>
<h3>[url=./service.do?id=]Service未知错误[/url]</h3>


<h3>[url=./controller.do?id=1]Controller正常错误[/url]</h3>
<h3>[url=./controller.do?id=10]Controller参数错误[/url]</h3>
<h3>[url=./controller.do?id=]Controller未知错误[/url]</h3>


<h3>[url=./404.do?id=1]404错误[/url]</h3>
</body>
</html>
