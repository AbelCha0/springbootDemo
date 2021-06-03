package com.yisquare.springboot.pojo;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

import java.io.Serializable;

@ApiModel
@Data
@NoArgsConstructor
@ToString
public class SystemMember implements Serializable {
    private static final long serialVersionUID = 8891211228421223358L;
    @ApiModelProperty("系统编码")
    private String systemCode;
    @ApiModelProperty("成员的角色，1系统管理员，2系统负责人，3普通用户")
    private int role;
    @ApiModelProperty("成员的用户编码")
    private String memberCode;
}
