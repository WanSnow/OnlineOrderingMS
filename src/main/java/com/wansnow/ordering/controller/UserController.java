package com.wansnow.ordering.controller;

import com.wansnow.ordering.entity.User;
import com.wansnow.ordering.service.UserServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpSession;

@Controller
public class UserController {
    @Autowired
    private HttpSession session;

    @Autowired
    UserServiceImpl userService;

    @RequestMapping(value = "/login", method = RequestMethod.POST, produces = "text/plain;charset=UTF-8")
    @ResponseBody
    public String login(String username, String password){
        User user = userService.login(username, password);
        if(user!=null&&user.getEmail()!=null&&user.getEmail()!=""){
            session.setAttribute("user",user);
            return "登录成功！";
        }else {
            return "用户名或密码错误！";
        }
    }

    @RequestMapping(value = "/logout", method = RequestMethod.GET, produces = "text/plain;charset=UTF-8")
    @ResponseBody
    public String logout(){
        session.removeAttribute("user");
        return "已退出登录!";
    }
}
