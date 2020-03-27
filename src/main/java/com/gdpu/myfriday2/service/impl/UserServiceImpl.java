package com.gdpu.myfriday2.service.impl;

import com.gdpu.myfriday2.dao.UserMapper;
import com.gdpu.myfriday2.dto.PageDto;
import com.gdpu.myfriday2.model.User;
import com.gdpu.myfriday2.service.UserService;
import com.github.pagehelper.PageHelper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * @Descriptin TODO
 * @Author AlanLiang
 * Date 2020/3/27 18:37
 * Version 1.0
 **/
@Service
public class UserServiceImpl implements UserService {

    @Autowired
    private UserMapper userMapper;

    /**
     * 分页查询所有用户
     *
     * @param pageDto 分页数据DTO
     * @return 用户列表
     */
    @Override
    public List<User> queryAllByPage(PageDto pageDto) {
        PageHelper.startPage(pageDto.getPage(), pageDto.getLimit());
        List<User> users = userMapper.selectWithRoleByExample(null);
        return users;
    }

    /**
     * 查询用户总数
     *
     * @return 用户总数
     */
    @Override
    public long countAll() {
        return userMapper.countByExample(null);
    }
}
