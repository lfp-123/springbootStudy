<%--
  Created by IntelliJ IDEA.
  User: asus
  Date: 2020/2/8
  Time: 16:32
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>文件上传</title>
</head>
<body>
<form action="/mvcs/file/fileUpload" method="post"enctype="multipart/form-data">
<p>选择文件 ：<input type="file" name="upload"></p>
    <input type="submit">
</form>
</body>
</html>
