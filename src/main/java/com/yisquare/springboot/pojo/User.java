package com.yisquare.springboot.pojo;


import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

import javax.validation.constraints.*;
import java.io.Serializable;

@ApiModel("用户信息")
@Data
@AllArgsConstructor
@NoArgsConstructor
@ToString
public class User implements Serializable {
    private static final long serialVersionUID = 6002302333073323632L;
    @ApiModelProperty("用户编码，该编码具有唯一性")
    @NotEmpty(message = "用户编码不能为空")
    @Size(min = 3, max = 20, message = "用户名应该在3-20字符之间")
    private String userCode;
    @ApiModelProperty("用户名称")
    private String userName;
    @ApiModelProperty("用户密码")
    @NotEmpty(message = "用户密码不能为空")
    @Size(min = 6, max = 20, message = "用户密码应该在6-20字符之间")
    private String userPassword;
    @ApiModelProperty("联系电话")
    @NotEmpty(message = "电话不能为空")
    @Pattern(regexp = "^1[0-9]{10}$", message = "手机号不合法")
    private String phone;
    @ApiModelProperty("邮件地址")
    @NotNull(message = "邮箱地址不能为空")
    @Email(message = "请输入正确的邮箱地址")
    private String email;
    @Min(value = 1,message = "角色ID的值为1或者2")
    @Max(value = 2,message = "角色ID的值为1或者2")
    @ApiModelProperty("用户角色，1系统管理员，2普通用户")
    private int roleID;
    @ApiModelProperty("创建时间")
    private String createDateTime;

}
