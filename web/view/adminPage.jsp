<%--
  Created by IntelliJ IDEA.
  User: 晚落
  Date: 2020/8/16
  Time: 15:51
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ page import="com.wansnow.ordering.entity.Shop" %>
<%@ page import="com.wansnow.ordering.entity.User" %>
<%@ page import="java.util.List" %>
<html>
<head>
    <title>管理界面</title>
    <script src="${pageContext.request.contextPath}/js/jquery.js"></script>
    <script>
        function confirmShop(shop_id) {
            $(function(){$.ajax({
                url: "${pageContext.request.contextPath}/confirmShop",
                type: 'POST',
                data:{'shopId':shop_id},
                success: function (data) {
                    alert(data)
                    window.location.reload();
                }
            })
            });
        }
    </script>
</head>
<body>
<%
    List<User> users = (List<User>) session.getAttribute("allUsers");
    List<Shop> shops = (List<Shop>) session.getAttribute("allShops");
%>
<div>
    <%--    用户管理界面--%>
    <h3>用户：</h3>
    <hr>
    <%
        for(User user:users){
    %>
    用户名：<%=user.getUsername()%><br>
    邮箱：<%=user.getEmail()%><br>
    姓名：<%=user.getRealName()%><br>
    密码：<%=user.getPwd()%><br>
    电话：<%=user.getTel()%><br>
    <hr>
    <%
        }
    %>
</div>
<div>
    <%--    店铺管理界面--%>
    <h3>店铺：</h3>
    <hr>
    <%
        for(Shop shop:shops){
    %>
    店铺ID:<%=shop.getShopId()%>
    店名：<%=shop.getShopName()%><br>
    密码：<%=shop.getPwd()%><br>
    店主：<%=shop.getOwnerName()%><br>
    电话：<%=shop.getTel()%><br>
    地址：<%=shop.getAddr()%><br>
    是否审核：<%=shop.isVerify()%><br>
    <%
        if(!shop.isVerify()){
    %>
    <input type="button" value="通过审核" onclick="confirmShop('<%=shop.getShopId()%>')">
    <%
            }
        out.print("<hr>");
        }
    %>
</div>
</body>
</html>
