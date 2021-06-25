package com.yisquare.springboot.service;

import com.github.pagehelper.PageInfo;
import com.yisquare.springboot.common.APIResponse;
import com.yisquare.springboot.dao.query.QueryCondition;
import com.yisquare.springboot.pojo.Flow;

import java.util.List;

public interface FlowService {

    APIResponse<PageInfo<Flow>> queryFlow(QueryCondition queryCondition);

    List<Flow> listFlowBySystem(String systemCode);

    APIResponse<Boolean> addFlow(Flow flow);

    APIResponse<Boolean> deleteFlow(int id);

}
