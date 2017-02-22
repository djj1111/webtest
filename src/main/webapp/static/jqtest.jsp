<%--
  Created by IntelliJ IDEA.
  User: djj
  Date: 2017/2/22
  Time: 17:35
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
        /*$(document).ready(function () {
         $("#uploadfiles").fileupload({
         url: "/uploadfiletopath",
         sequentialUploads: true
         }
         );
         }

         );*/
        $(document).ready(function () {
            var token = $("meta[name='_csrf']").attr("content");
            var header = $("meta[name='_csrf_header']").attr("content");
            $(document).ajaxSend(function (e, xhr, options) {
                xhr.setRequestHeader(header, token);
            });
            $('#file').fileupload({
                //dataType: 'json',
                /*add: function (e, data) {
                 data.context = $('<button/>').text('Upload')
                 .appendTo(document.body)
                 .click(function () {
                 data.context = $('<p/>').text('Uploading...').replaceAll($(this));
                 data.submit();
                 });
                 },}*/
                done: function (e, data) {
                    //data.context.text('Upload finished.');
                    $("p").html("");//清空info内容
                    $.each(data.comments, function (i, item) {
                        $("p").append(
                            "<div>" + item.code + "</div><hr/>");
                    });
                }

            });
        });
    </script>

    <security:csrfMetaTags/>
</head>

<body>
<input type="file" id="file" name="files" data-url="/uploadfiletopath" multiple>
<p></p>
</body>
</html>
