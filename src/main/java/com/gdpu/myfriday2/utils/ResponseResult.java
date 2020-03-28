package com.gdpu.myfriday2.utils;

import lombok.Data;

import java.io.Serializable;

/**
 * @Descriptin TODO
 * @Author AlanLiang
 * Date 2020/3/27 19:23
 * Version 1.0
 **/
@Data
public class ResponseResult<T> implements Serializable {
    private Integer code;

    private String msg;

    private Long count;

    private T data;

    public static <T> ResponseResult<T> tableSuccess(T data, Long count) {
        ResponseResult<T> result = new ResponseResult<>();
        result.setCode(200);
        result.setMsg("请求成功");
        result.setData(data);
        result.setCount(count);
        return result;
    }

    public static <T> ResponseResult<T> success() {
        ResponseResult<T> result = new ResponseResult<>();
        result.setCode(200);
        result.setMsg("请求成功");
        return result;
    }

    public static <T> ResponseResult<T> failure() {
        ResponseResult<T> result = new ResponseResult<>();
        result.setCode(400);
        result.setMsg("请求失败");
        return result;
    }
}
