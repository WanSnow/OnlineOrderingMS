<%--
  Created by IntelliJ IDEA.
  User: 晚落
  Date: 2020/8/20
  Time: 20:31
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@page  import="com.wansnow.ordering.entity.DishList" %>
<html>
<head>
    <title>编辑菜品</title>
    <script src="${pageContext.request.contextPath}/js/jquery.js"></script>
    <script>
        $(function () {
            $("#updateDishList").on('click', function () {
                $.ajax({
                    url: "${pageContext.request.contextPath}/updateDishList",
                    type: 'POST',
                    data: {'dishId': $("#dish_id").val(),
                        'shopId': $("#shop_id").val(),
                        'dishName': $("#dish_name").val(),
                        'price': $("#price").val(),
                        'dishImage': $("#dish_image").val()
                    },
                    success: function (data) {
                        if(data=="false"){
                            alert("修改失败！");
                            window.location.reload();
                        }else {
                            alert(data);
                            window.location.href = "${pageContext.request.contextPath}/shopPage";
                        }
                    }
                });
            })
        });

        $(function () {
            $("#addDishList").on('click', function () {
                $.ajax({
                    url: "${pageContext.request.contextPath}/addDishList",
                    type: 'POST',
                    data: {'dishId': $("#dish_id").val(),
                        'shopId': $("#shop_id").val(),
                        'dishName': $("#dish_name").val(),
                        'price': $("#price").val(),
                        'dishImage': $("#dish_image").val()
                    },
                    success: function (data) {
                        if(data=="false"){
                            alert("添加失败！");
                            window.location.reload();
                        }else {
                            alert(data);
                            window.location.href = "${pageContext.request.contextPath}/shopPage";
                        }
                    }
                });
            })
        });
    </script>
</head>
<body>
<div>
<%--    编辑菜品界面--%>
    <%
        DishList tempDish = (DishList) session.getAttribute("tempDish");
    %>
    菜品ID：<%=tempDish.getDishId()%><br>
    <input type="hidden" id="dish_id" value="<%=tempDish.getDishId()%>">
    店铺ID：<%=tempDish.getShopId()%><br>
    <input type="hidden" id="shop_id" value="<%=tempDish.getShopId()%>">
    菜名：<input type="text" id="dish_name" value="<%=tempDish.getDishName()%>"><br>
    价格：<input type="text" id="price" value="<%=tempDish.getPrice()%>"><br>
    图片：<input type="text" id="dish_image" value="<%=tempDish.getDishImage()%>"><br>
    <%
        if(tempDish.getDishId()!=null){
    %>
    <input type="submit" id="updateDishList" value="提交">
    <%
        }else {
    %>
    <input type="submit" id="addDishList" value="提交">
    <%}%>
    <input type="reset" value="重置">
</div>
</body>
</html>
