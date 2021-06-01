package com.yisquare.springboot.pojo;


import lombok.Data;

import java.time.LocalDateTime;

@Data
public class SysToken {
    private String userCode;
    private String token;
    private LocalDateTime expireDateTime;
    private int roleID;
}
