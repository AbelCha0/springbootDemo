package com.yisquare.springboot.controller;


import com.yisquare.springboot.common.APIResponse;
import com.yisquare.springboot.dao.query.QueryCondition;
import com.yisquare.springboot.pojo.SystemInfo;
import com.yisquare.springboot.service.SystemService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.apache.shiro.authz.annotation.Logical;
import org.apache.shiro.authz.annotation.RequiresRoles;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;


@Api(tags ="系统管理")
@RestController
@RequestMapping("/api")
public class SystemController {

    private SystemService systemService;

    @Autowired
    public void setSystemService(SystemService systemService) {
        this.systemService = systemService;
    }

    @ApiOperation("根据系统编码查询系统的详情信息")
    @GetMapping(value="/system/{systemCode}")
    public APIResponse getSystemInfo(@PathVariable("systemCode") String systemCode){

        return systemService.getSystemInfoByCode(systemCode);
    }

    @ApiOperation("根据系统编码删除系统")
    @RequiresRoles({"superAdmin"})
    @DeleteMapping(value="/system/{systemCode}")
    public APIResponse deleteSystemInfo(@PathVariable("systemCode") String systemCode){
        return systemService.deleteSystemInfo(systemCode);
    }

    @ApiOperation("分页查询所有系统信息")
    @PostMapping(value="/listSystem")
    public APIResponse querySystemInfo(@RequestBody QueryCondition queryCondition){
        return systemService.listSystemInfoByCode(queryCondition);
    }

    @ApiOperation("更新系统信息")
    @RequiresRoles(value = {"superAdmin","systemAdmin","systemOwner"},logical= Logical.OR)
    @PutMapping(value="/system")
    public APIResponse updateSystemInfo(@RequestBody @Validated SystemInfo systemInfo, BindingResult bindingResult){
        if (bindingResult.hasErrors()) {
            APIResponse.fail(bindingResult.getFieldError().getDefaultMessage(),null);
        }
        return systemService.updateSystemInfo(systemInfo);
    }

    @ApiOperation("新建系统")
    @RequiresRoles(value = {"superAdmin","systemAdmin","systemOwner"},logical= Logical.OR)
    @PostMapping(value="/system")
    public APIResponse createSystemInfo(@RequestBody @Validated SystemInfo systemInfo, BindingResult bindingResult){
        if (bindingResult.hasErrors()) {
            APIResponse.fail(bindingResult.getFieldError().getDefaultMessage(),null);
        }
        return systemService.createSystemInfo(systemInfo);
    }


}
