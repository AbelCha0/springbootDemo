package com.yisquare.springboot.service;


import com.github.pagehelper.PageInfo;
import com.yisquare.springboot.common.APIResponse;
import com.yisquare.springboot.dao.query.QueryCondition;
import com.yisquare.springboot.pojo.InterfaceInfo;

import java.util.List;


public interface InterfaceInfoService {

    APIResponse<PageInfo<InterfaceInfo>> listInterfaceInfo(QueryCondition queryCondition);

    List<InterfaceInfo> listInterfaceInfoBySysCode(String systemCode);

    APIResponse<Boolean> deleteInterfaceInfo(int id);

    APIResponse<Boolean> updateInterfaceInfo(InterfaceInfo interfaceInfo);

    APIResponse<Boolean> addInterfaceInfo(InterfaceInfo interfaceInfo);
}
