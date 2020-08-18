package com.wansnow.ordering.controller;

import com.wansnow.ordering.entity.Shop;
import com.wansnow.ordering.service.ShopServiceImpl;
import com.wansnow.ordering.utils.SnowFlake;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
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

    @Resource(name = "shop")
    private SnowFlake shopIdGenerator;

    @RequestMapping(path = "/shop", method = RequestMethod.GET)
    public String shop(){
        return "shop";
    }

    @RequestMapping(path = "/shopPage", method = RequestMethod.GET)
    public String shopPage(){
        return "shopPage";
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
}
