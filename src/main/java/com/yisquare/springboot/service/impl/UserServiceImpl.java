package com.yisquare.springboot.service.impl;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.yisquare.springboot.common.APIResponse;
import com.yisquare.springboot.common.PasswordProcess;
import com.yisquare.springboot.dao.UserDao;
import com.yisquare.springboot.dao.query.QueryCondition;
import com.yisquare.springboot.dto.LoginDTO;

import com.yisquare.springboot.pojo.User;
import com.yisquare.springboot.service.UserService;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;

@Service
public class UserServiceImpl implements UserService {
    @Resource
    private UserDao userDao;

    @Override
    public APIResponse<List<User>> listUsers() {

        return APIResponse.success(userDao.listUsers());
    }

    //根据userCode或用户名查询用户，如果不输入条件，则查询所有用户
    @Override
    public APIResponse<PageInfo<User>> queryUser(QueryCondition userQuery) {
        PageHelper.startPage(userQuery.getPageNum(),userQuery.getPageSize());
        return  APIResponse.success(new PageInfo<>(userDao.queryUser(userQuery)));
    }

    @Override
    public APIResponse<User> getUserInfo(String userCode) {

        return APIResponse.success(userDao.getUserInfo(userCode));
    }

    @Override
    public APIResponse<Boolean> deleteUserByCode(String userCode) {

        int i  = userDao.deleteUserByCode(userCode);
        if(i >0) {
            return APIResponse.success(true);
        }else{
            return APIResponse.fail("删除失败",false);
        }
    }

    @Override
    public APIResponse<User> addUser(User user) {

        if(null != userDao.getUserInfo(user.getUserCode())){
            return APIResponse.fail(String.format("用户编码%s已经存在",user.getUserCode()),null);
        }

        user.setUserPassword(PasswordProcess.makeMD5(user.getUserPassword()));
        int i =  userDao.addUser(user);
        if(i>0) {
            return APIResponse.success(user);
        }else{
            return APIResponse.fail("新增用户失败",user);
        }
    }

    @Override
    public APIResponse<Boolean> updateUser(User user) {
        int i = userDao.updateUser(user);
        if(i>0) {
            return APIResponse.success(true);
        }else{
            return APIResponse.fail("更新用户失败",false);
        }
    }

    @Override
    public APIResponse<Boolean> updateUserByPatch(User user) {
        int i = userDao.updateUserByPatch(user);
        if(i>0) {
            return APIResponse.success(true);
        }else{
            return APIResponse.fail("更新用户失败",false);
        }
    }

    @Override
    public User login(LoginDTO loginDTO) {
        String userCode = loginDTO.getUserCode();
        String password = PasswordProcess.makeMD5(loginDTO.getUserPassword());

        User user = userDao.getUserInfo(userCode);

        if (null != user && password.equals(user.getUserPassword())) {
            return user;
        } else {
            return null;
        }
    }

}
