<%--
  Created by IntelliJ IDEA.
  User: asus
  Date: 2020/2/8
  Time: 11:06
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Title</title>
</head>
<body>
<a href="/mvcs/user/rep">resp</a>
<button id="ajax">发送Ajax</button>
</body>
</html>
<script src="js/jquery-3.0.0.min.js"></script>
<script>
    //加载页面
    $(function () {
        $("#ajax").click(function () {
            $.ajax({
                url:"/mvcs/user/ajaxTest",
                contentType:"application/json;charset=UTF-8",
                data:'{"username:"hehe","password":"123","age":"30"}',
                dataType:"json",
                type:"post",
                success:function (data) {
                    alert(data.username);
                    alert(data.age);
                }
            });
        });
    });

</script>

