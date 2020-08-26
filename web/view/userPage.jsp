<%--
  Created by IntelliJ IDEA.
  User: 晚落
  Date: 2020/8/24
  Time: 14:56
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ page import="com.wansnow.ordering.entity.User" %>
<%@ page import="java.util.List" %>
<%@ page import="com.wansnow.ordering.entity.OrderingList" %>
<html>
<head>
    <title>个人中心</title>
    <script src="${pageContext.request.contextPath}/js/jquery.js"></script>
    <script>
        $(function(){
            $("#update_user_info").on('click',function () {
                $.ajax({
                    url:'${pageContext.request.contextPath}/updateUserInfo',
                    type:'post',
                    data:{
                        'email':$('#email').val(),
                        'pwd':'',
                        'username':$('#username').val(),
                        'realName':$('#real_name').val(),
                        'tel':$('#tel').val()
                    },
                    success:function (data) {
                        alert(data);
                        window.location.reload();
                    }
                })
            })
        });

    </script>
</head>
<body>
<%
  User user = (User) session.getAttribute("user");
    List<OrderingList> orderingLists = (List<OrderingList>) session.getAttribute("userOrdering");
%>
<div>
    <a href="${pageContext.request.contextPath}/">首页</a>
</div>
<div>
<%--    个人信息--%>
    邮箱：<%=user.getEmail()%><br>
    <input type="hidden" id="email" value="<%=user.getEmail()%>">
    用户名：<input type="text" id="username" value="<%=user.getUsername()%>"><br>
    姓名：<input type="text" id="real_name" value="<%=user.getRealName()%>"> <br>
    电话：<input type="tel" id="tel" value="<%=user.getTel()%>"><br>
    <input type="submit" value="修改个人信息" id="update_user_info"><br>
    <a href="updateUserPwdPage">修改密码</a>
</div>
<hr>
<div>
<%--    订单--%>
    <h3>订单</h3>
    <%
        for(OrderingList orderingList:orderingLists){
    %>
    订单ID：<%=orderingList.getOrderingId()%><br>
    餐厅：<%=orderingList.getShopName()%><br>
    姓名：<%=orderingList.getRealName()%><br>
    电话：<%=orderingList.getTel()%><br>
    菜单：<br>
    <%=orderingList.getOrderList()%><br>
    餐厅是否确认:<%=orderingList.isOrdering()%>
    <hr>
    <%
        }
    %>
</div>
</body>
</html>
