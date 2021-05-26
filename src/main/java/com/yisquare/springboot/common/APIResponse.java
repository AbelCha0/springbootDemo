package com.yisquare.springboot.common;

import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;

@Data
@NoArgsConstructor
public class APIResponse<T> implements Serializable {


    private static final long serialVersionUID = -4712090527721947837L;
    private String status;  //success or fail
    private String msg;
    private T data;


    public static <T> APIResponse<T> success(T data){
        APIResponse<T> apiResponse = new APIResponse();
        apiResponse.setStatus("success");
        apiResponse.setMsg("成功");
        apiResponse.setData(data);
        return apiResponse;
    }

    public static <T> APIResponse fail(String msg, T data){
        APIResponse<T> apiResponse = new APIResponse();
        apiResponse.setStatus("fail");
        apiResponse.setMsg(msg);
        apiResponse.setData(data);
        return apiResponse;
    }

}

