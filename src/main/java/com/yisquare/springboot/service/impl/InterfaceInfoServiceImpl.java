package com.yisquare.springboot.service.impl;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.yisquare.springboot.common.APIResponse;
import com.yisquare.springboot.dao.InterfaceDao;
import com.yisquare.springboot.dao.SystemDao;
import com.yisquare.springboot.dao.query.QueryCondition;
import com.yisquare.springboot.pojo.InterfaceInfo;
import com.yisquare.springboot.pojo.User;
import com.yisquare.springboot.service.InterfaceInfoService;
import org.apache.catalina.security.SecurityUtil;
import org.apache.shiro.SecurityUtils;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;

@Service
public class InterfaceInfoServiceImpl implements InterfaceInfoService {
    @Resource
    private InterfaceDao interfaceDao;

    @Resource
    private SystemDao systemDao;

    @Override
    public APIResponse<PageInfo<InterfaceInfo>> listInterfaceInfo(QueryCondition queryCondition) {
        PageHelper.startPage(queryCondition.getPageNum(),queryCondition.getPageSize());
        return APIResponse.success(new PageInfo<>(interfaceDao.listInterface(queryCondition)));

    }

    @Override
    public APIResponse<Boolean> deleteInterfaceInfo(int id) {
        InterfaceInfo interfaceInfo = interfaceDao.getInterfaceInfoByID(id);
        if (interfaceInfo == null) {
            return APIResponse.fail(String.format("interface id :%s不存在", id), false);
        }


        int result = interfaceDao.deleteInterfaceByID(id);
        if (result == 0) {
            return APIResponse.fail("删除API失败,请检查该API是否存在", false);
        } else {
            return APIResponse.success(true);
        }
    }



    @Override
    public APIResponse<Boolean>  updateInterfaceInfo(InterfaceInfo interfaceInfo) {

        int result = interfaceDao.updateInterface(interfaceInfo);
        if (result == 0) {
            return APIResponse.fail("删除Flow失败", null);
        } else {
            return APIResponse.success(true);
        }
    }



    @Override
    public APIResponse<Boolean>  addInterfaceInfo(InterfaceInfo interfaceInfo) {
        if(interfaceInfo != null && null ==systemDao.getSystemInfoByCode(interfaceInfo.getSystemCode())){
            return APIResponse.fail(String.format("系统编码%s不存在"),null);
        }
        if (null != interfaceDao.getInterfaceInfoByCode(interfaceInfo.getSystemCode(), interfaceInfo.getApiCode())) {
            return APIResponse.fail(String.format("系统编码%s下已经存在接口%s.", interfaceInfo.getSystemCode(), interfaceInfo.getApiCode()), null);
        }
        User user = (User) SecurityUtils.getSubject().getPrincipal();
        interfaceInfo.setCreateUser(user.getUserName());

        int result = interfaceDao.addInterface(interfaceInfo);
        if (result == 0) {
            return APIResponse.fail("新增Interface失败", null);
        } else {
            return APIResponse.success(true);
        }
    }


}
