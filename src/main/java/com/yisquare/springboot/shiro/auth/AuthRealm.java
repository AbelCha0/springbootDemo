package com.yisquare.springboot.shiro.auth;

import com.yisquare.springboot.common.APIResponse;
import com.yisquare.springboot.common.constraint.Role;
import com.yisquare.springboot.pojo.SysToken;
import com.yisquare.springboot.pojo.User;
import com.yisquare.springboot.service.ShiroService;
import com.yisquare.springboot.service.UserService;
import org.apache.shiro.authc.*;
import org.apache.shiro.authz.AuthorizationInfo;
import org.apache.shiro.authz.SimpleAuthorizationInfo;
import org.apache.shiro.realm.AuthorizingRealm;
import org.apache.shiro.subject.PrincipalCollection;
import org.springframework.beans.factory.annotation.Autowired;

import java.time.LocalDateTime;

public class AuthRealm extends AuthorizingRealm {
    @Autowired
    private ShiroService shiroService;

    @Autowired
    private UserService userService;



    @Override
    public boolean supports(AuthenticationToken  authenticationToken){
        return authenticationToken instanceof AuthToken;
    }

    @Override
    protected AuthorizationInfo doGetAuthorizationInfo(PrincipalCollection principals) {
        //1. 从 PrincipalCollection 中来获取登录用户的信息
        User user = (User) principals.getPrimaryPrincipal();
        //2.添加角色和权限
        SimpleAuthorizationInfo simpleAuthorizationInfo = new SimpleAuthorizationInfo();
        int roleID = shiroService.getRoleID(user.getUserCode());
        switch (roleID) {
            case  0:
                simpleAuthorizationInfo.addRole(Role.SUPERADMIN.getRoleName());

                break;
            case 1:
                simpleAuthorizationInfo.addRole(Role.SYSADMIN.getRoleName());

                break;
            case 2:
                simpleAuthorizationInfo.addRole(Role.SYSOWNER.getRoleName());
                break;
            case 3:
                simpleAuthorizationInfo.addRole(Role.SYSMEMBER.getRoleName());
                break;
        }
        return simpleAuthorizationInfo;
    }

    @Override
    protected AuthenticationInfo doGetAuthenticationInfo(AuthenticationToken token) throws AuthenticationException {
         //获取token，既前端传入的token
        String accessToken = (String) token.getPrincipal();
        //1. 根据accessToken，查询用户信息
        SysToken tokenEntity = shiroService.findByToken(accessToken);
        //2. token失效
        if (tokenEntity == null || tokenEntity.getExpireDateTime().isBefore(LocalDateTime.now())) {
            throw new IncorrectCredentialsException("token失效，请重新登录");
        }
        //3. 调用数据库的方法, 从数据库中查询 username 对应的用户记录
        APIResponse<User> apiResponse = userService.getUserInfo(tokenEntity.getUserCode());
        //4. 若用户不存在, 则可以抛出 UnknownAccountException 异常
        if (apiResponse.getData() == null) {
            throw new UnknownAccountException("用户不存在!");
        }
        //5. 根据用户的情况, 来构建 AuthenticationInfo 对象并返回. 通常使用的实现类为: SimpleAuthenticationInfo
        SimpleAuthenticationInfo info = new SimpleAuthenticationInfo(apiResponse.getData(), accessToken, this.getName());
        return info;
    }
}
