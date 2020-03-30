package com.gdpu.myfriday2.service;

import com.alibaba.fastjson.JSONArray;

import java.util.List;

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

    /**
     * 查询指定角色的权限ID列表
     *
     * @param roleId 角色ID
     * @return 权限ID列表
     */
    List<Long> listByRoleId(Long roleId);
}
