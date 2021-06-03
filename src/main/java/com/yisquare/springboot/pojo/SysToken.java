package com.yisquare.springboot.pojo;


import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

import java.time.LocalDateTime;

@ApiModel("登录后的返回信息")
@Data
@NoArgsConstructor
@ToString
public class SysToken {
    @ApiModelProperty("用户代码")
    private String userCode;
    @ApiModelProperty("token信息")
    private String token;
    @ApiModelProperty("过期时间")
    private LocalDateTime expireDateTime;
    @ApiModelProperty("用户角色，0超级管理员，1系统管理员，2系统负责人，3普通用户")
    private int roleID;
}
