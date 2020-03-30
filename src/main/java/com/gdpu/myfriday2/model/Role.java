package com.gdpu.myfriday2.model;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.Data;

import javax.validation.constraints.NotBlank;
import java.util.Date;


/**
 * @author AlanLiang
 */
@Data
public class Role {
    private Long roleId;
    @NotBlank(message = "角色名不能为空")
    private String roleName;

    private String description;
    @JsonFormat(pattern = "yyyy-MM-dd hh:mm:ss")
    private Date createTime;
    @JsonFormat(pattern = "yyyy-MM-dd hh:mm:ss")
    private Date updateTime;

}