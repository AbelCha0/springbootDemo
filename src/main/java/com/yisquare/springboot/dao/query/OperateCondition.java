package com.yisquare.springboot.dao.query;


import com.yisquare.springboot.common.constraint.Operate;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class OperateCondition {
    private String userCode;
    private String systemCode;
    private Operate operate;
}
