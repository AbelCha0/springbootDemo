package com.yisquare.springboot.dao.query;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class QueryCondition {
    private Integer pageNum =1;
    private Integer pageSize = 2;
    private String code;
}
