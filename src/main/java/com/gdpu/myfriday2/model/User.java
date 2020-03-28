package com.gdpu.myfriday2.model;

import lombok.Data;

import java.util.Date;

@Data
public class User {
    private Long userId;

    private String username;

    private String password;

    private String nickname;

    private String avatar;

    private String email;

    private String phone;

    private String sex;

    private Date birthday;

    private Byte status;

    private Date createTime;

    private Date updateTime;

    private Role role;

}