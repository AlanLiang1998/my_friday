package com.gdpu.myfriday2.exception;

/**
 * @Descriptin TODO
 * @Author AlanLiang
 * Date 2020/3/30 16:58
 * Version 1.0
 **/
public class AssociationExistException extends RuntimeException {
    public AssociationExistException(String message) {
        super(message);
    }
}
