package com.gdpu.myfriday2.dto;

import lombok.Data;
import org.hibernate.validator.constraints.Length;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

/**
 * @Descriptin TODO
 * @Author AlanLiang
 * Date 2020/4/2 13:11
 * Version 1.0
 **/
@Data
public class PassDto {
    @NotNull
    private Long userId;
    @NotBlank(message = "原密码不能为空")
    private String oldPass;

    @Length(min = 6, max = 16, message = "密码为6-16个字符")
    private String newPass;
}
