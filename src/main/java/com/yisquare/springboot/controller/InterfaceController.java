package com.yisquare.springboot.controller;

import com.yisquare.springboot.common.APIResponse;
import com.yisquare.springboot.dao.InterfaceDao;
import com.yisquare.springboot.dao.query.QueryCondition;
import com.yisquare.springboot.pojo.InterfaceInfo;
import com.yisquare.springboot.service.InterfaceInfoService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.extern.slf4j.Slf4j;
import org.apache.shiro.authz.annotation.Logical;
import org.apache.shiro.authz.annotation.RequiresRoles;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

@Api(tags ="管理API信息")
@RestController
@Slf4j
@RequestMapping("/api")
public class InterfaceController {

    private InterfaceInfoService interfaceInfoService;
    @Autowired
    public void setInterfaceInfoService(InterfaceInfoService interfaceInfoService) {
        this.interfaceInfoService = interfaceInfoService;
    }

    @ApiOperation("查询某系统编码下的所有API信息")
    @GetMapping ("/listInterface")
    @RequiresRoles(value = {"superAdmin","systemAdmin","systemOwner"},logical= Logical.OR)
    public APIResponse listInterfaceInfo(@RequestBody  @Validated QueryCondition queryCondition, BindingResult bindingResult){
        if (bindingResult.hasErrors()) {
            APIResponse.fail(bindingResult.getFieldError().getDefaultMessage(),null);
        }

        return interfaceInfoService.listInterfaceInfo(queryCondition);
    }

    @ApiOperation("根据ID删除API")
    @DeleteMapping("/interface/{id}")
    @RequiresRoles(value = {"superAdmin","systemAdmin","systemOwner"},logical= Logical.OR)
    public APIResponse deleteInterface(@PathVariable int id){
        return interfaceInfoService.deleteInterfaceInfo(id);
    }

    @ApiOperation("修改API信息")
    @PutMapping("interface")
    @RequiresRoles(value = {"superAdmin","systemAdmin","systemOwner"},logical= Logical.OR)
    public APIResponse updateInterface(@RequestBody @Validated InterfaceInfo interfaceInfo, BindingResult bindingResult){
        if (bindingResult.hasErrors()) {
            APIResponse.fail(bindingResult.getFieldError().getDefaultMessage(),null);
        }
        return interfaceInfoService.updateInterfaceInfo(interfaceInfo);
    }

    @ApiOperation("创建API信息")
    @PostMapping("interface")
    @RequiresRoles(value = {"superAdmin","systemAdmin","systemOwner"},logical= Logical.OR)
    public APIResponse createInterface(@RequestBody @Validated InterfaceInfo interfaceInfo, BindingResult bindingResult){
        if (bindingResult.hasErrors()) {
            APIResponse.fail(bindingResult.getFieldError().getDefaultMessage(),null);
        }
        return interfaceInfoService.addInterfaceInfo(interfaceInfo);
    }
}
