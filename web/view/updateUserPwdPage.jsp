<%--
  Created by IntelliJ IDEA.
  User: 晚落
  Date: 2020/8/25
  Time: 9:24
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>修改用户密码</title>
    <script src="${pageContext.request.contextPath}/js/jquery.js"></script>
    <script>
        $(function(){
                $("#submit").on('click',function () {
                    $.ajax({
                        url:'${pageContext.request.contextPath}/updateUserPwd',
                        type:'post',
                        data:{
                            'email':$('#email').val(),
                            'oldPwd':$('#old_pwd').val(),
                            'newPwd':$('#new_pwd').val(),
                            'newPwd2':$('#new_pwd_2').val(),
                        },
                        success:function (data) {
                            if(data=='true'){
                                alert("密码修改成功");
                                window.location.href = '${pageContext.request.contextPath}/';
                            }else {
                                alert(data);
                                window.location.reload();
                            }
                        }
                    })
                })
            }

        );
    </script>
</head>
<body>
<div>
    <input type="hidden" value="${sessionScope.user.getEmail()}" id="email">
旧密码：<input type="password" id="old_pwd"><br>
    新密码：<input type="password" id="new_pwd"><br>
    二次输入：<input type="password" id="new_pwd_2"> <br>
    <input type="button" id="submit" value="确认">
</div>
</body>
</html>
