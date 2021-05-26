package com.yisquare.springboot.service;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.yisquare.springboot.common.APIResponse;
import com.yisquare.springboot.dao.UserDao;
import com.yisquare.springboot.dao.query.QueryCondition;
import com.yisquare.springboot.pojo.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class UserServiceImpl implements UserService{
    @Autowired
    private UserDao userDao;

    @Override
    public APIResponse<List<User>> listUsers() {

        return APIResponse.success(userDao.listUsers());
    }

    //根据userCode查询用户，如果不输入条件，则查询所有用户
    @Override
    public APIResponse<PageInfo<User>> listUsersByUserCode(QueryCondition userQuery) {
        PageHelper.startPage(userQuery.getPageNum(),userQuery.getPageSize());
        return  APIResponse.success(new PageInfo<User>(userDao.listUsersByUserCode(userQuery)));
    }

    @Override
    public APIResponse<User> getUserInfo(String userCode) {
        return APIResponse.success(userDao.getUserInfo(userCode));
    }

    @Override
    public APIResponse<Boolean> deleteUserByCode(String userCode) {
        int j = userDao.getSystemCodeByUserCode(userCode);
        if(j > 0){
            return APIResponse.fail("用户存在关联的系统，不能删除",null);
        }
        int i  = userDao.deleteUserByCode(userCode);
        if(i >0) {
            return APIResponse.success(true);
        }else{
            return APIResponse.fail("删除失败",false);
        }
    }

    @Override
    public APIResponse<User> addUser(User user) {
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
}
