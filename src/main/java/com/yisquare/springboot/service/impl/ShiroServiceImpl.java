package com.yisquare.springboot.service.impl;

import com.yisquare.springboot.common.APIResponse;
import com.yisquare.springboot.dao.SystemDao;
import com.yisquare.springboot.dao.TokenDao;
import com.yisquare.springboot.pojo.SysToken;
import com.yisquare.springboot.pojo.User;
import com.yisquare.springboot.service.ShiroService;
import com.yisquare.springboot.shiro.auth.TokenGenerator;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.HashMap;
import java.util.Map;

@Service
public class ShiroServiceImpl implements ShiroService {

    private final static int EXPIRE = 12;

    @Autowired
    private TokenDao tokenDao;

    @Autowired
    private SystemDao systemDao;


    @Override
    public SysToken createToken(String userCode) {
        //生成一个token
        String token = TokenGenerator.generateValue();
        //当前时间
        LocalDateTime now = LocalDateTime.now();
        //过期时间
        LocalDateTime expireTime = now.plusHours(EXPIRE);
        //判断是否生成过token
        SysToken sysToken = tokenDao.findByUserCode(userCode);


        if (sysToken == null) {
            sysToken = new SysToken();
            sysToken.setUserCode(userCode);
            //保存token
            sysToken.setToken(token);
            sysToken.setExpireDateTime(expireTime);
            tokenDao.saveToken(sysToken);
        } else {
            //更新token
            sysToken.setToken(token);
            sysToken.setExpireDateTime(expireTime);
            tokenDao.updateToken(sysToken);

        }
        //注入roleID
        sysToken.setRoleID(getRoleID(userCode));
        return sysToken;
    }

    @Override
    public APIResponse<String> logout(String token) {
        SysToken byToken = findByToken(token);
        if(byToken == null){
            return APIResponse.fail("无效的token",null);
        }
        token =  TokenGenerator.generateValue();
        byToken.setToken(token);
        tokenDao.updateToken(byToken);
        return APIResponse.success(null);
    }

    @Override
    public SysToken findByToken(String accessToken) {
       SysToken sysToken = tokenDao.findByToken(accessToken);
        sysToken.setRoleID(getRoleID(sysToken.getUserCode()));
        return sysToken;
    }

    @Override
    public SysToken findByUserCode(String userCode) {
        SysToken sysToken = tokenDao.findByUserCode(userCode);
        sysToken.setRoleID(getRoleID(sysToken.getUserCode()));
        return sysToken;
    }

    @Override
    public int getRoleID(String userCode){
        Integer roleID = null ;
        if("Admin".equals(userCode)){
            roleID = 2;
        }else {
            roleID = systemDao.getUserRole(userCode);
        }
        if(roleID == null){
            roleID = 1;
        }

        return roleID;
    }
}
