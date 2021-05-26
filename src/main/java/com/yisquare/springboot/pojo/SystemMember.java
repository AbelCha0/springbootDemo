package com.yisquare.springboot.pojo;

import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

import java.io.Serializable;

@Data
@NoArgsConstructor
@ToString
public class SystemMember implements Serializable {
    private static final long serialVersionUID = 8891211228421223358L;
    private String systemCode;
    private int role;
    private String memberCode;
}
