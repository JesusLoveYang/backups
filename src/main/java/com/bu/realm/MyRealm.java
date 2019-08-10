package com.bu.realm;

import com.bu.entity.User;
import com.bu.service.UserService;
import org.apache.shiro.authc.AuthenticationException;
import org.apache.shiro.authc.AuthenticationInfo;
import org.apache.shiro.authc.AuthenticationToken;
import org.apache.shiro.authc.SimpleAuthenticationInfo;
import org.apache.shiro.authz.AuthorizationInfo;
import org.apache.shiro.realm.AuthorizingRealm;
import org.apache.shiro.subject.PrincipalCollection;
import org.springframework.beans.factory.annotation.Autowired;

/**
 * 自定义realm，由它来与我们的数据库打交道
 */
public class MyRealm extends AuthorizingRealm {

    @Autowired
    private UserService userService;

    @Override
    protected AuthorizationInfo doGetAuthorizationInfo(PrincipalCollection principalCollection) {
        return null;
    }

    // 覆盖认证的方法，对前端输入的用户 进行认证
    @Override
    protected AuthenticationInfo doGetAuthenticationInfo(AuthenticationToken authenticationToken) throws AuthenticationException {
        System.out.println("你以进入myrealm");

        // 具体逻辑
        // 1、通过getPrincipal()方法，获取用户名
        String username = (String) authenticationToken.getPrincipal();
        // 2、通过service层获取用户信息
        User user = userService.queryUser(username);
        // 判断非空
        if (user == null) {
            return null;
        }

        AuthenticationInfo authenticationInfo = new SimpleAuthenticationInfo(user.getUserName(), user.getUserPassword(), "myRealm");
        return authenticationInfo;
    }

}
