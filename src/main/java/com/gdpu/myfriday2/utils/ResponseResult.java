package com.gdpu.myfriday2.utils;

import lombok.Data;

/**
 * @Descriptin TODO
 * @Author AlanLiang
 * Date 2020/3/27 19:23
 * Version 1.0
 **/
@Data
public class ResponseResult<T> {
    private Integer code;

    private String msg;

    private Long count;

    private T data;

    public ResponseResult(T data, Long count) {
        this.setData(data);
        this.setCount(count);
    }

    public ResponseResult success() {
        this.setCode(200);
        this.setMsg("请求成功");
        return this;
    }

    public ResponseResult failure() {
        this.setCode(400);
        this.setMsg("请求失败");
        return this;
    }
}
