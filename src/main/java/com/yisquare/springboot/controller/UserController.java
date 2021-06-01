package com.yisquare.springboot.controller;

import com.github.pagehelper.PageInfo;
import com.yisquare.springboot.common.APIResponse;
import com.yisquare.springboot.dao.query.QueryCondition;
import com.yisquare.springboot.pojo.User;
import com.yisquare.springboot.service.UserService;
import org.apache.shiro.authz.annotation.Logical;
import org.apache.shiro.authz.annotation.RequiresRoles;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import java.util.List;


@RestController
@RequestMapping("/api")
public class UserController {
    @Autowired
    private UserService userService;

    @GetMapping(value = "/listAllUser")
    public APIResponse<List<User>> getUser(){

        return userService.listUsers();
    }

    @PostMapping(value = "/listUser", consumes = "application/json")
    public APIResponse<PageInfo<User>> queryUserByCode(@RequestBody QueryCondition userQuery){
        return userService.listUsersByUserCode(userQuery);
    }

    @GetMapping(value = "/user/{userCode}")
    public APIResponse<User> getUserInfo(@PathVariable("userCode") String userCode){
        return userService.getUserInfo(userCode);
    }

    @RequiresRoles({"superAdmin"})
    @DeleteMapping(value = "/user/{userCode}")
    public APIResponse<Boolean> deleteUser(@PathVariable("userCode") String userCode){
        return userService.deleteUserByCode(userCode);
    }

    @RequiresRoles(value = {"superAdmin","systemAdmin"},logical= Logical.OR)
    @PostMapping(value = "/user",consumes = "application/json")
    public APIResponse<User> createUser(@RequestBody User user){
        return userService.addUser(user);
    }

    @RequiresRoles(value = {"superAdmin","systemAdmin"},logical= Logical.OR)
    @PutMapping(value = "/user",consumes = "application/json")
    public APIResponse<Boolean> updateUser(@RequestBody User user){
        return userService.updateUser(user);
    }

    @RequiresRoles(value = {"superAdmin","systemAdmin"},logical= Logical.OR)
    @PatchMapping(value = "/user",consumes = "application/json")
    public APIResponse<Boolean> updateUserByPatch(@RequestBody User user){
        return userService.updateUserByPatch(user);
    }


}
