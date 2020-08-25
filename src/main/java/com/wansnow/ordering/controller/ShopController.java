package com.wansnow.ordering.controller;

import com.wansnow.ordering.entity.DishList;
import com.wansnow.ordering.entity.OrderingList;
import com.wansnow.ordering.entity.Shop;
import com.wansnow.ordering.service.OrderingServiceImpl;
import com.wansnow.ordering.service.ShopServiceImpl;
import com.wansnow.ordering.utils.SnowFlake;
import io.lettuce.core.dynamic.annotation.Param;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.annotation.Resource;
import javax.servlet.http.HttpSession;

@Controller
public class ShopController {
    @Autowired
    private HttpSession session;
    @Autowired
    private ShopServiceImpl shopService;
    @Autowired
    private OrderingServiceImpl orderingService;
    @Resource(name = "shop")
    private SnowFlake shopIdGenerator;
    @Resource(name = "dish")
    private SnowFlake dishIdGenerator;

    //下面是servlet
    @RequestMapping(path = "/", method = RequestMethod.GET)
    public String index(){
        session.setAttribute("shops", shopService.getAllShops());
        return "index";
    }

    @RequestMapping(path = "/shop", method = RequestMethod.GET)
    public String shop(){
        return "shop";
    }

    @RequestMapping(path = "/shopPage", method = RequestMethod.GET)
    public String shopPage(){
        return "shopPage";
    }

    @RequestMapping(path = "/editDishListPage", method = RequestMethod.GET)
    public String editDishPage(){
        return "editDishListPage";
    }

    @RequestMapping(path = "/shopLogin", method = RequestMethod.POST, produces = "text/plain;charset=UTF-8")
    @ResponseBody
    public String login(String shopId, String pwd){
        if(shopId==null||shopId==""){
            return "店铺ID不能为空！";
        }
        if(pwd==null||pwd==""){
            return "密码不能为空！";
        }
        Shop shop = shopService.login(shopId, pwd);
        if(shop!=null&&shop.getShopId()!=null&&shop.getShopId()!=""){
            session.setAttribute("shop",shop);
            session.setAttribute("dishLists",shopService.getAllDish(shopId));
            session.setAttribute("shopOrderingList", orderingService.getAllOrderingListByShopId(shopId));
            return "登录成功！";
        }else {
            return "店铺ID或密码错误！";
        }
    }

    @RequestMapping(path = "/shopRegistryPage", method = RequestMethod.GET)
    public String registryPage(){
        return "shopRegistryPage";
    }

    @RequestMapping(path = "/shopRegistry", method = RequestMethod.POST, produces = "text/plain;charset=UTF-8")
    @ResponseBody
    public String registry(Shop shop, String pwd2){
        if(shop.getShopName()==null||shop.getShopName()==""){
            return "店铺名不能为空！";
        }
        if(shop.getPwd()==null||shop.getPwd()==""){
            return "密码不能为空！";
        }
        if(pwd2==null||pwd2==""||!pwd2.equals(shop.getPwd())){
            return "两次密码不一致！";
        }
        if(shop.getOwnerName()==null||shop.getOwnerName()==""){
            return "拥有者姓名不能为空！";
        }
        if(shop.getTel()==null||shop.getTel()==""||shop.getTel().length()!=11){
            return "电话格式不正确！";
        }
        if(shop.getAddr()==null||shop.getAddr()==""){
            return "店铺地址不能为空！";
        }
        shop.setShopId(""+shopIdGenerator.nextId());
        boolean registry = shopService.registry(shop);
        if(!registry){
            return "注册失败!";
        }
        return "注册成功！你的店铺ID是："+ shop.getShopId() +"。请牢记！";
    }

    @RequestMapping(path = "/updateDishListPage", method = RequestMethod.POST, produces = "text/plain;charset=UTF-8")
    @ResponseBody
    public void updateDishList(String dishId){
        DishList dishList = shopService.getDish(dishId);
        session.setAttribute("tempDish", dishList);
    }

    @RequestMapping(path = "/newDishList", method = RequestMethod.POST, produces = "text/plain;charset=UTF-8")
    @ResponseBody
    public void newtDishList(String shopId){
        DishList dishList = new DishList();
        dishList.setShopId(shopId);
        session.setAttribute("tempDish", dishList);
    }

    @RequestMapping(path = "/addDishList", method = RequestMethod.POST, produces = "text/plain;charset=UTF-8")
    @ResponseBody
    public String addDishList(DishList dishList){
        if(dishList.getDishName()==null||dishList.getDishName()==""){
            return "菜名不能为空！";
        }
        if(dishList.getPrice()==null||dishList.getPrice()==""){
            return "菜名不能为空！";
        }
        if(dishList.getDishImage()==null||dishList.getDishImage()==""){
            return "菜名不能为空！";
        }
        dishList.setDishId(dishIdGenerator.nextId()+"");
        if(shopService.addDishList(dishList)){
            Shop shop = (Shop) session.getAttribute("shop");
            session.setAttribute("dishLists", shopService.getAllDish(shop.getShopId()));
            return "添加成功！";
        }else {
            return "false";
        }
    }

    @RequestMapping(path = "/updateDishList", method = RequestMethod.POST, produces = "text/plain;charset=UTF-8")
    @ResponseBody
    public String updateDishList(DishList dishList){
        if(dishList.getDishName()==null||dishList.getDishName()==""){
            return "菜名不能为空！";
        }
        if(dishList.getPrice()==null||dishList.getPrice()==""){
            return "菜名不能为空！";
        }
        if(dishList.getDishImage()==null||dishList.getDishImage()==""){
            return "菜名不能为空！";
        }
        if(shopService.updateDishList(dishList)){
            Shop shop = (Shop) session.getAttribute("shop");
            session.setAttribute("dishLists", shopService.getAllDish(shop.getShopId()));
            return "修改成功！";
        }else {
            return "false";
        }
    }

    @RequestMapping(path = "/deleteDishList", method = RequestMethod.POST, produces = "text/plain;charset=UTF-8")
    @ResponseBody
    public String deleteDishList(String dishId){
        if(shopService.deleteDishList(dishId)){
            Shop shop = (Shop) session.getAttribute("shop");
            session.setAttribute("dishLists", shopService.getAllDish(shop.getShopId()));
            return "删除成功！";
        }else {
            return "删除失败！";
        }
    }

}
