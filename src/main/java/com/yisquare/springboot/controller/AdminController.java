package com.yisquare.springboot.controller;


import com.yisquare.springboot.pojo.Flow;
import com.yisquare.springboot.pojo.InterfaceInfo;
import com.yisquare.springboot.service.FlowService;
import com.yisquare.springboot.service.InterfaceInfoService;

import org.jasypt.encryption.StringEncryptor;
import org.jasypt.encryption.pbe.PooledPBEStringEncryptor;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

@RestController
@RequestMapping(value = "/admin")
public class AdminController {

    @Resource
    private FlowService flowService;

    @Resource
    private InterfaceInfoService interfaceInfoService;

    @Resource
    private StringEncryptor stringEncryptor;

    @GetMapping(value = "getFlowBySystemCode/{systemCode}")
    public List<String> listFlow(@PathVariable String systemCode){
        List<String> result = new ArrayList<>();
        Set<String> set = new HashSet<>();

       List<Flow>  flowResult = flowService.listFlowBySystem(systemCode);
       List<InterfaceInfo> interfaceInfoResult = interfaceInfoService.listInterfaceInfoBySysCode(systemCode);

       if(flowResult != null && flowResult.size() > 0){
            for(Flow item : flowResult){
                set.add(item.getFlowServiceName());
            }
       }
        if(interfaceInfoResult != null && interfaceInfoResult.size() > 0){
            for(InterfaceInfo item : interfaceInfoResult){
                set.add(item.getApiName());
            }
        }
        result.addAll(set);
       return result;

    }

    @GetMapping(value="encrypt/{text}")
    public String encrypt(@PathVariable String text){
        return stringEncryptor.encrypt(text);

    }






}
