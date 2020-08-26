<%--
  Created by IntelliJ IDEA.
  User: 晚落
  Date: 2020/8/23
  Time: 20:15
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ page import="com.wansnow.ordering.entity.DishList" %>
<%@ page import="java.util.List" %>
<html>
<head>
    <title>商店页面</title>
</head>
<body>
<div>
    店名：${sessionScope.shopView.getShopName()}<br>
    地址：${sessionScope.shopView.getAddr()}<br><br><br>
    菜单：<br>
    <%
        for(DishList dishList : (List<DishList>)session.getAttribute("dishView")){
    %>
    <hr>
    菜名：<%=dishList.getDishName()%><br>
    价格：<%=dishList.getPrice()%><br>
    <%
        }
    %>
    <br>
    <a href="${pageContext.request.contextPath}/orderView" >我要点餐</a>
</div>
</body>
</html>
