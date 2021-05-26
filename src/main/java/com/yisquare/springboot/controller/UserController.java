package com.yisquare.springboot.controller;

import com.github.pagehelper.PageInfo;
import com.yisquare.springboot.common.APIResponse;
import com.yisquare.springboot.dao.query.QueryCondition;
import com.yisquare.springboot.pojo.User;
import com.yisquare.springboot.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import java.util.List;


@RestController
public class UserController {
    @Autowired
    private UserService userService;

    @GetMapping(value = "/list")
    public APIResponse<List<User>> getUser(){

        return userService.listUsers();
    }

    @PostMapping(value = "/queryUser", consumes = "application/json")
    public APIResponse<PageInfo<User>> queryUserByCode(@RequestBody QueryCondition userQuery){
        return userService.listUsersByUserCode(userQuery);
    }

    @GetMapping(value = "/user/{userCode}")
    public APIResponse<User> getUserInfo(@PathVariable("userCode") String userCode){
        return userService.getUserInfo(userCode);
    }

    @DeleteMapping(value = "/user/{userCode}")
    public APIResponse<Boolean> deleteUser(@PathVariable("userCode") String userCode){
        return userService.deleteUserByCode(userCode);
    }

    @PostMapping(value = "/user",consumes = "application/json")
    public APIResponse<User> createUser(@RequestBody User user){
        return userService.addUser(user);
    }

    @PutMapping(value = "/user",consumes = "application/json")
    public APIResponse<Boolean> updateUser(@RequestBody User user){
        return userService.updateUser(user);
    }

    @PatchMapping(value = "/user",consumes = "application/json")
    public APIResponse<Boolean> updateUserByPatch(@RequestBody User user){
        return userService.updateUserByPatch(user);
    }


}