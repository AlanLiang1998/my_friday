package com.gdpu.myfriday2.controller;

import com.gdpu.myfriday2.dto.KeywordDto;
import com.gdpu.myfriday2.dto.PageDto;
import com.gdpu.myfriday2.dto.PassDto;
import com.gdpu.myfriday2.dto.UserDto;
import com.gdpu.myfriday2.model.User;
import com.gdpu.myfriday2.service.UserService;
import com.gdpu.myfriday2.utils.ResponseResult;
import lombok.extern.slf4j.Slf4j;
import org.apache.ibatis.annotations.Param;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
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
    @PreAuthorize("hasAuthority('sys:user:query')")
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
    @PreAuthorize("hasAuthority('sys:user:add')")
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
    @PreAuthorize("hasAuthority('sys:user:add')")
    @ResponseBody
    @PostMapping
    public ResponseResult<Object> create(@Validated(User.Create.class) UserDto userDto) {
        userDto.setPassword(new BCryptPasswordEncoder().encode("123456"));
        userDto.setCreateTime(new Date());
        userDto.setUpdateTime(userDto.getCreateTime());
        int result = userService.create(userDto);
        return result == 1 ? ResponseResult.success() : ResponseResult.failure();
    }

    /**
     * 根据关键词（用户ID或用户名）查询用户
     *
     * @param keywordDto 关键词DTO
     * @return 用户列表
     */
    @PreAuthorize("hasAuthority('sys:user:query')")
    @ResponseBody
    @GetMapping
    public ResponseResult<Object> searchUsersByPage(KeywordDto keywordDto) {
        List<User> users = userService.queryAllByKeyword(keywordDto);
        long count = userService.countAllByKeyword(keywordDto);
        return ResponseResult.tableSuccess(users, count);
    }


    /**
     * 切换用户状态
     *
     * @param userId 用户ID
     * @return 切换结果
     */
    @ResponseBody
    @PutMapping("/state")
    @PreAuthorize("hasAuthority('sys:user:edit')")
    public ResponseResult<Object> switchState(@NotNull @Param("userId") Long userId) {
        int result = userService.switchState(userId);
        return result == 1 ? ResponseResult.success() : ResponseResult.failure();
    }

    /**
     * 返回用户编辑页面并填充用户信息
     *
     * @param userId 用户ID
     * @param model  model
     * @return 用户编辑页面
     */
    @PreAuthorize("hasAuthority('sys:user:edit')")
    @GetMapping("/editPage")
    public String editPage(@Param("userId") Long userId, Model model) {
        User user = userService.queryById(userId);
        model.addAttribute(user);
        return "user/user-edit";
    }

    /**
     * 更新用户信息
     *
     * @param userDto 用户信息
     * @return 更新结果
     */
    @PreAuthorize("hasAuthority('sys:user:edit')")
    @ResponseBody
    @PutMapping
    public ResponseResult<Object> update(@Validated(User.Update.class) UserDto userDto) {
        userDto.setUpdateTime(new Date());
        int result = userService.update(userDto);
        return result == 1 ? ResponseResult.success() : ResponseResult.failure();
    }

    /**
     * 删除用户
     *
     * @param userId 用户ID
     * @return 删除结果
     */
    @PreAuthorize("hasAuthority('sys:user:del')")
    @ResponseBody
    @DeleteMapping
    public ResponseResult<Object> delete(@NotNull @RequestParam("userId") Long userId) {
        int result = userService.delete(userId);
        return result == 1 ? ResponseResult.success() : ResponseResult.failure();
    }

    /**
     * 批量删除用户
     *
     * @param idList 用户ID列表
     * @return 删除结果
     */
    @PreAuthorize("hasAuthority('sys:user:del')")
    @ResponseBody
    @DeleteMapping("/list")
    public ResponseResult<Object> deleteBatch(@NotEmpty @RequestBody List<Long> idList) {
        int result = userService.deleteBatch(idList);
        return result == idList.size() ? ResponseResult.success() : ResponseResult.failure();
    }

    @PreAuthorize("hasAuthority('sys:user:edit')")
    @ResponseBody
    @PostMapping("/newPassword")
    public ResponseResult<Object> changePassword(@Validated PassDto passDto) {
        int result = userService.changePassword(passDto);
        return result == 1 ? ResponseResult.success() : ResponseResult.failure();
    }
}
