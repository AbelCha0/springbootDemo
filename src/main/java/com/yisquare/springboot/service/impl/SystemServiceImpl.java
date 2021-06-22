package com.yisquare.springboot.service.impl;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.yisquare.springboot.common.APIResponse;
import com.yisquare.springboot.dao.FlowDao;
import com.yisquare.springboot.dao.InterfaceDao;
import com.yisquare.springboot.dao.SystemDao;
import com.yisquare.springboot.dao.query.QueryCondition;
import com.yisquare.springboot.pojo.*;
import com.yisquare.springboot.service.SystemService;
import org.apache.shiro.SecurityUtils;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import javax.annotation.Resource;


@Service
public class SystemServiceImpl implements SystemService {
    @Resource
    private SystemDao systemDao;

    @Resource
    private FlowDao flowDao;

    @Resource
    private InterfaceDao interfaceDao;

    @Override
    public APIResponse<SystemInfo> getSystemInfoByCode(String systemCode) {
          return APIResponse.success(systemDao.getSystemInfoByCode(systemCode));

    }

    @Override
    @Transactional
    public APIResponse<Boolean> deleteSystemInfo(String systemCode){
        //如果系统下有接口或者flowService禁止删除
        if(flowDao.getFlowCount(systemCode) >0 || interfaceDao.getInterfaceCount(systemCode)>0){
            return APIResponse.fail(String.format("系统%s下存在接口或者Flow，请删除后再删除系统",systemCode),null);
        }
        SystemInfo systemInfo = systemDao.getSystemInfoByCode(systemCode);
        if(null == systemInfo){
            APIResponse.fail(String.format("系统%s不存在，删除失败",systemCode),null);
        }

        systemDao.deleteSystemIP(systemCode);
        systemDao.deleteSystemInfo(systemCode);
        return APIResponse.success(true);

    }

    @Override
    public APIResponse<PageInfo<SystemInfo>> listSystemInfoByCode(QueryCondition queryCondition) {

        PageHelper.startPage(queryCondition.getPageNum(),queryCondition.getPageSize());
        return APIResponse.success(new PageInfo<>(systemDao.listSystemInfoByCode(queryCondition)));

    }

    @Override
    @Transactional
    public APIResponse<Boolean> createSystemInfo(SystemInfo systemInfo) {

        if(null != systemDao.getSystemInfoByCode(systemInfo.getSystemCode())){
            return APIResponse.fail(String.format("系统编码%s已经存在.",systemInfo.getSystemCode()),null);
        }

        User user = (User) SecurityUtils.getSubject().getPrincipal();
        systemInfo.setCreateUser(user.getUserName());

        int resultCode = systemDao.createSystemInfo(systemInfo);
        if(resultCode > 0) {
            String[] ipAddress = systemInfo.getIpAddress();
            if (ipAddress != null && ipAddress.length > 0) {
                systemDao.insertSystemIP(systemInfo);
            }
        }
        return APIResponse.success(true);
    }



    @Override
    @Transactional
    public APIResponse<Boolean> updateSystemInfo(SystemInfo systemInfo) {

            int resultCode = systemDao.updateSystemInfo(systemInfo);
            if (resultCode > 0) {
                String systemCode = systemInfo.getSystemCode();
                String[] ipAddress = systemInfo.getIpAddress();

                systemDao.deleteSystemIP(systemCode);

                if (ipAddress != null && ipAddress.length > 0) {

                    systemDao.insertSystemIP(systemInfo);
                }

            }
            return APIResponse.success(true);
    }


}
