package com.yisquare.springboot.service.impl;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.yisquare.springboot.common.APIResponse;
import com.yisquare.springboot.dao.FlowDao;
import com.yisquare.springboot.dao.SystemDao;
import com.yisquare.springboot.dao.query.QueryCondition;
import com.yisquare.springboot.pojo.Flow;
import com.yisquare.springboot.service.FlowService;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;


@Service
public class FlowServiceImpl implements FlowService {
    @Resource
    private FlowDao flowDao;

    @Resource
    private SystemDao systemDao;


    @Override
    public APIResponse<PageInfo<Flow>> queryFlow(QueryCondition queryCondition) {
            PageHelper.startPage(queryCondition.getPageNum(),queryCondition.getPageSize());
            return APIResponse.success(new PageInfo<>(flowDao.listFlowServiceByName(queryCondition)));
    }


    @Override
    public APIResponse<List<Flow>> listFlowBySystem(String systemCode) {
        QueryCondition queryCondition = new QueryCondition();
        queryCondition.setSystemCode(systemCode);
        return APIResponse.success(flowDao.listFlowServiceByName(queryCondition));
    }

    @Override
    public APIResponse<Boolean> addFlow(Flow flow) {
        if(flow!=null && null ==systemDao.getSystemInfoByCode(flow.getSystemCode())){
            return APIResponse.fail(String.format("系统编码%s不存在"),null);
        }

        if(null != flowDao.getFlowByCode(flow.getSystemCode(),flow.getFlowServiceName())){
            return APIResponse.fail(String.format("系统编码%s下已经存在Flow %s.",flow.getSystemCode(),flow.getFlowServiceName()),null);
        }
            int result = flowDao.addFlowService(flow);
            if (result == 0) {
                return APIResponse.fail("新增Flow失败", null);
            }
            return APIResponse.success(true);
        }


    @Override
    public APIResponse<Boolean> deleteFlow(int id) {
        Flow flow = flowDao.getFlowByID(id);
        if (flow == null) {
            return APIResponse.fail("指定的Flow不存在", null);
        }

        int result = flowDao.deleteFlowServiceByID(id);
        if (result == 0) {
            return APIResponse.fail("删除Flow失败", null);
        } else {
            return APIResponse.success(true);
        }
    }


}
