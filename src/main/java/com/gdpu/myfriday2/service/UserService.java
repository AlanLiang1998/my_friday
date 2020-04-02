package com.gdpu.myfriday2.service;

import com.gdpu.myfriday2.dto.KeywordDto;
import com.gdpu.myfriday2.dto.PageDto;
import com.gdpu.myfriday2.dto.PassDto;
import com.gdpu.myfriday2.dto.UserDto;
import com.gdpu.myfriday2.model.User;

import java.util.List;

public interface UserService {
    List<User> queryAllByPage(PageDto pageDto);

    long countAll();

    int create(UserDto userDto);

    /**
     * 根据关键词（用户ID或用户名）查询用户
     *
     * @param keyword 关键词DTO
     * @return 用户列表
     */
    List<User> queryAllByKeyword(KeywordDto keyword);

    /**
     * 据关键词（用户ID或用户名）查询用户数量
     *
     * @param keywordDto 关键词DTO
     * @return 用户数量
     */
    long countAllByKeyword(KeywordDto keywordDto);

    /**
     * 切换用户状态
     *
     * @param userId 用户ID
     * @return 切换结果
     */
    int switchState(Long userId);

    /**
     * 根据用户ID查询用户
     *
     * @param userId 用户ID
     * @return 用户
     */
    User queryById(Long userId);

    /**
     * 更新用户信息
     *
     * @param userDto 用户信息
     * @return 更新结果
     */
    int update(UserDto userDto);

    /**
     * 删除用户
     *
     * @param userId 用户ID
     * @return 删除结果
     */
    int delete(Long userId);

    /**
     * 批量删除用户
     *
     * @param idList 用户ID列表
     * @return 删除结果
     */
    int deleteBatch(List<Long> idList);

    /**
     * 修改用户密码
     *
     * @param passDto 密码DTO
     * @return 修改结果
     */
    int changePassword(PassDto passDto);
}
