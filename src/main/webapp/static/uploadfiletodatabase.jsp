<%--
  Created by IntelliJ IDEA.
  User: djj
  Date: 2017/2/19
  Time: 19:57
  To change this template use File | Settings | File Templates.
--%>
<%@ taglib prefix='security' uri='http://www.springframework.org/security/tags' %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %><%--
<!DOCTYPE html><html xmlns:th="http://www.thymeleaf.org">
<html>--%>
<head>
    <meta name="_csrf" content="${_csrf.token}"/>
    <!-- default header name is X-CSRF-TOKEN -->
    <meta name="_csrf_header" content="${_csrf.headerName}"/>
    <%-- <security:csrfMetaTags/>--%>
    <script type="text/javascript" src="js/jquery-3.1.1.min.js"></script>
    <script type="text/javascript" src="js/jquery.fileupload.js"></script>
    <%--<script type="text/javascript">
       /* $("#go").click(function () {
            var token = $("meta[name='_csrf']").attr("content");
            var header = $("meta[name='_csrf_header']").attr("content");
            $("#go").ajaxSend(function (e, xhr, options) {
                xhr.setRequestHeader(header, token);
            });
        });*/
        function doit() {
            var token = $("meta[name='_csrf']").attr("content");
            var header = $("meta[name='_csrf_header']").attr("content");
            $(document).ajaxSend(function (e, xhr, options) {
                xhr.setRequestHeader(header, token);
            })
        /*}*/
    </script>--%>

    <title>Title</title>
</head>
<script>
    $(document).ready(function () {
        var token = $("meta[name='_csrf']").attr("content");
        var header = $("meta[name='_csrf_header']").attr("content");
        $(document).ajaxSend(function (e, xhr, options) {
            xhr.setRequestHeader(header, token);
        });
        $('#file').fileupload({
            dataType: 'json',
            add: function (e, data) {
                data.context = $('<button/>').text('Upload')
                    .appendTo(document.body)
                    .click(function () {
                        data.context = $('<p/>').text('Uploading...').replaceAll($(this));
                        data.submit();
                    });
            },
            done: function (e, data) {
                data.context.text('Upload finished.');
            }
        });
    })
</script>
<body>
<div>
    <input type="file" name="file" id="file" data-url="/uploadfiletodatabase"/><br/>
</div>
</body>
</html>
