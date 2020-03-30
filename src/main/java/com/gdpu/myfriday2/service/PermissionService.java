package com.gdpu.myfriday2.service;

import com.alibaba.fastjson.JSONArray;

/**
 * @Descriptin TODO
 * @Author AlanLiang
 * Date 2020/3/29 22:24
 * Version 1.0
 **/
public interface PermissionService {
    /**
     * 查询所有权限
     *
     * @return 权限列表
     */
    JSONArray list();
}
