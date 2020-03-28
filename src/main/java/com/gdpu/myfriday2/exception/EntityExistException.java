package com.gdpu.myfriday2.exception;

import org.springframework.util.StringUtils;

/**
 * @Descriptin 实体已存在异常
 * @Author AlanLiang
 * Date 2020/3/28 17:10
 * Version 1.0
 **/
public class EntityExistException extends RuntimeException {
    public EntityExistException(Class clazz, String field, String val) {
        super(EntityExistException.generateMessage(clazz.getSimpleName(), field, val));
    }

    public EntityExistException(String field, String val) {
        super(field + ":" + val + "已存在");
    }

    public EntityExistException(String message) {
        super(message);
    }

    private static String generateMessage(String entity, String field, String val) {
        return StringUtils.capitalize(entity)
                + " with " + field + " " + val + " existed";
    }
}
