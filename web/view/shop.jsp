<%--
  Created by IntelliJ IDEA.
  User: 晚落
  Date: 2020/8/18
  Time: 21:02
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>店铺登录界面</title>
    <script src="/js/jquery.js"></script>
    <script>
        $(function () {
            $("#submit").on('click', function () {
                $.ajax({
                    url: "${pageContext.request.contextPath}/shopLogin",
                    type: 'POST',
                    data: {'shopId': $("#shop_id").val(), 'pwd': $("#pwd").val()},
                    success: function (data) {
                        alert(data);
                        window.location.href = "${pageContext.request.contextPath}/shopPage";
                    }
                });
            })
        });
    </script>
</head>
<body>
<div>
    店铺ID:<input type="text" id="shop_id"><br>
    密码：<input type="password" id="pwd"> <br>
    <input type="submit" id="submit" value="登录"><br>
    <a href="${pageContext.request.contextPath}/shopRegistryPage">注册</a>
</div>
</body>
</html>
