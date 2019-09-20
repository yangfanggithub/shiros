package com.shiro.shirospringboot.controller;

import org.apache.shiro.SecurityUtils;
import org.apache.shiro.authc.AuthenticationException;
import org.apache.shiro.authc.IncorrectCredentialsException;
import org.apache.shiro.authc.UnknownAccountException;
import org.apache.shiro.authc.UsernamePasswordToken;
import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.apache.shiro.subject.Subject;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class LoginController {

    @GetMapping("/login")
    public String login(String username,String password){
        UsernamePasswordToken token=new UsernamePasswordToken(username,password);
        Subject subject = SecurityUtils.getSubject();
        try {
            //主体提交登录请求到SecurityManager
            subject.login(token);
        }catch (IncorrectCredentialsException ice){
            ice.printStackTrace();
        }catch(UnknownAccountException uae){
            uae.printStackTrace();
        }catch(AuthenticationException ae){
            ae.printStackTrace();
        }
        if(subject.isAuthenticated()){
            System.out.println("认证成功");
            return "登录成功";
        }else{
            return "请登录";
        }
    }


    @GetMapping("/index")
    public String index(){
        return "index page";
    }

    @RequiresPermissions("add")
    @GetMapping("add")
    public String add(){
        return "add";
    }

    @RequiresPermissions("delete")
    @GetMapping("delete")
    public String delete(){
        return "delete";
    }


}
