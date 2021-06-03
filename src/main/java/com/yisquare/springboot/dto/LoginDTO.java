package com.yisquare.springboot.dto;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import javax.validation.constraints.NotBlank;

@ApiModel("登录信息")
@Data
public class LoginDTO {
    @ApiModelProperty("登录的用户编码")
    @NotBlank(message = "用户编码不能为空")
    private String userCode;
    @ApiModelProperty("登录的用户密码")
    @NotBlank(message = "密码不能为空")
    private String userPassword;
}
