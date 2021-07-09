package com.yisquare.springboot.pojo;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

import javax.validation.constraints.Min;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Pattern;
import java.io.Serializable;

@ApiModel
@Data
@NoArgsConstructor
@ToString
public class InterfaceInfo implements Serializable {
    private static final long serialVersionUID = 4140498806255456821L;
    @ApiModelProperty("API在数据库中的ID")
    private int id;
    @ApiModelProperty("系统编码")
    @NotBlank(message = "系统编码不能为空")
    private String systemCode;
    @ApiModelProperty("关联系统编码")
    private String relationSystemCode;
    @ApiModelProperty("API编码")
    @NotBlank(message = "API编码不能为空")
    private String apiCode;
    @ApiModelProperty("API名称")
    @NotBlank(message = "API名称不能为空")
    private String apiName;
    @ApiModelProperty("API版本")
    @NotBlank(message = "版本不能为空")
    private String version;
    @ApiModelProperty("API文档地址")
    //@NotBlank(message = "api文档地址不能为空")
    @Pattern(regexp = "^(http|https):\\/\\/([\\w.]+\\/?)\\S*", message = "api文档地址必须是http或者https开头的地址")
    private String apiDocUrl;
    @ApiModelProperty("接口状态，0启用，1禁用")
    @Min(value = 0,message = "接口状态的值为0或者2")
    @Min(value = 1,message = "接口状态的值为0或者1")
    private int status;
    @ApiModelProperty("创建者")
    private String createUser;
    @ApiModelProperty("创建时间")
    private String createDateTime;



}
