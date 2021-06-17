package com.yisquare.springboot.service.impl;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.yisquare.springboot.common.APIResponse;
import com.yisquare.springboot.common.constraint.Operate;
import com.yisquare.springboot.dao.InterfaceDao;
import com.yisquare.springboot.dao.query.QueryCondition;
import com.yisquare.springboot.pojo.InterfaceInfo;
import com.yisquare.springboot.service.InterfaceInfoService;
import com.yisquare.springboot.service.ShiroService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class InterfaceInfoServiceImpl implements InterfaceInfoService {

    private InterfaceDao interfaceDao;


    private ShiroService shiroService;

    @Autowired
    public void setInterfaceDao(InterfaceDao interfaceDao) {
        this.interfaceDao = interfaceDao;
    }
    @Autowired
    public void setShiroService(ShiroService shiroService){
        this.shiroService =shiroService;
    }

    @Override
    public APIResponse<PageInfo<InterfaceInfo>> listInterfaceInfo(QueryCondition queryCondition) {
        if(shiroService.hasSystemPermit(queryCondition.getSystemCode(), Operate.SELECT)){
            PageHelper.startPage(queryCondition.getPageNum(),queryCondition.getPageSize());
            return APIResponse.success(new PageInfo<>(interfaceDao.listInterface(queryCondition)));
        }
        return APIResponse.fail(String.format("你没有权限对系统%s下的API进行该操作",queryCondition.getSystemCode()),null);


    }

    @Override
    public APIResponse<Boolean> deleteInterfaceInfo(int id) {
        InterfaceInfo interfaceInfo = interfaceDao.getInterfaceInfoByID(id);
        if(interfaceInfo == null){
            return APIResponse.fail(String.format("interface id :%s不存在",id),false);
        }

        if (shiroService.hasSystemPermit(interfaceInfo.getSystemCode(), Operate.DELETE)) {
            int result = interfaceDao.deleteInterface(interfaceInfo);
            if (result == 0) {
                return APIResponse.fail("删除API失败,请检查该API是否存在", false);
            }
            return APIResponse.success(true);
        }
        return APIResponse.fail(String.format("你没有权限对系统%s下的API进行该操作",interfaceInfo.getSystemCode()),false);

    }

    @Override
    public APIResponse<Boolean>  updateInterfaceInfo(InterfaceInfo interfaceInfo) {
        if (shiroService.hasSystemPermit(interfaceInfo.getSystemCode(), Operate.UPDATE)) {
            int result = interfaceDao.updateInterface(interfaceInfo);
            if (result == 0) {
                return APIResponse.fail("删除Flow失败", null);
            }
            return APIResponse.success(true);
        }
        return APIResponse.fail(String.format("你没有权限对系统%s进行该操作",interfaceInfo.getSystemCode()),null);

    }

    @Override
    public APIResponse<Boolean>  addInterfaceInfo(InterfaceInfo interfaceInfo) {
        if(null != interfaceDao.getInterfaceInfoByCode(interfaceInfo.getSystemCode(),interfaceInfo.getApiCode())){
            return APIResponse.fail(String.format("系统编码%s下已经存在接口%s.",interfaceInfo.getSystemCode(),interfaceInfo.getApiCode()),null);
        }

        if (shiroService.hasSystemPermit(interfaceInfo.getSystemCode(), Operate.INSERT)) {
            int result = interfaceDao.addInterface(interfaceInfo);
            if (result == 0) {
                return APIResponse.fail("新增Flow失败", null);
            }
            return APIResponse.success(true);
        }
        return APIResponse.fail(String.format("你没有权限对系统%s进行该操作",interfaceInfo.getSystemCode()),null);
    }

}
