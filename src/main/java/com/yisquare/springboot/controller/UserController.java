package com.yisquare.springboot.controller;

import com.github.pagehelper.PageInfo;
import com.yisquare.springboot.common.APIResponse;
import com.yisquare.springboot.common.PasswordProcess;
import com.yisquare.springboot.dao.query.QueryCondition;
import com.yisquare.springboot.dto.LoginDTO;
import com.yisquare.springboot.pojo.User;
import com.yisquare.springboot.service.UserService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;


import org.apache.shiro.SecurityUtils;
import org.apache.shiro.authz.annotation.Logical;
import org.apache.shiro.authz.annotation.RequiresRoles;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
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
    public APIResponse<User> createUser(@RequestBody @Validated User user, BindingResult bindingResult){
        if (bindingResult.hasErrors()) {
            return APIResponse.fail(bindingResult.getFieldError().getDefaultMessage(),null);
        }
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
        String userPassword = user.getUserPassword();
        if(null != userPassword) {
            user.setUserPassword(PasswordProcess.makeMD5(userPassword));
        }
        return userService.updateUserByPatch(user);
    }

    @ApiOperation(value = "修改用户密码",notes = "修改用户密码")
    @PostMapping(value = "/user/changePwd",consumes = "application/x-www-form-urlencoded")
    public APIResponse<Boolean> updateUserPassword(@RequestParam String oldPassword, @RequestParam String newPassword){
        User loginUser = (User) SecurityUtils.getSubject().getPrincipal();
        if(loginUser != null){
           User user = userService.login(new LoginDTO(loginUser.getUserCode(), oldPassword));
           if(user!= null){
               user.setUserPassword(PasswordProcess.makeMD5(newPassword));
               userService.updateUserByPatch(user);
               return APIResponse.success(null);
           }
        }
            return APIResponse.fail("修改密码失败，请检查原密码是否正确。",null);

    }


}
