package com.yisquare.springboot.controller;


import com.yisquare.springboot.common.APIResponse;
import com.yisquare.springboot.dao.query.QueryCondition;
import com.yisquare.springboot.service.SystemService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
public class SystemController {
    @Autowired
    private SystemService systemService;

    @GetMapping(value="/system/{systemCode}")
    public APIResponse getSystemInfo(@PathVariable("systemCode") String systemCode){
        return APIResponse.success(systemService.getSystemInfoByCode(systemCode));
    }

    @DeleteMapping(value="/system/{systemCode}")
    public APIResponse deleteSystemInfo(@PathVariable("systemCode") String systemCode){
        return APIResponse.success(systemService.deleteSystemInfo(systemCode));
    }

    @PostMapping(value="/system/query")
    public APIResponse querySystemInfo(@RequestBody QueryCondition queryCondition){
        return APIResponse.success(systemService.listSystemInfoByCode(queryCondition));
    }


}
