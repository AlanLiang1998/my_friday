package com.gdpu.myfriday2;

import com.alibaba.fastjson.JSONArray;
import com.gdpu.myfriday2.dao.PermissionMapper;
import com.gdpu.myfriday2.dao.RolePermissionMapper;
import com.gdpu.myfriday2.dao.RoleUserMapper;
import com.gdpu.myfriday2.dao.UserMapper;
import com.gdpu.myfriday2.model.Permission;
import com.gdpu.myfriday2.model.RoleUserKey;
import com.gdpu.myfriday2.model.User;
import com.gdpu.myfriday2.utils.PermissionTreeUtil;
import org.junit.jupiter.api.Test;
import org.mybatis.generator.api.MyBatisGenerator;
import org.mybatis.generator.config.Configuration;
import org.mybatis.generator.config.xml.ConfigurationParser;
import org.mybatis.generator.internal.DefaultShellCallback;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

import javax.sql.DataSource;
import java.io.File;
import java.util.*;

@SpringBootTest
class Myfriday2ApplicationTests {

    @Test
    void contextLoads() {
    }

    @Autowired
    private DataSource dataSource;

    @Test
    void testDataSource() {
        System.out.println(dataSource.getClass());
    }

    @Test
    void testMybatisGenerator() throws Exception {
        List<String> warnings = new ArrayList<String>();
        boolean overwrite = true;
        //以下为MyBatis Generator的XML配置文件的位置
        File configFile = new File("src/main/resources/mbg.xml");
        ConfigurationParser cp = new ConfigurationParser(warnings);
        Configuration config = cp.parseConfiguration(configFile);
        DefaultShellCallback callback = new DefaultShellCallback(overwrite);
        MyBatisGenerator myBatisGenerator = new MyBatisGenerator(config, callback, warnings);
        myBatisGenerator.generate(null);
    }

    @Autowired
    private UserMapper userMapper;

    @Test
    void testUserMapper() {
//        long number = 18600000000L;
//        Random sexRandom = new Random();
//        for (int i = 0; i < 100; i++) {
//            User user = new User();
//            String uuid = UUID.randomUUID().toString().substring(0, 5);
//            user.setUsername(uuid);
//            user.setPassword(uuid);
//            user.setNickname("测试用户");
//            user.setEmail(uuid + "@qq.com");
//            user.setPhone(Long.toString(number));
//            number += 1;
//            String sex = sexRandom.nextBoolean() ? "M" : "F";
//            user.setSex(sex);
//            user.setBirthday(new Date());
//            user.setStatus("启用");
//            user.setCreateTime(new Date());
//            user.setUpdateTime(new Date());
//            userMapper.insertSelective(user);
//        }
//        List<User> users = userMapper.selectWithRoleByExample(null);
//        System.out.println(users);
        User user = userMapper.selectWithRoleByPrimaryKey(1L);
        System.out.println(user.getRole().getRoleId() + ":" + user.getRole().getRoleName());
    }

    @Autowired
    private RoleUserMapper roleUserMapper;

    @Test
    void testRoleUserMapper() {
//        RoleUserKey roleUserKey = new RoleUserKey();
//        roleUserKey.setUserId(1L);
//        roleUserKey.setRoleId(1L);
        for (int i = 2; i <= 101; i++) {
            RoleUserKey roleUserKey = new RoleUserKey();
            roleUserKey.setUserId((long) i);
            roleUserKey.setRoleId(2L);
            roleUserMapper.insert(roleUserKey);
        }

    }

    @Autowired
    private PermissionMapper permissionMapper;

    @Test
    void testPermissionTreeUtil() {
        List<Permission> permissions = permissionMapper.selectByExample(null);
        JSONArray array = new JSONArray();
        PermissionTreeUtil.setPermissionTree(0, permissions, array);
        System.out.println(array);
    }

    @Autowired
    private RolePermissionMapper rolePermissionMapper;

    @Test
    void testRolePermissionInsertBatch() {
        List<Long> permissionIds = new ArrayList<>();
        for (long i = 1; i <= 25; i++) {
            permissionIds.add(i);
        }
        rolePermissionMapper.insertBatch(1L, permissionIds);
    }

    @Test
    void testListPermissionByUserId() {
        List<Permission> permissions = permissionMapper.listByUserId(1);
        System.out.println(permissions);
    }

    @Test
    void testBCrypt() {
        BCryptPasswordEncoder encoder = new BCryptPasswordEncoder();
        System.out.println(encoder.encode("123456"));
    }
}
