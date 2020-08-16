<%--
  Created by IntelliJ IDEA.
  User: 晚落
  Date: 2020/8/16
  Time: 15:50
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>管理员登录界面</title>
    <script src="/js/jquery.js"></script>
    <script>
        $(function () {
            $("#submit").on('click', function () {
                $.ajax({
                    url: "/OnlineOrderingMS/adminLogin",
                    type: 'GET',
                    data: {'username': $("#username").val(), 'password': $("#password").val()},
                    success: function (data) {
                        if(data=="adminPage"){
                            alert("登录成功！");
                        }else {
                            alert("用户名或密码错误！");
                        }
                        window.location.href = data;
                    }
                });
            })
        });

    </script>
</head>
<body>
用户名<input id="username" type="text"/>
密码<input id="password" type="password"/>
<input id="submit" type="submit" value="登录"/>
</body>
</html>
