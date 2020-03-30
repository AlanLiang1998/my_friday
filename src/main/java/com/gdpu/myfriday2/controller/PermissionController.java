package com.gdpu.myfriday2.controller;

import com.gdpu.myfriday2.service.PermissionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

/**
 * @Descriptin TODO
 * @Author AlanLiang
 * Date 2020/3/29 22:22
 * Version 1.0
 **/
@Controller
@RequestMapping("/permission")
public class PermissionController {

    @Autowired
    private PermissionService permissionService;

    /**
     * 查询所有权限，以JSONArray的形式返回
     *
     * @return 权限列表
     */
    @GetMapping("/list")
    public ResponseEntity<Object> list() {
        return new ResponseEntity<>(permissionService.list(), HttpStatus.OK);
    }
}
