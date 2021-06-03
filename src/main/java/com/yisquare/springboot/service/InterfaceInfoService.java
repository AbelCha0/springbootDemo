package com.yisquare.springboot.service;


import com.github.pagehelper.PageInfo;
import com.yisquare.springboot.common.APIResponse;
import com.yisquare.springboot.dao.query.QueryCondition;
import com.yisquare.springboot.pojo.InterfaceInfo;


public interface InterfaceInfoService {

    APIResponse<PageInfo<InterfaceInfo>> listInterfaceInfo(QueryCondition queryCondition);

    APIResponse<Boolean> deleteInterfaceInfo(int id);

    APIResponse<Boolean> updateInterfaceInfo(InterfaceInfo interfaceInfo);

    APIResponse<Boolean> addInterfaceInfo(InterfaceInfo interfaceInfo);
}
