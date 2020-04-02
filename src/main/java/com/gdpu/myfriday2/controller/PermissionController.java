package com.gdpu.myfriday2.controller;

import com.alibaba.fastjson.JSONArray;
import com.gdpu.myfriday2.model.Permission;
import com.gdpu.myfriday2.service.PermissionService;
import com.gdpu.myfriday2.utils.ResponseResult;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import javax.validation.constraints.NotNull;
import java.util.HashMap;
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
    @PreAuthorize("hasAuthority('sys:menu:query')")
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
    @PreAuthorize("hasAuthority('sys:menu:query')")
    @GetMapping("/list/roleId/{roleId}")
    public ResponseEntity<Object> listByRoleId(@NotNull @PathVariable("roleId") Long roleId) {
        return new ResponseEntity<>(permissionService.listByRoleId(roleId), HttpStatus.OK);
    }

    /**
     * 查询所有权限，以普通数组方式返回
     *
     * @return 权限列表
     */
    @PreAuthorize("hasAuthority('sys:menu:query')")
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
    @PreAuthorize("hasAuthority('sys:menu:add')")
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
    @PreAuthorize("hasAuthority('sys:menu:add')")
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
    @PreAuthorize("hasAuthority('sys:menu:edit')")
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
    @PreAuthorize("hasAuthority('sys:menu:edit')")
    @ResponseBody
    @PutMapping
    public ResponseResult<Object> update(@Validated @RequestBody Permission permission) {
        int result = permissionService.update(permission);
        return result == 1 ? ResponseResult.success() : ResponseResult.failure();
    }

    /**
     * 删除指定权限
     *
     * @param id 权限ID
     * @return 删除结果
     */
    @PreAuthorize("hasAuthority('sys:menu:del')")
    @ResponseBody
    @DeleteMapping
    public ResponseResult<Object> delete(@NotNull @RequestParam("id") Long id) {
        int result = permissionService.delete(id);
        return result == 1 ? ResponseResult.success() : ResponseResult.failure();
    }

    @PreAuthorize("hasAuthority('sys:menu:query')")
    @GetMapping("/list/userId/{userId}")
    @ResponseBody
    public ResponseResult<Object> listByUserId(@PathVariable("userId") Long userId) {
        JSONArray array = permissionService.listByUserId(userId);
        HashMap<String, Object> data = new HashMap<>();
        data.put("permissions", array);
        return ResponseResult.tableSuccess(data, 0L);
    }
}
