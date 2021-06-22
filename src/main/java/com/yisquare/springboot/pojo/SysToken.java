package com.yisquare.springboot.pojo;


import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

import java.io.Serializable;
import java.time.LocalDateTime;

@ApiModel("登录后的返回信息")
@Data
@NoArgsConstructor
@ToString
public class SysToken implements Serializable {
    private static final long serialVersionUID = -5162772653464312687L;
    @ApiModelProperty("用户代码")
    private String userCode;
    @ApiModelProperty("用户名称")
    private String userName;
    @ApiModelProperty("token信息")
    private String token;
    @ApiModelProperty("过期时间")
    private LocalDateTime expireDateTime;
    @ApiModelProperty("用户角色，0超级管理员，1系统管理员，2普通用户")
    private int roleID;
}
