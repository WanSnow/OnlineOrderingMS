<%--
  Created by IntelliJ IDEA.
  User: 晚落
  Date: 2020/8/18
  Time: 22:22
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@page  import="com.wansnow.ordering.entity.Shop" %>
<%@page  import="com.wansnow.ordering.entity.DishList" %>
<%@page  import="com.wansnow.ordering.entity.OrderingList" %>
<%@ page import="java.util.List" %>
<html>
<head>
    <title>店铺页面</title>
    <script src="/js/jquery.js"></script>
    <script>
        $(function () {
            $(".updateDish").on('click', function () {
                $.ajax({
                    url: "${pageContext.request.contextPath}/updateDishList",
                    type: 'POST',
                    data: {'dishId': $("#dish_id").val(),
                        'shopId': ${sessionScope.shop.getShopId()},
                        "dish_name":$("#dish_name").val(),
                        "price":$("#price").val(),
                        "dishImage":$("#dish_image").val(),
                    },
                    success: function (data) {
                        alert(data);
                        window.location.reload();
                    }
                });
            })
        });

        $(function () {
            $(".deleteDish").on('click', function () {
                $.ajax({
                    url: "${pageContext.request.contextPath}/deleteDishList",
                    type: 'Post',
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
<div>
    <%
        Shop shop = (Shop) session.getAttribute("shop");
        if(!shop.isVerify()){
            out.println("你的店铺还未经过审核，请通过审核过后再登录！");
        }else {
    %>
    欢迎您！<%=shop.getOwnerName()%>店长
    <hr>
    <div>
        <div>
            <%--            浏览/修改菜单--%>
                <div>
                    <%--                浏览修改菜单--%>
                    <%
                        List<DishList> dishLists = (List<DishList>) session.getAttribute("dishLists");
                        for(int i=1;i<=dishLists.size();i++){
                    %>
                    <input type="image" src="<%=dishLists.get(i).getDishImage()%>" id="dish_image_<%=i%>" value="<%=dishLists.get(i).getDishImage()%>">
                    菜品ID：<label id="dish_id_<%=i%>"><%=dishLists.get(i).getDishId()%></label>
                    菜名：<input type="text" id="dish_name_<%=i%>" value="<%=dishLists.get(i).getDishName()%>">
                    价格：<input type="text" id="price_<%=i%>" value="<%=dishLists.get(i).getPrice()%>">
                    <a href="#" id="updateDish_<%=i%>" class="updateDish">修改</a>
                    <a href="#" id="deleteDish_<%=i%>" class="deleteDish">删除</a>
                    <%
                        }
                    %>
                </div>
                <div>
                    <%--                    增加菜品--%>
                    菜名：<input type="text" id="new_dish_id"><br>
                    价格：<br>
                    图片：<br>
                </div>
            <div>

            </div>
        </div>
        <div>
            浏览/审批订单
        </div>
        <div>
            浏览/修改店铺信息
        </div>
    </div>
    <%
        }
    %>
</div>
</body>
</html>
