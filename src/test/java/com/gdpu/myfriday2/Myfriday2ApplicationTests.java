package com.gdpu.myfriday2;

import com.gdpu.myfriday2.dao.UserMapper;
import com.gdpu.myfriday2.model.User;
import org.junit.jupiter.api.Test;
import org.mybatis.generator.api.MyBatisGenerator;
import org.mybatis.generator.config.Configuration;
import org.mybatis.generator.config.xml.ConfigurationParser;
import org.mybatis.generator.exception.XMLParserException;
import org.mybatis.generator.internal.DefaultShellCallback;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import javax.sql.DataSource;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

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
        User user = new User();
        user.setUsername("admin");
        user.setPassword("123456");
        user.setNickname("系统管理员");
        user.setEmail("admin@qq.com");
        user.setPhone("13415418399");
        user.setSex("M");
        user.setBirthday(new Date(1998, 5, 10));
        user.setStatus("启用");
        user.setCreateTime(new Date());
        user.setUpdateTime(new Date());
        userMapper.insertSelective(user);
    }
}
