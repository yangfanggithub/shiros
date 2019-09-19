package com.yf.shiro.realm;

import org.apache.shiro.authc.AuthenticationException;
import org.apache.shiro.authc.AuthenticationInfo;
import org.apache.shiro.authc.AuthenticationToken;
import org.apache.shiro.authc.SimpleAuthenticationInfo;
import org.apache.shiro.authc.credential.HashedCredentialsMatcher;
import org.apache.shiro.authz.AuthorizationInfo;
import org.apache.shiro.authz.SimpleAuthorizationInfo;
import org.apache.shiro.realm.AuthorizingRealm;
import org.apache.shiro.subject.PrincipalCollection;

import java.util.Arrays;
import java.util.Collection;

public class Shiro4Realm extends AuthorizingRealm {

    public Shiro4Realm(){
        /**
         * 在插入密码的时候，需要自己进行加密，然后再把你加密的数据信息告诉shiro中的realm
         */
        //告诉shiro采用md5算法
        HashedCredentialsMatcher passwordMatcher=new HashedCredentialsMatcher("md5");
        //循环加密3次
        passwordMatcher.setHashIterations(3);
        //再将这个加密组件注入到我们的Realm中
        //在realm进行比较的时候会使用该算法进行加密
        //this.setCredentialsMatcher(passwordMatcher);
    }

    @Override
    protected AuthorizationInfo doGetAuthorizationInfo(PrincipalCollection principals) {
        String yourInputUsername = (String) principals.getPrimaryPrincipal();
        //构造一个授权凭证
        SimpleAuthorizationInfo info = new SimpleAuthorizationInfo();
        //通过你的用户名查询数据库，得到你的权限信息与角色信息。并存放到权限凭证中
        info.addRole(getYourRoleByUsernameFromDB(yourInputUsername));
        info.addStringPermissions(getYourPermissionByUsernameFromDB(yourInputUsername));
        //返回你的权限信息
        return info;
    }

    private Collection<String> getYourPermissionByUsernameFromDB(String yourInputUsername) {
        return Arrays.asList("add","delete","update","select");
    }

    private String getYourRoleByUsernameFromDB(String yourInputUsername) {
        return "admin";
    }

    @Override
    protected AuthenticationInfo doGetAuthenticationInfo(AuthenticationToken authenticationToken) throws AuthenticationException {
        //默认所有用户都登录
        String name = (String) authenticationToken.getPrincipal();
        String password = new String((char[]) authenticationToken.getCredentials());
        return new SimpleAuthenticationInfo(name,password,getName());
    }



}
