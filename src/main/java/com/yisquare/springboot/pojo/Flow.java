package com.yisquare.springboot.pojo;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

import javax.validation.constraints.NotBlank;
import java.io.Serializable;

@ApiModel
@Data
@NoArgsConstructor
@ToString
public class Flow implements Serializable {
    private static final long serialVersionUID = -8113664244633052678L;
    @ApiModelProperty("webmethods Flow Service在数据库中的ID")
    private int id;
    @ApiModelProperty("系统编码")
    @NotBlank(message = "系统编码不能为空")
    private String systemCode;
    @ApiModelProperty("系统名称")
    private String systemName;
    @ApiModelProperty("FlowService限定名")
    @NotBlank(message = "FlowService不能为空")
    private String flowServiceName;
    @ApiModelProperty("描述")
    private String description;
}
