<%--
  Created by IntelliJ IDEA.
  User: 晚落
  Date: 2020/8/25
  Time: 15:47
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>修改店铺密码</title>
    <script src="${pageContext.request.contextPath}/js/jquery.js"></script>
    <script>
        $(function(){
                $("#submit").on('click',function () {
                    $.ajax({
                        url:'${pageContext.request.contextPath}/updateShopPwd',
                        type:'post',
                        data:{
                            'shopId':$('#shop_id').val(),
                            'oldPwd':$('#old_pwd').val(),
                            'newPwd':$('#new_pwd').val(),
                            'newPwd2':$('#new_pwd_2').val(),
                        },
                        success:function (data) {
                            if(data=='true'){
                                alert("密码修改成功");
                                window.location.href = '${pageContext.request.contextPath}/shop';
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
    <input type="hidden" value="${sessionScope.shop.getShopId()}" id="shop_id">
    旧密码：<input type="password" id="old_pwd"><br>
    新密码：<input type="password" id="new_pwd"><br>
    二次输入：<input type="password" id="new_pwd_2"> <br>
    <input type="button" id="submit" value="确认">
</div>
</body>
</html>
