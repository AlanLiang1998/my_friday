package com.gdpu.myfriday2.service.impl;

import com.alibaba.fastjson.JSONArray;
import com.gdpu.myfriday2.dao.PermissionMapper;
import com.gdpu.myfriday2.model.Permission;
import com.gdpu.myfriday2.service.PermissionService;
import com.gdpu.myfriday2.utils.PermissionTreeUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

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

    @Override
    @Transactional(readOnly = true)
    public JSONArray list() {
        List<Permission> permissions = permissionMapper.selectByExample(null);
        JSONArray array = new JSONArray();
        PermissionTreeUtil.getPermissionTree(0, permissions, array);
        return array;
    }
}
