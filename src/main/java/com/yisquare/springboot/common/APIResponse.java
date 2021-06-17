package com.yisquare.springboot.common;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;

@ApiModel("api通用返回数据")
@Data
@NoArgsConstructor
public class APIResponse<T> implements Serializable {


    private static final long serialVersionUID = -4712090527721947837L;
    @ApiModelProperty("标识代码，success表示成功，fail表示失败")
    private String status;  //success or fail
    @ApiModelProperty("提示信息，出错的时候可以使用")
    private String msg;
    @ApiModelProperty("返回的数据")
    private T data;


    public static <T> APIResponse<T> success(T data){
        APIResponse<T> apiResponse = new APIResponse<>();
        apiResponse.setStatus("success");
        apiResponse.setMsg("成功");
        apiResponse.setData(data);
        return apiResponse;
    }

    public static <T> APIResponse<T> fail(String msg, T data){
        APIResponse<T> apiResponse = new APIResponse<>();
        apiResponse.setStatus("fail");
        apiResponse.setMsg(msg);
        apiResponse.setData(data);
        return apiResponse;
    }

}

