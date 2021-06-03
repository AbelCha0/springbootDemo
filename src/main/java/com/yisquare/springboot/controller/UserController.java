package com.yisquare.springboot.controller;

import com.github.pagehelper.PageInfo;
import com.yisquare.springboot.common.APIResponse;
import com.yisquare.springboot.dao.query.QueryCondition;
import com.yisquare.springboot.pojo.User;
import com.yisquare.springboot.service.UserService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;


import io.swagger.annotations.ApiResponse;
import org.apache.shiro.authz.annotation.Logical;
import org.apache.shiro.authz.annotation.RequiresRoles;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import java.util.List;

@Api(tags = "用户信息管理")
@RestController
@RequestMapping("/api")
public class UserController {

    private UserService userService;
    @Autowired
    public void setUserService(UserService userService) {
        this.userService = userService;
    }
    @ApiOperation("查询所有用户信息")
    @GetMapping(value = "/listAllUser")
    public APIResponse<List<User>> getUser(){

        return userService.listUsers();
    }

    @ApiOperation("分页查询用户信息")
    @PostMapping(value = "/listUser", consumes = "application/json")
    public APIResponse<PageInfo<User>> queryUserByCode(@RequestBody QueryCondition userQuery){
        return userService.listUsersByUserCode(userQuery);
    }

    @ApiOperation("根据用户编码查询用户信息")
    @GetMapping(value = "/user/{userCode}")
    public APIResponse<User> getUserInfo(@PathVariable("userCode") String userCode){
        return userService.getUserInfo(userCode);
    }

    @ApiOperation("根据用户编码删除用户信息")
    @RequiresRoles({"superAdmin"})
    @DeleteMapping(value = "/user/{userCode}")
    public APIResponse<Boolean> deleteUser(@PathVariable("userCode") String userCode){
        return userService.deleteUserByCode(userCode);
    }

    @ApiOperation("新增用户信息")
    @RequiresRoles(value = {"superAdmin","systemAdmin","systemOwner"},logical= Logical.OR)
    @PostMapping(value = "/user",consumes = "application/json")
    public APIResponse<User> createUser(@RequestBody User user){
        return userService.addUser(user);
    }

    @ApiOperation("PUT更新用户信息")
    @RequiresRoles(value = {"superAdmin","systemAdmin","systemOwner"},logical= Logical.OR)
    @PutMapping(value = "/user",consumes = "application/json")
    public APIResponse<Boolean> updateUser(@RequestBody User user){
        return userService.updateUser(user);
    }

    @ApiOperation(value = "PATCH更新用户信息",notes = "传什么字段数据就修改什么数据，不传的字段不修改")
    @RequiresRoles(value = {"superAdmin","systemAdmin","systemOwner"},logical= Logical.OR)
    @PatchMapping(value = "/user",consumes = "application/json")
    public APIResponse<Boolean> updateUserByPatch(@RequestBody User user){
        return userService.updateUserByPatch(user);
    }


}
