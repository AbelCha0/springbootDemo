package com.yisquare.springboot.pojo;


import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;
import java.io.Serializable;

@ApiModel("用户信息")
@Data
@AllArgsConstructor
@NoArgsConstructor
@ToString
public class User implements Serializable {
    private static final long serialVersionUID = 6002302333073323632L;
    @ApiModelProperty("用户编码，该编码具有唯一性")
    @NotBlank(message = "用户编码不能为空")
    private String userCode;
    @ApiModelProperty("用户名称")
    private String userName;
    @ApiModelProperty("用户密码")
    @NotBlank(message = "用户密码不能为空")
    private String userPassword;
    @ApiModelProperty("联系电话")
    @NotBlank(message = "电话不能为空")
    private String phone;
    @ApiModelProperty("邮件地址")
    @Email(message = "请输入正确的邮箱地址")
    private String email;
    @ApiModelProperty("创建时间")
    private String createDateTime;

}
