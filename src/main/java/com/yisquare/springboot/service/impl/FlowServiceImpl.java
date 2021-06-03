package com.yisquare.springboot.service.impl;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.yisquare.springboot.common.APIResponse;
import com.yisquare.springboot.common.constraint.Operate;
import com.yisquare.springboot.dao.FlowDao;
import com.yisquare.springboot.dao.query.QueryCondition;
import com.yisquare.springboot.pojo.Flow;
import com.yisquare.springboot.service.FlowService;
import com.yisquare.springboot.service.ShiroService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;



@Service
public class FlowServiceImpl implements FlowService {
    @Autowired
    private FlowDao flowDao;

    @Autowired
    private ShiroService shiroService;

    @Override
    public APIResponse<PageInfo<Flow>> queryFlow(QueryCondition queryCondition) {
        if(shiroService.hasSystemPermit(queryCondition.getSystemCode(), Operate.SELECT)){
            PageHelper.startPage(queryCondition.getPageNum(),queryCondition.getPageSize());
            return APIResponse.success(new PageInfo<Flow>(flowDao.listFlowServiceByName(queryCondition)));
        }
        return APIResponse.fail(String.format("你没有权限对系统%s下的Flow进行该操作",queryCondition.getSystemCode()),null);
    }

    @Override
    public APIResponse<Boolean> addFlow(Flow flow) {
        if (shiroService.hasSystemPermit(flow.getSystemCode(), Operate.INSERT)) {
            int result = flowDao.addFlowService(flow);
            if (result == 0) {
                return APIResponse.fail("新增Flow失败", null);
            }
            return APIResponse.success(true);
        }
         return APIResponse.fail(String.format("你没有权限对系统%s下的Flow进行该操作",flow.getSystemCode()),null);
    }

    @Override
    public APIResponse<Boolean> deleteFlow(int id) {
        Flow flow = flowDao.getFlowByID(id);
        if(flow == null){
            return APIResponse.fail(String.format("Flow:%s不存在",flow.getFlowServiceName()),null);
        }
        if (shiroService.hasSystemPermit(flow.getSystemCode(), Operate.DELETE)) {
            int result = flowDao.deleteFlowService(flow);
            if (result == 0) {
                return APIResponse.fail("删除Flow失败", null);
            }
            return APIResponse.success(true);
        }
        return APIResponse.fail(String.format("你没有权限对系统%s下的Flow进行该操作",flow.getSystemCode()),null);
    }

}
