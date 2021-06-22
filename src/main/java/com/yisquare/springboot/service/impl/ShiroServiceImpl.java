package com.yisquare.springboot.service.impl;


import com.github.benmanes.caffeine.cache.Cache;
import com.yisquare.springboot.common.APIResponse;
import com.yisquare.springboot.common.JwtUtil;
import com.yisquare.springboot.pojo.SysToken;
import com.yisquare.springboot.pojo.User;
import com.yisquare.springboot.service.ShiroService;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.time.LocalDateTime;


@Service
public class ShiroServiceImpl implements ShiroService {

    private final static int EXPIRE = 12;

    @Resource
    private Cache cache;



    @Override
    public SysToken createToken(User user) {
        //生成一个token
        String token = JwtUtil.sign(user.getUserCode());
        //当前时间
        LocalDateTime now = LocalDateTime.now();
        //过期时间
        LocalDateTime expireTime = now.plusHours(EXPIRE);

        SysToken sysToken = new SysToken();
        sysToken.setToken(token);
        sysToken.setUserCode(user.getUserCode());
        sysToken.setExpireDateTime(expireTime);
        sysToken.setUserName(user.getUserName());
        sysToken.setRoleID(user.getRoleID());
        cache.put(token,expireTime);
        return sysToken;
    }

    @Override
    public APIResponse<String> logout(String token) {
        if(null == cache.getIfPresent(token)){
            return APIResponse.fail("token失效",null);
        }
        cache.invalidate(token);
        return APIResponse.success(null);
    }


}
