package com.gdpu.myfriday2.service;

import com.gdpu.myfriday2.dto.KeywordDto;
import com.gdpu.myfriday2.dto.PageDto;
import com.gdpu.myfriday2.dto.UserDto;
import com.gdpu.myfriday2.model.User;

import java.util.List;

public interface UserService {
    List<User> queryAllByPage(PageDto pageDto);

    long countAll();

    int create(UserDto userDto);

    List<User> queryAllByKeyword(KeywordDto keyword);

    long countAllByKeyword(KeywordDto keywordDto);
}
