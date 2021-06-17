package com.yisquare.springboot.controller;

import com.yisquare.springboot.common.APIResponse;

import com.yisquare.springboot.dao.query.QueryCondition;
import com.yisquare.springboot.pojo.Flow;
import com.yisquare.springboot.service.FlowService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.extern.slf4j.Slf4j;
import org.apache.shiro.authz.annotation.Logical;
import org.apache.shiro.authz.annotation.RequiresRoles;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

@Api(tags ="FlowService管理")
@RestController
@Slf4j
@RequestMapping("/api")
public class FlowServiceController {

    private FlowService flowService;
    @Autowired
    public void setFlowDao(FlowService flowService) {
        this.flowService = flowService;
    }


    @ApiOperation("分页查询某个系统编码下所有FlowService")
    @RequiresRoles(value = {"superAdmin","systemAdmin","systemOwner","systemMember"},logical= Logical.OR)
    @PostMapping("/listFlow")
    public APIResponse listFlow(@RequestBody QueryCondition queryCondition, BindingResult bindingResult){
        if (bindingResult.hasErrors()) {
            APIResponse.fail(bindingResult.getFieldError().getDefaultMessage(),null);
        }
        return flowService.queryFlow(queryCondition);
    }

    @ApiOperation("删除FlowService")
    @RequiresRoles(value = {"superAdmin","systemAdmin","systemOwner"},logical= Logical.OR)
    @DeleteMapping("/flow/{id}")
    public APIResponse deleteFlowByID(@PathVariable  int id){
        return flowService.deleteFlow(id);
    }



    @ApiOperation("新建FlowService")
    @RequiresRoles(value = {"superAdmin","systemAdmin","systemOwner"},logical= Logical.OR)
    @PostMapping("/flow")
    public APIResponse createFlow(@RequestBody Flow flow, BindingResult bindingResult){
        if (bindingResult.hasErrors()) {
            APIResponse.fail(bindingResult.getFieldError().getDefaultMessage(),null);
        }
        return flowService.addFlow(flow);
    }


}
