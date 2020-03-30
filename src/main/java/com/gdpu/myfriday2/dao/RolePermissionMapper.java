package com.gdpu.myfriday2.dao;

import com.gdpu.myfriday2.model.RolePermissionExample;
import com.gdpu.myfriday2.model.RolePermissionKey;

import java.util.List;

import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

@Repository
public interface RolePermissionMapper {
    long countByExample(RolePermissionExample example);

    int deleteByExample(RolePermissionExample example);

    int deleteByPrimaryKey(RolePermissionKey key);

    int insert(RolePermissionKey record);

    int insertSelective(RolePermissionKey record);

    List<RolePermissionKey> selectByExample(RolePermissionExample example);

    int updateByExampleSelective(@Param("record") RolePermissionKey record, @Param("example") RolePermissionExample example);

    int updateByExample(@Param("record") RolePermissionKey record, @Param("example") RolePermissionExample example);

    /**
     * 批量插入
     *
     * @param roleId        角色ID
     * @param permissionIds 权限ID集合
     * @return 插入记录数
     */
    int insertBatch(@Param("roleId") Long roleId, @Param("permissionIds") List<Long> permissionIds);
}