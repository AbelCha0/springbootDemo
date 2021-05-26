package com.yisquare.springboot.pojo;


import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

import java.io.Serializable;

@Data
@AllArgsConstructor
@NoArgsConstructor
@ToString
public class User implements Serializable {
    private static final long serialVersionUID = 6002302333073323632L;
    private String userCode;
    private String userName;
    private String userPassword;
    private String phone;
    private String email;
    private String createDateTime;

}
