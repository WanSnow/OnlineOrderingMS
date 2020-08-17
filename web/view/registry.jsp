<%--
  Created by IntelliJ IDEA.
  User: 晚落
  Date: 2020/8/17
  Time: 20:53
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>注册</title>
    <script src="/js/jquery.js"></script>
    <script>
        $(function () {
            $("#submit").on('click', function () {
                $.ajax({
                    url: "${pageContext.request.contextPath}/registry",
                    type: 'POST',
                    data: {'email': $("#email").val(),
                        'pwd': $("#pwd").val(),
                        'username':$("#username").val(),
                        'realName':$("#name").val(),
                        'tel':$("#tel").val(),
                        'pwd2':$("#pwd2").val()
                    },
                    success: function (data) {
                        if(data=="false"){
                            alert("注册失败！");
                            window.location.reload();
                        }else {
                            alert(data);
                            window.location.href = "${pageContext.request.contextPath}";
                        }

                    }
                });
            })
        });

    </script></head>
<body>
<div>
    邮箱：<input type="text" id="email"><br>
    密码：<input type="password" id="pwd"><br>
    二次密码：<input type="password" id="pwd2"><br>
    用户名：<input type="text" id="username"><br>
    姓名：<input type="text" id="name"><br>
    电话：<input type="text" id="tel"><br>
    <input type="submit" id="submit" value="注册">
    <input type="reset" value="重置">
</div>
</body>
</html>
