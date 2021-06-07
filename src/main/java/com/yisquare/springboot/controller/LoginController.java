package com.yisquare.springboot.controller;


import com.yisquare.springboot.common.APIResponse;
import com.yisquare.springboot.common.JwtUtil;
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
import org.springframework.util.ObjectUtils;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;


@Api(tags = "登录")
@RestController
@Slf4j
public class LoginController {

    @Autowired
    private UserService userService;

    @Autowired
    private ShiroService shiroService;



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

        if("success".equals(userAPIResponse.getStatus()) ){
            String secret = userAPIResponse.getData().getUserPassword();
           if( secret.equals(password)) {
               SysToken sysToken = shiroService.createToken(userCode, secret);
               return APIResponse.success(sysToken);
           }
           else{
               return APIResponse.fail("账号或密码错误!",null);
           }

        }else{
            return APIResponse.fail("账号或密码错误!",null);
        }


    }

    @ApiOperation("登出用户")
    @GetMapping("/logout")
    public APIResponse<String>  logout(@RequestHeader(name = "token",required = false) String token){
        if(token == null){
            return APIResponse.fail("Token为空",null);
        }
        String userCode =JwtUtil.getUserCode(token);
        if(!ObjectUtils.isEmpty(userCode)) {
            APIResponse<User> userAPIResponse = userService.getUserInfo(userCode);
            if(userAPIResponse.getStatus() == "success" && userCode.equals(userAPIResponse.getData().getUserCode())){
                return shiroService.logout(token);
            }
        }
        return APIResponse.fail("Token非法或者无效",null);


    }

}
