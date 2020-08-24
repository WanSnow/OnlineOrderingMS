<%--
  Created by IntelliJ IDEA.
  User: 晚落
  Date: 2020/8/18
  Time: 21:32
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>注册店铺</title>
    <script src="${pageContext.request.contextPath}/js/jquery.js"></script>
    <script>
        $(function () {
            $("#submit").on('click', function () {
                $.ajax({
                    url: "${pageContext.request.contextPath}/shopRegistry",
                    type: 'POST',
                    data: {
                        'shopId': "",
                        'pwd': $("#pwd").val(),
                        'shopName': $("#shop_name").val(),
                        'ownerName':$("#owner_name").val(),
                        'tel':$("#tel").val(),
                        'addr':$("#addr").val(),
                        'isVerify':false,
                        'pwd2':$("#pwd2").val()
                    },
                    success: function (data) {
                        if(data=="false"){
                            alert("注册失败！");
                            window.location.reload();
                        }else {
                            alert(data);
                            window.location.href = "${pageContext.request.contextPath}/shop";
                        }

                    }
                });
            })
        });

    </script>
</head>
<body>
<div>
    店铺名：<input type="text" id="shop_name"><br>
    密码：<input type="password" id="pwd"><br>
    二次密码：<input type="password" id="pwd2"><br>
    拥有者姓名：<input type="text" id="owner_name"><br>
    电话：<input type="text" id="tel"><br>
    地址：<input type="text" id="addr"><br>
    <input type="submit" id="submit" value="注册">
    <input type="reset" value="重置">
</div>
</body>
</html>
