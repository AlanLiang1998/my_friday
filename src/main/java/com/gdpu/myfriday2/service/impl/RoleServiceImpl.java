package com.gdpu.myfriday2.service.impl;

import com.gdpu.myfriday2.dao.RoleMapper;
import com.gdpu.myfriday2.dto.KeywordDto;
import com.gdpu.myfriday2.model.Role;
import com.gdpu.myfriday2.model.RoleExample;
import com.gdpu.myfriday2.service.RoleService;
import com.github.pagehelper.PageHelper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;

/**
 * @Descriptin TODO
 * @Author AlanLiang
 * Date 2020/3/28 16:06
 * Version 1.0
 **/
@Service
@Transactional
public class RoleServiceImpl implements RoleService {

    @Autowired
    private RoleMapper roleMapper;

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

    @Override
    @Transactional(readOnly = true)
    public long countAll() {
        return roleMapper.countByExample(null);
    }
}
