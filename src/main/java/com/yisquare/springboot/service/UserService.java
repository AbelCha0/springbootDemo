package com.yisquare.springboot.service;

import com.github.pagehelper.PageInfo;
import com.yisquare.springboot.common.APIResponse;
import com.yisquare.springboot.dao.query.QueryCondition;
import com.yisquare.springboot.dto.LoginDTO;
import com.yisquare.springboot.pojo.User;

import java.util.List;

public interface UserService {

    APIResponse<List<User>> listUsers();

    APIResponse<PageInfo<User>> listUsersByUserCode(QueryCondition userQuery);

    APIResponse<User> getUserInfo(String userCode);

    APIResponse<Boolean> deleteUserByCode(String userCode);

    APIResponse<User> addUser(User user);

    APIResponse<Boolean> updateUser(User user);

    APIResponse<Boolean> updateUserByPatch(User user);

    User login(LoginDTO loginDTO);

}
