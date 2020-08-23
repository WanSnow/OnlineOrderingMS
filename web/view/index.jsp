<%--
  Created by IntelliJ IDEA.
  User: 晚落
  Date: 2020/8/16
  Time: 11:17
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ page import="com.wansnow.ordering.entity.User" %>
<%@ page import="com.wansnow.ordering.entity.Shop" %>
<%@ page import="com.wansnow.ordering.service.ShopServiceImpl" %>
<%@ page import="java.util.List" %>
<%@ page import="org.springframework.beans.factory.annotation.Autowired" %>
<%@ page import="com.wansnow.ordering.dao.ShopDao" %>
<%@ page import="com.wansnow.ordering.dao.impl.ShopDaoImpl" %>
<html>
<head>
  <title>网上订餐管理系统</title>
  <script src="/js/jquery.js"></script>
  <script>
    $(function () {
      $("#btn-login").on('click', function () {
        $.ajax({
          url: "${pageContext.request.contextPath}/login",
          type: 'POST',
          data: {'username': $("#user").val(), 'password': $("#pwd").val()},
          success: function (data) {
            alert(data);
            window.location.reload();
          }
        });
      })
    });

    $(function () {
      $("#logout").on('click', function () {
        $.ajax({
          url: "${pageContext.request.contextPath}/logout",
          type: 'GET',
          success: function (data) {
            alert(data);
            window.location.reload();
          }
        });
      })
    });
  </script>
</head>
<body>
<%--  头部：用户登录注册--%>
<div>
  <%
    List<Shop> shops = (List<Shop>) session.getAttribute("shops");
    User user = (User)session.getAttribute("user");
    if(user == null) {
  %>
  用户：<input type="email" id="user" placeholder="邮箱">密码：<input type="password" id="pwd" placeholder="密码">
  <a href="${pageContext.request.contextPath}/registryPage" id="btn-register">注册</a>
  <a href="#" id="btn-login">登陆</a>
  <%
  } else {
  %>
  您好,<%= user.getUsername() %>。
  <a href="logout" id="logout">退出登录</a>
  <%
    }
  %>
  <a href="${pageContext.request.contextPath}/shop" >查看店铺</a>
</div>
<hr>
<%--  主屏：--%>
<div id="shopView">
<%--  浏览店铺--%>
  <%
    for(Shop shop:shops){
  %>
  <a href="${pageContext.request.contextPath}/shopView/<%=shop.getShopId()%>"><%=shop.getShopName()%></a><hr>
  <%
    }
  %>
</div>
</body>
</html>
