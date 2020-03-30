package com.gdpu.myfriday2.service;

import com.gdpu.myfriday2.dto.KeywordDto;
import com.gdpu.myfriday2.dto.RoleDto;
import com.gdpu.myfriday2.model.Role;

import java.util.List;

/**
 * @Descriptin 角色服务层
 * @Author AlanLiang
 * Date 2020/3/28 16:05
 * Version 1.0
 **/
public interface RoleService {
    List<Role> queryAll();

    /**
     * 查询角色数量
     *
     * @return
     */
    long countAll();

    /**
     * 根据关键词（角色ID或角色名）查询角色
     *
     * @param keyword 关键词DTO
     * @return 角色列表
     */
    List<Role> queryAllByKeyword(KeywordDto keyword);

    /**
     * 据关键词（角色ID或角色名）查询角色数量
     *
     * @param keywordDto 关键词DTO
     * @return 角色数量
     */
    long countAllByKeyword(KeywordDto keywordDto);

    /**
     * 创建角色
     *
     * @param roleDto 角色DTO
     * @return 创建结果
     */
    int create(RoleDto roleDto);

    /**
     * 根据角色ID查询角色
     *
     * @param roleId 角色ID
     * @return 角色
     */
    Role queryById(Long roleId);

    /**
     * 更新角色
     *
     * @param roleDto 角色DTO
     * @return 更新结果
     */
    int update(RoleDto roleDto);

    /**
     * 删除角色
     *
     * @param roleId 角色ID
     * @return 删除结果
     */
    int delete(Long roleId);

    /**
     * 批量删除角色
     *
     * @param roleIdList 角色ID列表
     * @return 删除结果
     */
    int deleteBatch(List<Long> roleIdList);
}
