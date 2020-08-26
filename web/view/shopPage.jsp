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
    <script src="${pageContext.request.contextPath}/js/jquery.js"></script>
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

        function confirmOrderingList(ordering_list_id) {
            $(function(){$.ajax({
                url: "${pageContext.request.contextPath}/confirmOrderingList",
                type: 'POST',
                data:{'orderingListId':ordering_list_id},
                success: function (data) {
                    alert(data)
                    window.location.reload();
                }
            })
            });
        }

        $(function(){
            $("#update_shop_info").on('click',function () {
                $.ajax({
                    url:'${pageContext.request.contextPath}/updateShopInfo',
                    type:'post',
                    data:{
                        'shopId':$('#shop_id').val(),
                        'pwd':'',
                        'shopName':$('#shop_name').val(),
                        'ownerName':$('#owner_name').val(),
                        'tel':$('#tel').val(),
                        'addr':$('#addr').val(),
                        'isVerify':true
                    },
                    success:function (data) {
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
            <h3>菜单</h3>
            <hr>
            <div>
                <%--                浏览修改菜单--%>
                <%
                    List<DishList> dishLists = (List<DishList>) session.getAttribute("dishLists");
                    for(int i=0;i<dishLists.size();i++){
                %>
                <input type="image" src="<%=dishLists.get(i).getDishImage()%>"><br>
                <input type="hidden" id="dish_image_<%=i%>" value="<%=dishLists.get(i).getDishImage()%>">
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
        <hr>
        <h3>订单</h3>
        <hr>
        <div>
            <%--            浏览/审批订单--%>
            <%
                List<OrderingList> orderingLists = (List<OrderingList>) session.getAttribute("shopOrderingList");
                for(OrderingList orderingList:orderingLists){
            %>
            订单ID：<%=orderingList.getOrderingId()%>
            <input type="hidden" id="ordering_id" value="<%=orderingList.getOrderingId()%>"><br>
            预定时间：<%=orderingList.getAppointmentTime()%><br>
            订单人：<%=orderingList.getRealName()%><br>
            订单人电话：<%=orderingList.getTel()%><br>
            订单内容：<%=orderingList.getOrderList()%><br>
            是否已确认：<%=orderingList.isOrdering()%><br>
            <%
                if(!orderingList.isOrdering()){
            %>
            <input type="button" value="确认订单" onclick="confirmOrderingList('<%=orderingList.getOrderingId()%>')">
            <%
                }
            %>
            <hr>
            <%
                }
            %>

        </div>
        <div>
            <%--            浏览/修改店铺信息--%>
            <h3>店铺信息：</h3>
            <hr>
            <input type="hidden" id="shop_id" value="<%=shop.getShopId()%>">
            店铺名：<input type="text" id="shop_name" value="<%=shop.getShopName()%>"><br>
            店主：<input type="text" id="owner_name" value="<%=shop.getOwnerName()%>"><br>
            电话：<input type="text" id="tel" value="<%=shop.getTel()%>"><br>
            地址：<input type="text" id="addr" value="<%=shop.getAddr()%>"><br>
            <input type="submit" value="修改店铺信息" id="update_shop_info"><br>
            <a href="updateShopPwdPage">修改密码</a>
        </div>
    </div>
    <%
        }
    %>
</div>
</body>
</html>
