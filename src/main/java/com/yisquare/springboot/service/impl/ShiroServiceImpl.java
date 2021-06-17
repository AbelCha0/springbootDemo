package com.yisquare.springboot.service.impl;


import com.github.benmanes.caffeine.cache.Cache;
import com.yisquare.springboot.common.APIResponse;
import com.yisquare.springboot.common.JwtUtil;
import com.yisquare.springboot.common.constraint.Operate;
import com.yisquare.springboot.dao.SystemDao;
import com.yisquare.springboot.dao.TokenDao;
import com.yisquare.springboot.dao.query.OperateCondition;
import com.yisquare.springboot.pojo.SysToken;
import com.yisquare.springboot.pojo.User;
import com.yisquare.springboot.service.ShiroService;
import org.apache.shiro.SecurityUtils;
import org.apache.shiro.authc.IncorrectCredentialsException;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.time.LocalDateTime;


@Service
public class ShiroServiceImpl implements ShiroService {

    private final static int EXPIRE = 12;

    @Resource
    private TokenDao tokenDao;

    @Resource
    private SystemDao systemDao;

    @Resource
    private Cache cache;



    @Override
    public SysToken createToken(String userCode) {
        //生成一个token
        String token = JwtUtil.sign(userCode);
        //当前时间
        LocalDateTime now = LocalDateTime.now();
        //过期时间
        LocalDateTime expireTime = now.plusHours(EXPIRE);

        SysToken sysToken = new SysToken();
        sysToken.setToken(token);
        sysToken.setUserCode(userCode);
        sysToken.setExpireDateTime(expireTime);
        //注入roleID
        sysToken.setRoleID(getRoleID(userCode));
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



    @Override
    public int getRoleID(String userCode){
        Integer roleID = null ;
        if("admin".equalsIgnoreCase(userCode)){
            roleID = 0;
        }else {
            roleID = systemDao.getUserRole(userCode);
        }
        if(roleID == null){
            roleID = 3;
        }

        return roleID;
    }

    @Override
    public boolean hasSystemPermit(String systemCode, Operate operate) {
        User user = (User) SecurityUtils.getSubject().getPrincipal();
        if(user == null){
            return  false;
        }
        OperateCondition operateCondition = new OperateCondition();
        operateCondition.setSystemCode(systemCode);
        operateCondition.setUserCode(user.getUserCode());
        operateCondition.setOperate(operate);

        if("Admin".equals(user.getUserCode())){
            return true;
        }
        return 0==systemDao.hasPermit(operateCondition)? false: true;
    }
}
