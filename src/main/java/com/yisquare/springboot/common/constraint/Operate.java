package com.yisquare.springboot.common.constraint;

public enum Operate {
    SELECT("select"),
    UPDATE("update"),
    INSERT("insert"),
    DELETE("delete");

    private String operate;

    Operate(String operate) {
        this.operate = operate;
    }

    public String getOperate(){
        return this.operate;
    }
}
