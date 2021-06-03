package com.yisquare.springboot.controller;


import com.yisquare.springboot.common.APIResponse;
import com.yisquare.springboot.common.PasswordProcess;
import com.yisquare.springboot.dto.LoginDTO;
import com.yisquare.springboot.pojo.SysToken;
import com.yisquare.springboot.pojo.User;
import com.yisquare.springboot.service.ShiroService;
import com.yisquare.springboot.service.SystemService;
import com.yisquare.springboot.service.UserService;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RestController;


@Api(tags = "登录")
@RestController
@Slf4j
public class LoginController {

    @Autowired
    private UserService userService;

    @Autowired
    private ShiroService shiroService;

    @Autowired
    private SystemService systemService;


    @ApiOperation(value = "登录",notes = "参数：用户名，密码")
    @PostMapping("/login")
    public APIResponse<SysToken> login(@RequestBody @Validated LoginDTO loginDTO, BindingResult bindingResult) {
        if (bindingResult.hasErrors()) {
            APIResponse.fail(bindingResult.getFieldError().getDefaultMessage(),null);
        }
        //用户认证信息

        String userCode = loginDTO.getUserCode();
        String password = PasswordProcess.makeMD5(loginDTO.getUserPassword());

        APIResponse<User> userAPIResponse = userService.getUserInfo(userCode);
        if(userAPIResponse.getStatus() == "success" && password.equals(userAPIResponse.getData().getUserPassword())){

            SysToken sysToken = shiroService.createToken(userCode);
            return APIResponse.success(sysToken);
        }else{
            return APIResponse.fail("账号或密码错误!",null);
        }


    }

    @ApiOperation("登出用户")
    @PostMapping("/logout")
    public APIResponse<String>  logout(@RequestHeader(name = "token") String token){
        if(token == null){
            return APIResponse.fail("Token已经失效",null);
        }
        return shiroService.logout(token);
    }

}
