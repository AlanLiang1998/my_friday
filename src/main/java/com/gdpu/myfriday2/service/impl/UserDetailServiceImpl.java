package com.gdpu.myfriday2.service.impl;

import com.gdpu.myfriday2.dao.PermissionMapper;
import com.gdpu.myfriday2.dao.UserMapper;
import com.gdpu.myfriday2.dto.LoginUser;
import com.gdpu.myfriday2.model.Permission;
import com.gdpu.myfriday2.model.User;
import com.gdpu.myfriday2.model.UserExample;
import lombok.SneakyThrows;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.DisabledException;
import org.springframework.security.authentication.LockedException;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.util.CollectionUtils;
import org.springframework.util.StringUtils;

import javax.naming.NameNotFoundException;
import java.util.List;
import java.util.stream.Collectors;

/**
 * @Descriptin TODO
 * @Author AlanLiang
 * Date 2020/3/31 21:07
 * Version 1.0
 **/
@Service
public class UserDetailServiceImpl implements UserDetailsService {

    @Autowired
    private UserMapper userMapper;
    @Autowired
    PermissionMapper permissionMapper;

    @SneakyThrows
    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        UserExample userExample = new UserExample();
        userExample.createCriteria().andUsernameEqualTo(username);
        List<User> users = userMapper.selectByExample(userExample);
        if (users.size() != 1) {
            return null;
        }
        User user = users.get(0);

        LoginUser loginUser = new LoginUser();
        BeanUtils.copyProperties(user, loginUser);
        //查询用户的权限
        List<Permission> permissions = permissionMapper.listByUserId(loginUser.getUserId());
        //过滤空权限
        permissions = permissions.stream().filter(p -> (!StringUtils.isEmpty(p.getPermission()))).collect(Collectors.toList());
        loginUser.setPermissions(permissions);
        return loginUser;
    }
}
