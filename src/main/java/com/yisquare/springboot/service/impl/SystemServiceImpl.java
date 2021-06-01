package com.yisquare.springboot.service.impl;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.yisquare.springboot.common.APIResponse;
import com.yisquare.springboot.dao.SystemDao;
import com.yisquare.springboot.dao.TokenDao;
import com.yisquare.springboot.dao.query.QueryCondition;
import com.yisquare.springboot.pojo.SysToken;
import com.yisquare.springboot.pojo.SystemInfo;
import com.yisquare.springboot.pojo.SystemMember;
import com.yisquare.springboot.service.ShiroService;
import com.yisquare.springboot.service.SystemService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
public class SystemServiceImpl implements SystemService {
    @Autowired
    private SystemDao systemDao;

    @Autowired
    private ShiroService shiroService;

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
    public APIResponse<PageInfo<SystemInfo>> listSystemInfoByCode(QueryCondition queryCondition,String token) {
        SysToken sysToken =shiroService.findByToken(token);
        PageHelper.startPage(queryCondition.getPageNum(),queryCondition.getPageSize());

        if(sysToken.getRoleID() == 2){//管理员查询所有的系统
            return APIResponse.success(new PageInfo<SystemInfo>(systemDao.listAllSystemInfoByCode(queryCondition)));
        }else{//查询与自己权限相关的系统
            queryCondition.setOperateUserCode(sysToken.getUserCode());
            return APIResponse.success(new PageInfo<SystemInfo>(systemDao.listSystemInfoByCode(queryCondition)));
        }

    }

    @Override
    @Transactional
    public APIResponse<Boolean> createSystemInfo(SystemInfo systemInfo) {
        int resultCode = systemDao.createSystemInfo(systemInfo);
        if(resultCode > 0) {
            String[] ipAddress = systemInfo.getIpAddress();
            List<SystemMember> systemMember = systemInfo.getSystemMember();

            if (ipAddress != null && ipAddress.length > 0) {
                systemDao.insertSystemIP(systemInfo);
            }
            if (systemMember!= null && systemMember.size() > 0) {
                systemDao.insertSystemMember(systemInfo);
            }
        }

        return APIResponse.success(true);
    }


    @Override
    public Integer getUserRole(String userCode) {
        return systemDao.getUserRole(userCode);
    }

    @Override
    @Transactional
    public APIResponse<Boolean> updateSystemInfo(SystemInfo systemInfo) {

        int resultCode = systemDao.updateSystemInfo(systemInfo);
        if(resultCode > 0) {
            String systemCode = systemInfo.getSystemCode();
            String[] ipAddress = systemInfo.getIpAddress();
            List<SystemMember> systemMember = systemInfo.getSystemMember();
            systemDao.deleteSystemIP(systemCode);
            systemDao.deleteSystemMember(systemCode);
            if (ipAddress != null && ipAddress.length > 0) {

                systemDao.insertSystemIP(systemInfo);
            }
            if (systemMember != null && systemMember.size() > 0) {

                systemDao.insertSystemMember(systemInfo);
            }
        }
        return APIResponse.success(true);
    }

    @Override
    public boolean hasDataPermit(String userCode, String systemCode) {
        return false;
    }
}
