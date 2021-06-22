package com.yisquare.springboot.controller;

import com.yisquare.springboot.common.APIResponse;
import com.yisquare.springboot.dao.query.QueryCondition;
import com.yisquare.springboot.pojo.Flow;
import com.yisquare.springboot.service.FlowService;
import com.yisquare.springboot.service.SystemService;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import java.util.List;

@RestController
@RequestMapping(value = "/admin")
public class AdminController {

    @Resource
    private FlowService flowService;

    @Resource
    private SystemService systemService;

    @GetMapping(value = "getFlowBySystemCode/{systemCode}")
    public APIResponse<List<Flow>> listFlow(@PathVariable String systemCode){
        return flowService.listFlowBySystem(systemCode);
    }





}
