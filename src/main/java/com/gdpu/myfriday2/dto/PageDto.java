package com.gdpu.myfriday2.dto;

import lombok.Data;

/**
 * @Descriptin 分页参数DTO
 * @Author AlanLiang
 * Date 2020/3/27 18:28
 * Version 1.0
 **/
@Data
public class PageDto {
    /**
     * 当前页码
     */
    private Integer page;
    /**
     * 页面大小
     */
    private Integer limit;
}
