package com.gdpu.myfriday2.model;

import lombok.Data;

@Data
public class Permission {
    private Long permissionId;

    private Long parentId;

    private String permissionName;

    private String css;

    private String href;

    private Integer type;

    private String permission;

    private Integer sort;

}