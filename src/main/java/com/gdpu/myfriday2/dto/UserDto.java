package com.gdpu.myfriday2.dto;

import com.gdpu.myfriday2.model.User;
import lombok.Data;
import lombok.EqualsAndHashCode;

import javax.validation.constraints.NotNull;

/**
 * @Descriptin TODO
 * @Author AlanLiang
 * Date 2020/3/28 16:28
 * Version 1.0
 **/
@Data
@EqualsAndHashCode(callSuper = true)
public class UserDto extends User {
    @NotNull(groups = {Create.class}, message = "请为用户选择角色")
    private Long roleId;
}
