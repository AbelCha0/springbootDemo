package com.yisquare.springboot.service;

import com.github.pagehelper.PageInfo;
import com.yisquare.springboot.common.APIResponse;
import com.yisquare.springboot.dao.query.QueryCondition;
import com.yisquare.springboot.pojo.SystemInfo;

public interface SystemService {

    APIResponse<SystemInfo> getSystemInfoByCode(String systemCode);

    APIResponse<Boolean> deleteSystemInfo(String systemCode);

    APIResponse<PageInfo<SystemInfo>> listSystemInfoByCode(QueryCondition queryCondition);

    APIResponse<Boolean> createSystemInfo(SystemInfo systemInfo);

    APIResponse<Boolean> updateSystemInfo(SystemInfo systemInfo);




}
