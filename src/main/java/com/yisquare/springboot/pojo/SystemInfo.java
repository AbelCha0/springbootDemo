package com.yisquare.springboot.pojo;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

import javax.validation.constraints.*;
import java.io.Serializable;
import java.util.List;

@ApiModel
@Data
@NoArgsConstructor
@ToString
public class SystemInfo implements Serializable {
    private static final long serialVersionUID = 465810350170950576L;
    @ApiModelProperty("系统编码")
    @NotBlank(message = "系统编码不能为空")
    private String systemCode;
    @ApiModelProperty("系统名称")
    @NotBlank(message = "系统名称不能为空")
    private String systemName;
    @ApiModelProperty("部门信息")
    private String department;
    @ApiModelProperty("系统创建时间")
    private String createDatetime;
    @ApiModelProperty("负责人姓名")
    @NotBlank(message = "负责人不能为空")
    private String owner;
    @ApiModelProperty("负责人电话")
    @NotEmpty(message = "电话不能为空")
    @Pattern(regexp = "^1[0-9]{10}$", message = "手机号不合法")
    private String ownerPhone;
    @ApiModelProperty("负责人邮件")
    @NotNull(message = "邮箱地址不能为空")
    @Email(message = "请输入正确的邮箱地址")
    private String ownerEmail;
    @ApiModelProperty("系统创建人")
    private String createUser;
    @ApiModelProperty("系统IP地址")
    private String[] ipAddress;
}
