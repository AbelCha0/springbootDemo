package com.yisquare.springboot.service;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.yisquare.springboot.common.APIResponse;
import com.yisquare.springboot.dao.SystemDao;
import com.yisquare.springboot.dao.query.QueryCondition;
import com.yisquare.springboot.pojo.SystemInfo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
public class SystemServiceImpl implements  SystemService{
    @Autowired
    private SystemDao systemDao;

    @Override
    public APIResponse<SystemInfo> getSystemInfoByCode(String systemCode) {
        return APIResponse.success(systemDao.getSystemInfoByCode(systemCode));
    }

    @Override
    @Transactional
    public APIResponse<Boolean> deleteSystemInfo(String systemCode){
        int resultCode = systemDao.deleteSystemIP(systemCode);
        if(resultCode > 0) {
            systemDao.deleteSystemMember(systemCode);
            systemDao.deleteSystemInfo(systemCode);
        }

        if(resultCode >0){
            return APIResponse.success(true);
        }else {
            return APIResponse.fail("删除系统失败",true);
        }
    }

    @Override
    public APIResponse<PageInfo<SystemInfo>> listSystemInfoByCode(QueryCondition queryCondition) {
       PageHelper.startPage(queryCondition.getPageNum(),queryCondition.getPageSize());
       return APIResponse.success(new PageInfo<SystemInfo>(systemDao.listSystemInfoByCode(queryCondition)));
    }

    @Override
    @Transactional
    public APIResponse<Boolean> createSystemInfo(SystemInfo systemInfo) {
        int resultCode = systemDao.createSystemInfo(systemInfo);
        return APIResponse.success(true);
    }
}
