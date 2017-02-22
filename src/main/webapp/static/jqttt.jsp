<%--
  Created by IntelliJ IDEA.
  User: djj
  Date: 2017/2/22
  Time: 20:00
  To change this template use File | Settings | File Templates.
--%>
<%@ taglib prefix='security' uri='http://www.springframework.org/security/tags' %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <script src="js/jquery-3.1.1.min.js"></script>
    <script src="js/jquery.ui.widget.js"></script>
    <script src="js/jquery.iframe-transport.js"></script>
    <script src="js/jquery.fileupload.js"></script>
    <script>
        $(function () {
            $('#form1').fileupload({
                url: '/uploadfiletopath',
                sequentialUploads: true
            });
        });
    </script>
    <title>Title</title>
    <security:csrfMetaTags/>
</head>
<body>
<div id="form1">
    <input type="file" id="uploadfiles" name="files" data-url="/uploadfiletopath" multiple>
    <security:csrfInput/>
</div>
</body>
</html>
