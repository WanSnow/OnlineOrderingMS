package com.wansnow.ordering.controller;

import com.wansnow.ordering.entity.User;
import com.wansnow.ordering.service.UserServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpSession;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

@Controller
public class UserController {
    @Autowired
    private HttpSession session;

    @Autowired
    UserServiceImpl userService;

    @RequestMapping(value = "/login", method = RequestMethod.POST, produces = "text/plain;charset=UTF-8")
    @ResponseBody
    public String login(String username, String password){
        if(username==null||username==""){
            return "用户名不能为空！";
        }
        if(password==null||password==""){
            return "密码不能为空！";
        }
        if(!checkEmail(username)){
            return "邮箱格式不正确！";
        }
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

    @RequestMapping(value = "/registryPage", method = RequestMethod.GET)
    public String registryPage(){
        return "registryPage";
    }

    @RequestMapping(value = "/registry", method = RequestMethod.POST, produces = "text/plain;charset=UTF-8")
    @ResponseBody
    public String registry(User user, String pwd2){
        if(!checkEmail(user.getEmail())){
            return "邮箱格式不正确！";
        }
        if(user.getPwd()==null||user.getPwd()==""){
            return "密码不能为空！";
        }
        if(pwd2==null||pwd2==""||!pwd2.equals(user.getPwd())){
            return "两次密码不一致！";
        }
        if(user.getUsername()==null||user.getUsername()==""){
            return "用户名为空！";
        }
        if(user.getRealName()==null||user.getRealName()==""){
            return "姓名不能为空！";
        }
        if(user.getTel()==null||user.getTel()==""||user.getTel().length()!=11){
            return "电话格式不正确！";
        }
        boolean isRegistry = userService.register(user);
        if(isRegistry){
            session.setAttribute("user",user);
            return "注册成功!";
        }else {
            return "false";
        }

    }

    //正则表达式
    public static boolean checkEmail(String email){
        /**
         *   ^匹配输入字符串的开始位置
         *   $结束的位置
         *   \转义字符 eg:\. 匹配一个. 字符  不是任意字符 ，转义之后让他失去原有的功能
         *   \t制表符
         *   \n换行符
         *   \\w匹配字符串  eg:\w不能匹配 因为转义了
         *   \w匹配包括字母数字下划线的任何单词字符
         *   \s包括空格制表符换行符
         *   *匹配前面的子表达式任意次
         *   .小数点可以匹配任意字符
         *   +表达式至少出现一次
         *   ?表达式0次或者1次
         *   {10}重复10次
         *   {1,3}至少1-3次
         *   {0,5}最多5次
         *   {0,}至少0次 不出现或者出现任意次都可以 可以用*号代替
         *   {1,}至少1次  一般用+来代替
         *   []自定义集合     eg:[abcd]  abcd集合里任意字符
         *   [^abc]取非 除abc以外的任意字符
         *   |  将两个匹配条件进行逻辑“或”（Or）运算
         *   [1-9] 1到9 省略123456789
         *    邮箱匹配 eg: ^[a-zA-Z_]{1,}[0-9]{0,}@(([a-zA-z0-9]-*){1,}\.){1,3}[a-zA-z\-]{1,}$
         *
         */
        String RULE_EMAIL = "^\\w+((-\\w+)|(\\.\\w+))*\\@[A-Za-z0-9]+((\\.|-)[A-Za-z0-9]+)*\\.[A-Za-z0-9]+$";
        //正则表达式的模式 编译正则表达式
        Pattern p = Pattern.compile(RULE_EMAIL);
        //正则表达式的匹配器
        Matcher m = p.matcher(email);
        //进行正则匹配\
        return m.matches();
    }
}
