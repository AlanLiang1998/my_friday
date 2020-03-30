package com.gdpu.myfriday2.controller;

import com.gdpu.myfriday2.dto.KeywordDto;
import com.gdpu.myfriday2.dto.RoleDto;
import com.gdpu.myfriday2.model.Role;
import com.gdpu.myfriday2.service.RoleService;
import com.gdpu.myfriday2.utils.ResponseResult;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
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
     * 跳转至添加角色页面
     *
     * @return 添加角色页面
     */
    @GetMapping("/addPage")
    public String addPage() {
        return "role/role-add";
    }

    /**
     * 新增角色
     *
     * @param roleDto 角色DTO
     * @return 新增结果
     */
    @ResponseBody
    @PostMapping()
    public ResponseResult<Object> create(@Validated @RequestBody RoleDto roleDto) {
        roleDto.setCreateTime(new Date());
        roleDto.setUpdateTime(new Date());
        int result = roleService.create(roleDto);
        return result == 1 ? ResponseResult.success() : ResponseResult.failure();
    }

    /**
     * 跳转至编辑角色页面
     *
     * @return 编辑角色页面
     */
    @GetMapping("/editPage")
    public String editPage(@NotNull @RequestParam("roleId") Long roleId, Model model) {
        model.addAttribute("role", roleService.queryById(roleId));
        return "role/role-edit";
    }

    /**
     * 更新角色
     *
     * @param roleDto 角色DTO
     * @return 更新结果
     */
    @ResponseBody
    @PutMapping
    public ResponseResult<Object> update(@Validated @RequestBody RoleDto roleDto) {
        roleDto.setUpdateTime(new Date());
        int result = roleService.update(roleDto);
        return result == 1 ? ResponseResult.success() : ResponseResult.failure();
    }

    /**
     * 删除角色
     *
     * @param roleId 角色ID
     * @return 删除结果
     */
    @ResponseBody
    @DeleteMapping
    public ResponseResult<Object> delete(@NotNull @RequestParam("roleId") Long roleId) {
        int result = roleService.delete(roleId);
        return result == 1 ? ResponseResult.success() : ResponseResult.failure();
    }

    /**
     * 批量删除角色
     *
     * @param roleIdList 角色ID列表
     * @return 删除结果
     */
    @ResponseBody
    @DeleteMapping("/list")
    public ResponseResult<Object> deleteBatch(@NotEmpty @RequestBody List<Long> roleIdList) {
        int result = roleService.deleteBatch(roleIdList);
        return result == 1 ? ResponseResult.success() : ResponseResult.failure();
    }
}
