package com.gdpu.myfriday2.controller;

import com.gdpu.myfriday2.dto.KeywordDto;
import com.gdpu.myfriday2.dto.UserDto;
import com.gdpu.myfriday2.model.Role;
import com.gdpu.myfriday2.model.User;
import com.gdpu.myfriday2.service.RoleService;
import com.gdpu.myfriday2.utils.ResponseResult;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * @Descriptin TODO
 * @Author AlanLiang
 * Date 2020/3/28 16:04
 * Version 1.0
 **/
@Controller
@RequestMapping("/role")
public class RoleController {

    @Autowired
    private RoleService roleService;

    /**
     * 查询所有角色
     *
     * @return 角色列表
     */
    @GetMapping("/list")
    public ResponseEntity<Object> list() {
        Map<String, Object> map = new HashMap<>();
        map.put("code", 200);
        map.put("msg", "处理成功");
        map.put("count", roleService.countAll());
        map.put("data", roleService.queryAll());
        return new ResponseEntity<>(map, HttpStatus.OK);
    }

    /**
     * 根据关键词（角色ID或角色名）查询角色
     *
     * @param keywordDto 关键词DTO
     * @return 角色列表
     */
    @ResponseBody
    @GetMapping
    public ResponseResult<Object> searchRolesByPage(KeywordDto keywordDto) {
        List<Role> roles = roleService.queryAllByKeyword(keywordDto);
        long count = roleService.countAllByKeyword(keywordDto);
        return ResponseResult.tableSuccess(roles, count);
    }

    /**
     * 跳转至添加用户页面
     *
     * @return 添加用户页面
     */
    @GetMapping("/addPage")
    public String addPage() {
        return "role/role-add";
    }

    /**
     * 新增用户
     *
     * @param userDto 用户DTO
     * @return 新增结果
     */
   /* @ResponseBody
    @PostMapping
    public ResponseResult<Object> create(@Validated UserDto userDto) {
        userDto.setPassword("123456");
        userDto.setCreateTime(new Date());
        userDto.setUpdateTime(userDto.getCreateTime());
        int result = userService.create(userDto);
        return result == 1 ? ResponseResult.success() : ResponseResult.failure();
    }*/
}
