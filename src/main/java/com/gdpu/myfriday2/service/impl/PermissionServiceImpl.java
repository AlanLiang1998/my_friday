package com.gdpu.myfriday2.service.impl;

import com.alibaba.fastjson.JSONArray;
import com.gdpu.myfriday2.dao.PermissionMapper;
import com.gdpu.myfriday2.dao.RolePermissionMapper;
import com.gdpu.myfriday2.model.Permission;
import com.gdpu.myfriday2.model.RolePermissionExample;
import com.gdpu.myfriday2.model.RolePermissionKey;
import com.gdpu.myfriday2.service.PermissionService;
import com.gdpu.myfriday2.utils.PermissionTreeUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

/**
 * @Descriptin TODO
 * @Author AlanLiang
 * Date 2020/3/29 22:26
 * Version 1.0
 **/
@Service
@Transactional(rollbackFor = Exception.class)
public class PermissionServiceImpl implements PermissionService {

    @Autowired
    private PermissionMapper permissionMapper;
    @Autowired
    private RolePermissionMapper rolePermissionMapper;

    @Override
    public JSONArray listByUserId(Long userId) {
        JSONArray array = new JSONArray();
        List<Permission> permissions = permissionMapper.listByUserId(userId);
        permissions = permissions.stream().filter(p -> p.getType().equals(1)).collect(Collectors.toList());
        PermissionTreeUtil.setPermissionTree(0, permissions, array);
        return array;
    }

    @Override
    public int delete(Long id) {
        return permissionMapper.deleteByPrimaryKey(id);
    }

    @Override
    public int update(Permission permission) {
        return permissionMapper.updateByPrimaryKeySelective(permission);
    }

    @Override
    @Transactional(readOnly = true)
    public Permission queryById(Long id) {
        return permissionMapper.selectByPrimaryKey(id);
    }

    @Override
    public int create(Permission permission) {
        return permissionMapper.insertSelective(permission);
    }

    @Override
    @Transactional(readOnly = true)
    public List<Permission> queryAll() {
        return permissionMapper.selectByExample(null);
    }

    @Override
    @Transactional(readOnly = true)
    public List<Long> listByRoleId(Long roleId) {
        //根据角色ID查出所有权限ID
        RolePermissionExample rolePermissionExample = new RolePermissionExample();
        rolePermissionExample.createCriteria().andRoleIdEqualTo(roleId);
        List<RolePermissionKey> rolePermissionKeys = rolePermissionMapper.selectByExample(rolePermissionExample);
        ArrayList<Long> permissionIds = new ArrayList<>();
        for (RolePermissionKey rolePermissionKey : rolePermissionKeys) {
            permissionIds.add(rolePermissionKey.getPermissionId());
        }
        return permissionIds;
    }

    @Override
    @Transactional(readOnly = true)
    public JSONArray list() {
        List<Permission> permissions = permissionMapper.selectByExample(null);
        JSONArray array = new JSONArray();
        PermissionTreeUtil.setPermissionTree(0, permissions, array);
        return array;
    }
}
