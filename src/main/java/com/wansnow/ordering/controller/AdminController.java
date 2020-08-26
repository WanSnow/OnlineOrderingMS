package com.wansnow.ordering.controller;

import com.wansnow.ordering.service.ShopServiceImpl;
import com.wansnow.ordering.service.UserServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpSession;

@Controller
public class AdminController {

    @Autowired
    HttpSession session;
    @Autowired
    UserServiceImpl userService;
    @Autowired
    ShopServiceImpl shopService;

    @RequestMapping(value = "/adminLogin", method = RequestMethod.GET)
    @ResponseBody
    public String adminLogin(String username, String password){
        if("admin".equals(username)&&"admin".equals(password)){
            session.setAttribute("allUsers", userService.getAllUsers());
            session.setAttribute("allShops", shopService.getAllShops());
            return "adminPage";
        }
        return "admin";
    }

    @RequestMapping(value = "/admin", method = RequestMethod.GET)
    public String admin(){
        return "admin";
    }

    @RequestMapping(value = "/adminPage", method = RequestMethod.GET)
    public String adminPage(){
        return "adminPage";
    }

    @RequestMapping(value = "/confirmShop", method = RequestMethod.POST, produces = "text/plain;charset=UTF-8")
    @ResponseBody
    public String confirmShop(String shopId){
        if(shopService.confirmShop(shopId)){
            session.setAttribute("allShops", shopService.getAllShops());
            return "认证成功！";
        }else {
            return "认证失败！";
        }
    }
}
