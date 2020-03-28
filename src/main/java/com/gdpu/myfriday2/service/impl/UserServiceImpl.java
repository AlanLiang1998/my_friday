package com.gdpu.myfriday2.service.impl;

import com.gdpu.myfriday2.dao.RoleUserMapper;
import com.gdpu.myfriday2.dao.UserMapper;
import com.gdpu.myfriday2.dto.KeywordDto;
import com.gdpu.myfriday2.dto.PageDto;
import com.gdpu.myfriday2.dto.UserDto;
import com.gdpu.myfriday2.exception.EntityExistException;
import com.gdpu.myfriday2.model.RoleUserKey;
import com.gdpu.myfriday2.model.User;
import com.gdpu.myfriday2.model.UserExample;
import com.gdpu.myfriday2.service.UserService;
import com.github.pagehelper.PageHelper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.CollectionUtils;

import java.util.ArrayList;
import java.util.List;

/**
 * @Descriptin 用户服务类
 * @Author AlanLiang
 * Date 2020/3/27 18:37
 * Version 1.0
 **/
@Service
@Transactional(rollbackFor = Exception.class)
public class UserServiceImpl implements UserService {

    @Autowired
    private UserMapper userMapper;
    @Autowired
    private RoleUserMapper roleUserMapper;

    /**
     * 分页查询所有用户
     *
     * @param pageDto 分页数据DTO
     * @return 用户列表
     */
    @Override
    @Transactional(readOnly = true)
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
    @Transactional(readOnly = true)
    public long countAll() {
        return userMapper.countByExample(null);
    }

    /**
     * 新增用户
     *
     * @param userDto 新用户DTO
     * @return 新增结果
     */
    @Override
    public int create(UserDto userDto) throws EntityExistException {
        //验证用户名是否唯一
        UserExample userExample = new UserExample();
        userExample.createCriteria().andUsernameEqualTo(userDto.getUsername());
        if (!CollectionUtils.isEmpty(userMapper.selectByExample(userExample))) {
            throw new EntityExistException("用户名:" + userDto.getUsername() + " 已被注册");
        }
        //验证手机号码是否唯一
        userExample.clear();
        userExample.createCriteria().andPhoneEqualTo(userDto.getPhone());
        if (!CollectionUtils.isEmpty(userMapper.selectByExample(userExample))) {
            throw new EntityExistException("手机号码:" + userDto.getPhone() + " 已被注册");
        }
        //验证邮箱是否唯一
        userExample.clear();
        userExample.createCriteria().andEmailEqualTo(userDto.getEmail());
        if (!CollectionUtils.isEmpty(userMapper.selectByExample(userExample))) {
            throw new EntityExistException("邮箱:" + userDto.getEmail() + " 已被注册");
        }

        //通过验证
        //首先添加用户
        int result1 = userMapper.insertSelective(userDto);
        //再添加用户角色关联
        RoleUserKey roleUserKey = new RoleUserKey();
        roleUserKey.setRoleId(userDto.getRoleId());
        roleUserKey.setUserId(userDto.getUserId());
        int result2 = roleUserMapper.insert(roleUserKey);

        if (result1 != 1 || result2 != 1) {
            return 0;
        }

        return 1;
    }

    /**
     * 根据关键词查询用户
     */
    @Override
    public List<User> queryAllByKeyword(KeywordDto keywordDto) {
        PageHelper.startPage(keywordDto.getPage(), keywordDto.getLimit());
        String pattern = "[0-9]+";
        if (!"".equals(keywordDto.getKeyword()) && keywordDto.getKeyword().matches(pattern)) {
            //根据用户ID查询
            long userId = Long.parseLong(keywordDto.getKeyword());
            ArrayList<User> users = new ArrayList<>();
            users.add(userMapper.selectWithRoleByPrimaryKey(userId));
            return users;
        } else {
            //根据用户名查询
            UserExample userExample = new UserExample();
            userExample.createCriteria().andUsernameLike("%" + keywordDto.getKeyword() + "%");
            return userMapper.selectWithRoleByExample(userExample);
        }
    }

    @Override
    public long countAllByKeyword(KeywordDto keywordDto) {
        String pattern = "[0-9]+";
        if (!"".equals(keywordDto.getKeyword()) && keywordDto.getKeyword().matches(pattern)) {
            //根据用户ID查询
            UserExample userExample = new UserExample();
            long userId = Long.parseLong(keywordDto.getKeyword());
            userExample.createCriteria().andUserIdEqualTo(userId);
            return userMapper.countByExample(userExample);
        } else {
            //根据用户名查询
            UserExample userExample = new UserExample();
            userExample.createCriteria().andUsernameLike("%" + keywordDto.getKeyword() + "%");
            return userMapper.countByExample(userExample);
        }
    }
}
