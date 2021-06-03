package com.yisquare.springboot.pojo;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

import javax.validation.constraints.NotBlank;

@ApiModel
@Data
@NoArgsConstructor
@ToString
public class Flow {
    @ApiModelProperty("webmethods Flow Service在数据库中的ID")
    private int id;
    @ApiModelProperty("系统编码")
    @NotBlank(message = "系统编码不能为空")
    private String systemCode;
    @ApiModelProperty("FlowService限定名")
    @NotBlank(message = "FlowService不能为空")
    private String flowServiceName;
    @ApiModelProperty("描述")
    private String description;
}
