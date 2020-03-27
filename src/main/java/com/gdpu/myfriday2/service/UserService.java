package com.gdpu.myfriday2.service;

import com.gdpu.myfriday2.dto.PageDto;
import com.gdpu.myfriday2.model.User;

import java.util.List;

public interface UserService {
    List<User> queryAllByPage(PageDto pageDto);

    long countAll();
}
