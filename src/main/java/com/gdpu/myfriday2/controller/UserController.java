package com.gdpu.myfriday2.controller;

import com.gdpu.myfriday2.dto.KeywordDto;
import com.gdpu.myfriday2.dto.PageDto;
import com.gdpu.myfriday2.dto.UserDto;
import com.gdpu.myfriday2.model.User;
import com.gdpu.myfriday2.service.UserService;
import com.gdpu.myfriday2.utils.ResponseResult;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import javax.swing.*;
import java.util.Date;
import java.util.List;

/**
 * @Descriptin TODO
 * @Author AlanLiang
 * Date 2020/3/27 18:18
 * Version 1.0
 **/
@Slf4j
@Controller
@RequestMapping("/user")
public class UserController {

    @Autowired
    private UserService userService;

    /**
     * 分页查询所有用户
     *
     * @param pageDto 分页数据DTO
     * @return 用户列表
     */
    @ResponseBody
    @GetMapping("/list")
    public ResponseResult<Object> getUsers(PageDto pageDto) {
        List<User> users = userService.queryAllByPage(pageDto);
        Long count = userService.countAll();
        return ResponseResult.tableSuccess(users, count);
    }

    /**
     * 跳转至添加用户页面
     *
     * @return 添加用户页面
     */
    @GetMapping("/addPage")
    public String addPage() {
        return "user/user-add";
    }

    /**
     * 新增用户
     *
     * @param userDto 用户DTO
     * @return 新增结果
     */
    @ResponseBody
    @PostMapping
    public ResponseResult<Object> create(@Validated UserDto userDto) {
        userDto.setPassword("123456");
        userDto.setCreateTime(new Date());
        userDto.setUpdateTime(userDto.getCreateTime());
        if (userService.create(userDto) == 1) {
            return ResponseResult.success();
        } else {
            return ResponseResult.failure();
        }
    }

    /**
     * 根据关键词（用户ID或用户名）查询用户
     *
     * @param keywordDto 关键词DTO
     * @return 用户列表
     */
    @ResponseBody
    @GetMapping
    public ResponseResult<Object> searchUsersByPage(KeywordDto keywordDto) {
        List<User> users = userService.queryAllByKeyword(keywordDto);
        long count = userService.countAllByKeyword(keywordDto);
        return ResponseResult.tableSuccess(users, count);
    }
}
