package com.gdpu.myfriday2.controller;

import com.gdpu.myfriday2.model.Permission;
import com.gdpu.myfriday2.service.PermissionService;
import com.gdpu.myfriday2.utils.ResponseResult;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import javax.validation.constraints.NotNull;
import java.util.List;

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

    /**
     * 查询指定角色的权限ID列表
     *
     * @param roleId 角色ID
     * @return 权限ID列表
     */
    @GetMapping("/list/roleId/{roleId}")
    public ResponseEntity<Object> listByRoleId(@NotNull @PathVariable("roleId") Long roleId) {
        return new ResponseEntity<>(permissionService.listByRoleId(roleId), HttpStatus.OK);
    }

    /**
     * 查询所有权限，以普通数组方式返回
     *
     * @return 权限列表
     */
    @ResponseBody
    @GetMapping("/menu")
    public ResponseResult<Object> menu() {
        List<Permission> permissions = permissionService.queryAll();
        return ResponseResult.tableSuccess(permissions, 0L);
    }

    /**
     * 跳转至添加权限页面
     *
     * @return 添加权限页面
     */
    @GetMapping("/addPage")
    public String addPage() {
        return "/permission/permission-add";
    }

    /**
     * 创建权限
     *
     * @param permission 新权限
     * @return 结果
     */
    @ResponseBody
    @PostMapping
    public ResponseResult<Object> create(@Validated @RequestBody Permission permission) {
        int result = permissionService.create(permission);
        return result == 1 ? ResponseResult.success() : ResponseResult.failure();
    }

    /**
     * 跳转至编辑权限页面
     *
     * @return 编辑限页面
     */
    @GetMapping("/editPage")
    public String editPage(@RequestParam("id") Long id, Model model) {
        model.addAttribute("permission", permissionService.queryById(id));
        return "/permission/permission-edit";
    }

    /**
     * 更新权限
     *
     * @param permission 新权限
     * @return 更新结果
     */
    @ResponseBody
    @PutMapping
    public ResponseResult<Object> update(@Validated @RequestBody Permission permission) {
        int result = permissionService.update(permission);
        return result == 1 ? ResponseResult.success() : ResponseResult.failure();
    }
}
