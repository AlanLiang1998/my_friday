package com.gdpu.myfriday2.service;

import com.alibaba.fastjson.JSONArray;
import com.gdpu.myfriday2.model.Permission;

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

    /**
     * 查询所有权限
     *
     * @return 权限列表
     */
    List<Permission> queryAll();

    /**
     * 创建权限
     *
     * @param permission 新权限
     * @return 结果
     */
    int create(Permission permission);

    /**
     * 查询指定权限
     *
     * @param id 权限ID
     * @return 权限
     */
    Permission queryById(Long id);

    /**
     * 更新权限
     *
     * @param permission 新权限
     * @return 更新结果
     */
    int update(Permission permission);

    /**
     * 删除权限
     *
     * @param id 权限ID
     * @return 删除结果
     */
    int delete(Long id);
}
