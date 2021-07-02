package com.yisquare.springboot.handler;

import com.yisquare.springboot.common.APIResponse;
import lombok.extern.slf4j.Slf4j;
import org.apache.shiro.authz.AuthorizationException;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

@Slf4j
@ControllerAdvice
public class GlobalExceptionHandler {


    @ExceptionHandler(Exception.class)
    @ResponseStatus(HttpStatus.OK)
    @ResponseBody
    public APIResponse sendErrorResponse_System(Exception exception){
        if (exception instanceof AuthorizationException) {
            return APIResponse.fail("你未登录或者没有权限访问！",null);
        }else{
            exception.printStackTrace();
            log.error(exception.toString());
            return  APIResponse.fail("服务器端异常，请联系管理员！",null);
        }

    }
}
