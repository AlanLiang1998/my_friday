package com.gdpu.myfriday2.utils;

import java.io.PrintWriter;
import java.io.StringWriter;

/**
 * @Descriptin 异常工具
 * @Author AlanLiang
 * Date 2020/3/28 17:15
 * Version 1.0
 **/
public class ThrowableUtil {
    /**
     * 获取堆栈信息
     */
    public static String getStackTrace(Throwable throwable){
        StringWriter sw = new StringWriter();
        try (PrintWriter pw = new PrintWriter(sw)) {
            throwable.printStackTrace(pw);
            return sw.toString();
        }
    }

}
