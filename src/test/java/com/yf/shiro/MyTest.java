package com.yf.shiro;

import org.apache.shiro.SecurityUtils;
import org.apache.shiro.authc.AuthenticationException;
import org.apache.shiro.authc.UsernamePasswordToken;
import org.apache.shiro.config.IniSecurityManagerFactory;
import org.apache.shiro.crypto.hash.Md5Hash;
import org.apache.shiro.mgt.SecurityManager;
import org.apache.shiro.subject.Subject;
import org.apache.shiro.util.Factory;
import org.junit.After;
import org.junit.Assert;
import org.junit.Test;

/**
 * @author yang fang
 * @version 0.0.1
 * @date 2019-08-12 14:16
 */
public class MyTest {


    @Test
    public void test01(){
        //1、获取SecurityManager工厂，此处使用Ini配置文件初始化SecurityManager
        //使用ini初始化SecurityManager工厂
        Factory<SecurityManager> factory=new IniSecurityManagerFactory("classpath:shiro.ini");
        //2、得到SecurityManager实例 并绑定给SecurityUtils
        //通过工厂创建一个实例
        SecurityManager securityManager = factory.getInstance();
        //设置
        SecurityUtils.setSecurityManager(securityManager);
        //3、得到Subject及创建用户名/密码身份验证Token（即用户身份/凭证）
        Subject subject = SecurityUtils.getSubject();
        UsernamePasswordToken token = new UsernamePasswordToken("yangfang", "123456");

        try {
            //4、登录，即身份验证
            subject.login(token);
        } catch (AuthenticationException e) {
            //5、身份验证失败
            e.printStackTrace();
        }

        Assert.assertEquals(true, subject.isAuthenticated()); //断言用户已经登录

        //6、退出
        subject.logout();

    }


    @Test
    public void test02(){
        Factory<SecurityManager> factory=new IniSecurityManagerFactory("classpath:shiro2.ini");
        SecurityManager instance = factory.getInstance();
        SecurityUtils.setSecurityManager(instance);
        Subject subject = SecurityUtils.getSubject();

        UsernamePasswordToken token=new UsernamePasswordToken("yangfang","123456");
        subject.login(token);

        Assert.assertEquals(true, subject.isAuthenticated());

    }


    @Test
    public void test03(){
        Factory<SecurityManager> securityManagerFactory=new IniSecurityManagerFactory("classpath:shiro3.ini");
        SecurityManager instance = securityManagerFactory.getInstance();
        SecurityUtils.setSecurityManager(instance);
        Subject subject = SecurityUtils.getSubject();

        UsernamePasswordToken token=new UsernamePasswordToken("yangfang","123456");
        subject.login(token);

        Assert.assertEquals(true,subject.isAuthenticated());

    }


    @After
    public void after(){
        System.out.println("程序结束");
    }


    @Test
    public void jiami(){
        String password = "123456";
        Md5Hash md5Hash = new Md5Hash(password);
        System.out.println(md5Hash);
    }

    @Test
    public void jiamiSalt(){
        String password = "123456";
        Md5Hash md5Hash = new Md5Hash(password,"salt");
        System.out.println(md5Hash);
    }

    @Test
    public void jiamiSaltFor(){
        String password = "123456";
        Md5Hash md5Hash = new Md5Hash(password,"salt",3);
        System.out.println(md5Hash);
    }








}
