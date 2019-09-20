package com.shiro.shirospringboot.realm;

import org.apache.shiro.authc.AuthenticationException;
import org.apache.shiro.authc.AuthenticationInfo;
import org.apache.shiro.authc.AuthenticationToken;
import org.apache.shiro.authc.SimpleAuthenticationInfo;
import org.apache.shiro.authz.AuthorizationInfo;
import org.apache.shiro.authz.SimpleAuthorizationInfo;
import org.apache.shiro.realm.AuthorizingRealm;
import org.apache.shiro.subject.PrincipalCollection;

import java.util.Arrays;
import java.util.HashSet;
import java.util.Set;

public class CustomRealm extends AuthorizingRealm {

    /*
        做授权
     */
    @Override
    protected AuthorizationInfo doGetAuthorizationInfo(PrincipalCollection principalCollection) {
        SimpleAuthorizationInfo authorizationInfo=new SimpleAuthorizationInfo();
        //查询所有角色
        Set<String> role=new HashSet<>();
        role.add("mather");
        authorizationInfo.setRoles(role);
        //查询所有授权
        authorizationInfo.addStringPermissions(Arrays.asList("add"));
        return authorizationInfo;
    }

    /*
        做验证
     */
    @Override
    protected AuthenticationInfo doGetAuthenticationInfo(AuthenticationToken authenticationToken) throws AuthenticationException {
        String username = (String) authenticationToken.getPrincipal();
        String password = new String((char[]) authenticationToken.getCredentials());
        //所有用户都通过
        SimpleAuthenticationInfo authenticationInfo=new SimpleAuthenticationInfo(username,password,getName());
        return authenticationInfo;
    }


}
