package com.gdpu.myfriday2.service.impl;

import com.gdpu.myfriday2.dao.RoleMapper;
import com.gdpu.myfriday2.dao.RolePermissionMapper;
import com.gdpu.myfriday2.dto.KeywordDto;
import com.gdpu.myfriday2.dto.RoleDto;
import com.gdpu.myfriday2.exception.EntityExistException;
import com.gdpu.myfriday2.model.*;
import com.gdpu.myfriday2.service.RoleService;
import com.github.pagehelper.PageHelper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.CollectionUtils;

import java.util.ArrayList;
import java.util.List;

/**
 * @Descriptin TODO
 * @Author AlanLiang
 * Date 2020/3/28 16:06
 * Version 1.0
 **/
@Service
@Transactional(rollbackFor = Exception.class)
public class RoleServiceImpl implements RoleService {

    @Autowired
    private RoleMapper roleMapper;
    @Autowired
    private RolePermissionMapper rolePermissionMapper;

    @Override
    @Transactional(readOnly = true)
    public List<Role> queryAll() {
        return roleMapper.selectByExample(null);
    }

    @Override
    @Transactional(readOnly = true)
    public List<Role> queryAllByKeyword(KeywordDto keywordDto) {
        PageHelper.startPage(keywordDto.getPage(), keywordDto.getLimit());
        String pattern = "[0-9]+";
        if (!"".equals(keywordDto.getKeyword()) && keywordDto.getKeyword().matches(pattern)) {
            //根据角色ID查询
            long roleId = Long.parseLong(keywordDto.getKeyword());
            ArrayList<Role> roles = new ArrayList<>();
            roles.add(roleMapper.selectByPrimaryKey(roleId));
            return roles;
        } else {
            //根据角色名查询
            RoleExample roleExample = new RoleExample();
            roleExample.createCriteria().andRoleNameLike("%" + keywordDto.getKeyword() + "%");
            return roleMapper.selectByExample(roleExample);
        }
    }

    @Override
    @Transactional(readOnly = true)
    public long countAllByKeyword(KeywordDto keywordDto) {
        String pattern = "[0-9]+";
        if (!"".equals(keywordDto.getKeyword()) && keywordDto.getKeyword().matches(pattern)) {
            //根据角色ID查询
            RoleExample roleExample = new RoleExample();
            long roleId = Long.parseLong(keywordDto.getKeyword());
            roleExample.createCriteria().andRoleIdEqualTo(roleId);
            return roleMapper.countByExample(roleExample);
        } else {
            //根据角色名查询
            RoleExample roleExample = new RoleExample();
            roleExample.createCriteria().andRoleNameLike("%" + keywordDto.getKeyword() + "%");
            return roleMapper.countByExample(roleExample);
        }
    }


    /**
     * 创建角色
     *
     * @param roleDto 角色DTO
     * @return 创建结果
     */
    @Override
    public int create(RoleDto roleDto) {
        //验证角色名是否唯一
        RoleExample roleExample = new RoleExample();
        roleExample.createCriteria().andRoleNameEqualTo(roleDto.getRoleName());
        if (!CollectionUtils.isEmpty(roleMapper.selectByExample(roleExample))) {
            throw new EntityExistException("角色名:" + roleDto.getRoleName() + " 已存在");
        }
        //先创建角色
        int result1 = roleMapper.insertSelective(roleDto);
        if (result1 == 0) {
            return 0;
        }
        //再保存角色权限记录
        List<Long> permissionIds = roleDto.getPermissionIds();
        //移除根节点
        if (!CollectionUtils.isEmpty(permissionIds)) {
            permissionIds.remove(0);
        }
        int result2 = rolePermissionMapper.insertBatch(roleDto.getRoleId(), permissionIds);
        if (result2 != permissionIds.size()) {
            return 0;
        }
        return 1;
    }

    @Override
    @Transactional(readOnly = true)
    public long countAll() {
        return roleMapper.countByExample(null);
    }
}
