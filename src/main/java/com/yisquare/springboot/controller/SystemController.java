package com.yisquare.springboot.controller;


import com.yisquare.springboot.common.APIResponse;
import com.yisquare.springboot.dao.query.QueryCondition;
import com.yisquare.springboot.pojo.Role;
import com.yisquare.springboot.pojo.SystemInfo;
import com.yisquare.springboot.service.SystemService;
import org.apache.shiro.authz.annotation.Logical;
import org.apache.shiro.authz.annotation.RequiresRoles;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api")
public class SystemController {
    @Autowired
    private SystemService systemService;


    @GetMapping(value="/system/{systemCode}")
    public APIResponse getSystemInfo(@PathVariable("systemCode") String systemCode, @RequestHeader("token") String token){

        return systemService.getSystemInfoByCode(systemCode);
    }

    @RequiresRoles({"superAdmin"})
    @DeleteMapping(value="/system/{systemCode}")
    public APIResponse deleteSystemInfo(@PathVariable("systemCode") String systemCode, @RequestHeader("token") String token){
        return systemService.deleteSystemInfo(systemCode);
    }

    @PostMapping(value="/listSystem")
    public APIResponse querySystemInfo(@RequestBody QueryCondition queryCondition, @RequestHeader("token") String token){
        return systemService.listSystemInfoByCode(queryCondition,token);
    }

    @RequiresRoles(value = {"superAdmin","systemAdmin"},logical= Logical.OR)
    @PutMapping(value="/system")
    public APIResponse updateSystemInfo(@RequestBody SystemInfo systemInfo, @RequestHeader("token" )String token){
        return systemService.updateSystemInfo(systemInfo);
    }


}
