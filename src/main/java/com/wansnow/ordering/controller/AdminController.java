package com.wansnow.ordering.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
public class AdminController {
    @RequestMapping(value = "/adminLogin", method = RequestMethod.GET)
    @ResponseBody
    public String adminLogin(String username, String password){
        if("admin".equals(username)&&"admin".equals(password)){
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
}
