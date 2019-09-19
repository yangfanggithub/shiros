package com.yf.shiro.realm;

import org.apache.shiro.authc.AuthenticationException;
import org.apache.shiro.authc.AuthenticationInfo;
import org.apache.shiro.authc.AuthenticationToken;
import org.apache.shiro.authc.SimpleAuthenticationInfo;
import org.apache.shiro.authc.credential.HashedCredentialsMatcher;
import org.apache.shiro.authz.AuthorizationInfo;
import org.apache.shiro.realm.AuthorizingRealm;
import org.apache.shiro.subject.PrincipalCollection;
import org.apache.shiro.util.ByteSource;

public class Shiro3Realm extends AuthorizingRealm{

    public Shiro3Realm(){
            //采用md5算法
            HashedCredentialsMatcher passwordMatcher = new HashedCredentialsMatcher("md5");
            //循环加密3次
            passwordMatcher.setHashIterations(3);
            //再将这个加密组件注入到我们的Realm中
            this.setCredentialsMatcher(passwordMatcher);
    }

    @Override
    protected AuthorizationInfo doGetAuthorizationInfo(PrincipalCollection principalCollection) {

        return null;
    }

    @Override
    protected AuthenticationInfo doGetAuthenticationInfo(AuthenticationToken token) throws AuthenticationException {
        String clientUsername = (String) token.getPrincipal();
        String yourInputPasswordFromWeb = new String((char[]) token.getCredentials());
        return new SimpleAuthenticationInfo(clientUsername,"d1b129656359e35e95ebd56a63d7b9e0", ByteSource.Util.bytes("salt"),getName());
    }
}
