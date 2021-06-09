package com.yisquare.springboot.service;

import com.yisquare.springboot.common.APIResponse;
import com.yisquare.springboot.common.constraint.Operate;
import com.yisquare.springboot.pojo.SysToken;


public interface ShiroService {


    SysToken createToken(String userCode);

    APIResponse<String> logout(String userCode);

    int getRoleID(String userCode);

    boolean hasSystemPermit(String systemCode, Operate operate);
}
