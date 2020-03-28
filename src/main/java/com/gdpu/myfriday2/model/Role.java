package com.gdpu.myfriday2.model;

import lombok.Data;

import java.util.Date;

@Data
public class Role {
    private Long roleId;

    private String roleName;

    private String description;

    private Date createTime;

    private Date updateTime;

}