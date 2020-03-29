package com.gdpu.myfriday2.model;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.Data;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import java.util.Date;

/**
 * @author AlanLiang
 */
@Data
public class User {
    private Long userId;
    @NotBlank(message = "用户名不能为空", groups = {Create.class, Update.class})
    private String username;

    private String password;

    private String nickname;

    private String avatar;
    @NotBlank(message = "邮箱不能为空", groups = {Create.class, Update.class})
    @Email(message = "邮箱格式不合法", groups = {Create.class, Update.class})
    private String email;
    @NotBlank(message = "手机号码不能为空", groups = {Create.class, Update.class})
    private String phone;

    private String sex;

    private Date birthday;
    @NotNull(message = "请为用户选择状态", groups = {Create.class})
    private Byte status;
    @JsonFormat(pattern = "yyyy-MM-dd hh:mm:ss")
    private Date createTime;
    @JsonFormat(pattern = "yyyy-MM-dd hh:mm:ss")
    private Date updateTime;

    private Role role;


    public interface Create {
    }

    public interface Update {
    }
}