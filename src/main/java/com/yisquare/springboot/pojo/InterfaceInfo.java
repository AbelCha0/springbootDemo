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
public class InterfaceInfo {
    @ApiModelProperty("API在数据库中的ID")
    private int id;
    @ApiModelProperty("系统编码")
    @NotBlank(message = "系统编码不能为空")
    private String systemCode;
    @ApiModelProperty("API编码")
    @NotBlank(message = "API编码不能为空")
    private String apiCode;
    @ApiModelProperty("API名称")
    @NotBlank(message = "API名称不能为空")
    private String apiName;
    @ApiModelProperty("API版本")
    @NotBlank(message = "版本不能为空")
    private float version;
    @ApiModelProperty("API文档地址")
    private String apiDocUrl;


}
