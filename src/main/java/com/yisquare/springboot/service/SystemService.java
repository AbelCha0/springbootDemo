package com.yisquare.springboot.service;

import com.github.pagehelper.PageInfo;
import com.yisquare.springboot.common.APIResponse;
import com.yisquare.springboot.dao.query.QueryCondition;
import com.yisquare.springboot.pojo.SystemInfo;

public interface SystemService {

    APIResponse<SystemInfo> getSystemInfoByCode(String systemCode);

    APIResponse<Boolean> deleteSystemInfo(String systemCode);

    APIResponse<PageInfo<SystemInfo>> listSystemInfoByCode(QueryCondition queryCondition,String token);

    APIResponse<Boolean> createSystemInfo(SystemInfo systemInfo);

    Integer getUserRole(String userCode);

    APIResponse<Boolean> updateSystemInfo(SystemInfo systemInfo);

    boolean hasDataPermit(String userCode,String systemCode);


}
