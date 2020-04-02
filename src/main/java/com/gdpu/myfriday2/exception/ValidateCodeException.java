package com.gdpu.myfriday2.exception;

import org.springframework.security.core.AuthenticationException;

/**
 * @Descriptin TODO
 * @Author AlanLiang
 * Date 2020/4/2 17:22
 * Version 1.0
 **/
public class ValidateCodeException extends AuthenticationException {
    public ValidateCodeException(String message) {
        super(message);
    }
}
