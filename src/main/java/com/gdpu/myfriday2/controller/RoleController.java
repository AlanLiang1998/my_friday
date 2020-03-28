package com.gdpu.myfriday2.controller;

import com.gdpu.myfriday2.service.RoleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

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
        return new ResponseEntity<>(roleService.queryAll(), HttpStatus.OK);
    }
}
