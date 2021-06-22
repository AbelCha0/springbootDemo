package com.yisquare.springboot.dao.query;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.NotBlank;

@ApiModel("分页查询条件")
@Data
@NoArgsConstructor
@AllArgsConstructor
public class QueryCondition {
    @ApiModelProperty("页码")
    private Integer pageNum =1;
    @ApiModelProperty("一个分页的记录条数")
    private Integer pageSize = 20;
    @ApiModelProperty("系统编码，查询接口和FlowService使用")
    private String systemCode;
    @ApiModelProperty("查询关键字")
    private String queryStr;
    private String operateUserCode;
}
