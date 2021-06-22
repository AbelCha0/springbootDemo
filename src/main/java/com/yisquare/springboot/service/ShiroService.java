package com.yisquare.springboot.service;

import com.yisquare.springboot.common.APIResponse;
import com.yisquare.springboot.pojo.SysToken;
import com.yisquare.springboot.pojo.User;


public interface ShiroService {

    SysToken createToken(User user);

    APIResponse<String> logout(String userCode);

}
