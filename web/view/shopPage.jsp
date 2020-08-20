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

        function updateDish(dishId) {
            $(function(){$.ajax({
                url: "${pageContext.request.contextPath}/updateDishListPage",
                type: 'POST',
                data: {'dishId': dishId
                },
                success: function () {
                    window.location.href = "${pageContext.request.contextPath}/editDishListPage";
                }
            })
            });

        }

        function addDish(shopId) {
            $(function(){$.ajax({
                url: "${pageContext.request.contextPath}/newDishList",
                type: 'POST',
                data:{'shopId':shopId},
                success: function () {
                    window.location.href = "${pageContext.request.contextPath}/editDishListPage";
                }
            })
            });
        }

        function deleteDish(dishId) {
            $(function(){$.ajax({
                url: "${pageContext.request.contextPath}/deleteDishList",
                type: 'POST',
                data:{'dishId':dishId},
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
                    for(int i=0;i<dishLists.size();i++){
                %>
                <input type="image" src="<%=dishLists.get(i).getDishImage()%>"><br>
                    <input type="hidden" id="dish_image_<%=i%>" value="<%=dishLists.get(i).getDishId()%>">
                菜品ID：<%=dishLists.get(i).getDishId()%><br>
                    <input type="hidden" id="dish_id_<%=i%>" value="<%=dishLists.get(i).getDishId()%>">
                菜名：<%=dishLists.get(i).getDishName()%><br>
                    <input type="hidden" id="dish_name_<%=i%>" value="<%=dishLists.get(i).getDishName()%>">
                价格：<%=dishLists.get(i).getPrice()%><br>
                    <input type="hidden" id="price_<%=i%>" value="<%=dishLists.get(i).getPrice()%>">
                <input type="submit" id="update_dish_<%=i%>" value="修改" onclick="updateDish('<%=dishLists.get(i).getDishId()%>')">
                <input type="submit" id="delete_dish_<%=i%>" value="删除" onclick="deleteDish('<%=dishLists.get(i).getDishId()%>')">
                    <hr>
                <%
                    }
                %>
            </div>
            <div>
                <%--                    增加菜品--%>
                <input type="button" id="add_dish" value="增加菜品" onclick="addDish('<%=shop.getShopId()%>')">
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
