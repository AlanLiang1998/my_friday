package com.gdpu.myfriday2.model;

import lombok.Data;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

@Data
public class Permission {
    private Long permissionId;
    @NotNull(message = "上级菜单不能为空")
    private Long parentId;
    @NotBlank(message = "菜单名称")
    private String permissionName;

    private String css;

    private String href;
    @NotNull(message = "类型不能为空")
    private Integer type;

    private String permission;
    @NotNull(message = "排序不能为空")
    private Integer sort;

}