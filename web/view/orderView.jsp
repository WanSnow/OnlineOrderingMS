<%--
  Created by IntelliJ IDEA.
  User: 晚落
  Date: 2020/8/24
  Time: 7:48
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ page import="com.wansnow.ordering.entity.Shop" %>
<%@ page import="com.wansnow.ordering.entity.User" %>
<%@ page import="com.wansnow.ordering.entity.DishList" %>
<%@ page import="java.util.List" %>
<html>
<head>
    <title>订餐页面</title>
    <script src="${pageContext.request.contextPath}/js/jquery.js"></script>
    <script>
        var dishList;
        $(function () {
            var temp = $('#dishes').val()
            temp = temp.replace(/\'/g,"\"");
            dishList = $.parseJSON(temp);
        })

        function reduce(dishName,dishId) {
            var count = Number(document.getElementById(dishId).value) ;
            if(count > 0){
                document.getElementById(dishId).value = count-1;
                dishList[dishName] = count-1;
            }
        }

        function add(dishName,dishId) {
            var count = Number(document.getElementById(dishId).value) ;
            if(count < 99){
                document.getElementById(dishId).value = count+1;
                dishList[dishName] = count+1;
            }
        }

        $(function () {
            $("#submit").on("click",function () {
                $.ajax({
                    url: "${pageContext.request.contextPath}/addOrderingList",
                    type: 'POST',
                    data: {
                        'orderingId':'',
                        'shopId':$('#shop_id').val(),
                        'shopName':$('#shop_name').val(),
                        'email':$('#email').val(),
                        'realName':$('#real_name').val(),
                        'appointmentTime':$('#date').val()+','+$('#time').val(),
                        'tel':$('#tel').val(),
                        'orderList':JSON.stringify(dishList),
                        'isOrdering':""
                    },
                    success: function (data) {
                        alert(data);
                        window.location.href = ${pageContext.request.contextPath}/;
                    }
                });
            })
        })
    </script>
</head>
<body>
<%
    User user = (User)session.getAttribute("user");
    if(user==null){
        out.print("请先登录！");
    }else {
    Shop shop = (Shop)session.getAttribute("shopView");
    List<DishList> dishLists = (List<DishList>)session.getAttribute("dishView");
    String dishes="{'name':'"+user.getRealName()+"'";
%>
<div>
    <input type="hidden" id = "shop_id" value="<%=shop.getShopId()%>">
    <input type="hidden" id = "email" value="<%=user.getEmail()%>"><br>
    店名:<%=shop.getShopName()%><br>
    <input type="hidden" id = "shop_name" value="<%=shop.getShopName()%>">
    时间：<input type="date" id="date"><input type="time" id="time"><br>
    订餐人姓名：<input type="text" id="real_name" value="<%=user.getRealName()%>"><br>
    订餐人电话 ：<input type="tel" id="tel" value="<%=user.getTel()%>"><br>
    菜单：<br>
    <div id="dish_list">
        <%
            for(DishList dishList:dishLists){
        %>
        <%=dishList.getDishName()%>：<input type="button" onclick="reduce('<%=dishList.getDishName()%>','<%=dishList.getDishId()%>')" value="-">
        <input type="text" id="<%=dishList.getDishId()%>" value="0">
        <input type="button" onclick="add('<%=dishList.getDishName()%>','<%=dishList.getDishId()%>')" value="+"><br>
        <input type="hidden" id="">
        <%
                dishes+=",'"+dishList.getDishName()+"':0";
            }
            dishes+="}";
        %>
        <input type="hidden" id="dishes" value="<%=dishes%>">
    </div>
    <input type="submit" id="submit" value="提交订单">
</div>
<%
    }
%>
</body>
</html>
