package com.yjh.service;

import com.yjh.pojo.Role;
import com.yjh.pojo.User;
import org.apache.shiro.authc.*;
import org.apache.shiro.authz.AuthorizationInfo;
import org.apache.shiro.authz.SimpleAuthorizationInfo;
import org.apache.shiro.realm.AuthorizingRealm;
import org.apache.shiro.subject.PrincipalCollection;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.beans.SimpleBeanInfo;
import java.util.List;

/**
 * Created by YJH on 2015/9/17.
 */
@Service
@Transactional
public class MyShiro extends AuthorizingRealm {

    @Autowired
    private IUserService userService;


    /**
     * 权限认证
     *
     * @param principalCollection
     * @return
     */
    @Override
    protected AuthorizationInfo doGetAuthorizationInfo(PrincipalCollection principalCollection) {

        //获取登录时的用户名
        String loginName = (String) principalCollection.fromRealm(getName()).iterator().next();

        User user = userService.findByName(loginName);

        if (null != user) {
            //权限信息对象info,用来存放查出的用户的所有的角色（role）及权限（permission）
            SimpleAuthorizationInfo info = new SimpleAuthorizationInfo();
            //用户的角色集合
            info.setRoles(user.getRolesName());

            return info;
        }

        return null;
    }

    /**
     * 登录认证
     *
     * @param authenticationToken
     * @return
     * @throws AuthenticationException
     */
    @Override
    protected AuthenticationInfo doGetAuthenticationInfo(AuthenticationToken authenticationToken) throws AuthenticationException {
        //UsernamePasswordToken对象用来存放提交的登录信息
        UsernamePasswordToken token = (UsernamePasswordToken) authenticationToken;

        //检查出是否有此用户
        User user = userService.findByName(token.getUsername());

        if (null != user) {
            //若存在，放入登录认证info中
            return new SimpleAuthenticationInfo(user.getUsername(), user.getPassword(), getName());
        }

        return null;
    }
}
