package com.yisquare.springboot.pojo;

import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

import java.io.Serializable;
import java.util.List;

@Data
@NoArgsConstructor
@ToString
public class SystemInfo implements Serializable {
    private static final long serialVersionUID = 465810350170950576L;
    private String systemCode;
    private String systemName;
    private String department;
    private String createDatetime;
    private User owner;
    private List<SystemMember> systemMember;
    private String[] ipAddress;
}
