package com.gdpu.myfriday2.dto;

import com.gdpu.myfriday2.model.Role;
import lombok.Data;
import lombok.EqualsAndHashCode;

import java.util.List;

/**
 * @Descriptin TODO
 * @Author AlanLiang
 * Date 2020/3/30 11:32
 * Version 1.0
 **/
@Data
@EqualsAndHashCode(callSuper = true)
public class RoleDto extends Role {
    private List<Long> permissionIds;
}
