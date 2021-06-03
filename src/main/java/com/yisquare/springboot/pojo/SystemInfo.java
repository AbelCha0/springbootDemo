package com.yisquare.springboot.pojo;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

import javax.validation.constraints.NotBlank;
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
    @ApiModelProperty("系统成员")
    private List<SystemMember> systemMember;
    @ApiModelProperty("系统IP地址")
    private String[] ipAddress;
}
