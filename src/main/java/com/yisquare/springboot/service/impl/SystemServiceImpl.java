package com.yisquare.springboot.service.impl;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.yisquare.springboot.common.APIResponse;
import com.yisquare.springboot.common.constraint.Operate;
import com.yisquare.springboot.common.constraint.Role;
import com.yisquare.springboot.dao.SystemDao;
import com.yisquare.springboot.dao.query.QueryCondition;
import com.yisquare.springboot.pojo.*;
import com.yisquare.springboot.service.ShiroService;
import com.yisquare.springboot.service.SystemService;
import org.apache.shiro.SecurityUtils;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;
import java.util.List;

@Service
public class SystemServiceImpl implements SystemService {
    @Resource
    private SystemDao systemDao;

    @Resource
    private ShiroService shiroService;

    @Override
    public APIResponse<SystemInfo> getSystemInfoByCode(String systemCode) {

        if(shiroService.hasSystemPermit(systemCode,Operate.SELECT)) {
            return APIResponse.success(systemDao.getSystemInfoByCode(systemCode));
        }else{
            return APIResponse.fail(String.format("你没有权限对%s进行该操作!",systemCode),null);
        }
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
       // SysToken sysToken =shiroService.findByToken(token);
        //获取当前登录用户
        User user = (User) SecurityUtils.getSubject().getPrincipal();
        PageHelper.startPage(queryCondition.getPageNum(),queryCondition.getPageSize());

        if(shiroService.getRoleID(user.getUserCode()) == Role.SUPERADMIN.getRoleID()){//管理员查询所有的系统
            return APIResponse.success(new PageInfo<>(systemDao.listAllSystemInfoByCode(queryCondition)));
        }else{//查询与自己权限相关的系统
            queryCondition.setOperateUserCode(user.getUserCode());
            return APIResponse.success(new PageInfo<>(systemDao.listSystemInfoByCode(queryCondition)));
        }

    }

    @Override
    @Transactional
    public APIResponse<Boolean> createSystemInfo(SystemInfo systemInfo) {

        if(null != systemDao.getSystemInfoByCode(systemInfo.getSystemCode())){
            return APIResponse.fail(String.format("系统编码%s已经存在.",systemInfo.getSystemCode()),null);
        }

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
    @Transactional
    public APIResponse<Boolean> updateSystemInfo(SystemInfo systemInfo) {
        if(shiroService.hasSystemPermit(systemInfo.getSystemCode(),Operate.UPDATE)) {
            int resultCode = systemDao.updateSystemInfo(systemInfo);
            if (resultCode > 0) {
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
        }else{
            return APIResponse.fail(String.format("你没有权限对%s进行该操作!",systemInfo.getSystemCode()),null);
        }
    }

    @Override
    public APIResponse<List<String>> listSystemInfoByUserCode(String userCode) {
        return APIResponse.success(systemDao.listAllSystemInfoByUserCode(userCode));
    }


}
