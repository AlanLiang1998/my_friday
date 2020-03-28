package com.gdpu.myfriday2.service.impl;

import com.gdpu.myfriday2.dao.RoleMapper;
import com.gdpu.myfriday2.model.Role;
import com.gdpu.myfriday2.service.RoleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

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
}
