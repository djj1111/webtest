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
                start: function (e) {
                    $("#pp").html("上传中……");
                },
                progressall: function (e, data) {
                    // $("#pp").html("");
                    var per = parseInt(data.loaded / data.total * 100, 10);
                    // $("#pp").html("上传中……"+per+"%");
                    $('.bar').css(
                        'width',
                        per + '%'
                    );

                },
                done: function (e, data) {
                    //data.context.text('Upload finished.');
                    $("#pp").html("");//清空info内容
                    $.each(data.result, function (i, item) {
                        $("#pp").append(
                            "<div>" + item + "</div><hr/>");
                    });
                }


            });
        });
    </script>

    <security:csrfMetaTags/>
</head>

<body>
<input type="file" id="file" name="files" data-url="/uploadfiletopath" multiple>
<p id="pp">hello</p>
<div id="progress">
    <div class="bar" style="width: 10%;height: 18px;background: green"></div>
</div>
</body>
</html>
