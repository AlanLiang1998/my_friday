package com.gdpu.myfriday2.dao;

import com.gdpu.myfriday2.model.Permission;
import com.gdpu.myfriday2.model.RoleUserExample;
import com.gdpu.myfriday2.model.RoleUserKey;

import java.util.List;

import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

@Repository
public interface RoleUserMapper {
    long countByExample(RoleUserExample example);

    int deleteByExample(RoleUserExample example);

    int deleteByPrimaryKey(RoleUserKey key);

    int insert(RoleUserKey record);

    int insertSelective(RoleUserKey record);

    List<RoleUserKey> selectByExample(RoleUserExample example);

    int updateByExampleSelective(@Param("record") RoleUserKey record, @Param("example") RoleUserExample example);

    int updateByExample(@Param("record") RoleUserKey record, @Param("example") RoleUserExample example);

}