package com.yisquare.springboot.aop;

import com.yisquare.springboot.common.APIResponse;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.springframework.stereotype.Component;

@Aspect
@Component
public class ControllerAspect {

    @Around("execution(public com.yisquare.springboot.common.APIResponse  com..*.controller..*.*(..))")
    public Object handleControllerMethod(ProceedingJoinPoint pjp){
        APIResponse apiResponse;

        try{
            apiResponse =  (APIResponse)pjp.proceed(pjp.getArgs());
        } catch (Throwable throwable) {
            apiResponse =  handlerException(pjp,throwable);
        }

        return apiResponse;
    }

    private APIResponse handlerException(ProceedingJoinPoint pjp, Throwable e) {
        APIResponse apiResponse = APIResponse.fail(e.getMessage(),null);
        return apiResponse;
    }
}
