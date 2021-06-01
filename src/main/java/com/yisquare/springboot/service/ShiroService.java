package com.yisquare.springboot.service;

import com.yisquare.springboot.common.APIResponse;
import com.yisquare.springboot.pojo.SysToken;
import com.yisquare.springboot.pojo.User;

import java.util.Map;

public interface ShiroService {


    SysToken createToken(String userCode);

    APIResponse<String> logout(String token);

    SysToken findByToken(String accessToken);

    SysToken findByUserCode(String userCode);

    int getRoleID(String userCode);
}
