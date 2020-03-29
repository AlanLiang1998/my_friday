package com.gdpu.myfriday2.service;

import com.gdpu.myfriday2.dto.KeywordDto;
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
}
