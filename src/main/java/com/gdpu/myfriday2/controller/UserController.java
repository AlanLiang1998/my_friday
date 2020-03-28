package com.gdpu.myfriday2.controller;

import com.gdpu.myfriday2.dto.PageDto;
import com.gdpu.myfriday2.model.User;
import com.gdpu.myfriday2.service.UserService;
import com.gdpu.myfriday2.utils.ResponseResult;
import com.github.pagehelper.PageInfo;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

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
    public ResponseResult getUsers(PageDto pageDto) {
        List<User> users = userService.queryAllByPage(pageDto);
        Long count = userService.countAll();
        return new ResponseResult(users, count).success();
    }
}
