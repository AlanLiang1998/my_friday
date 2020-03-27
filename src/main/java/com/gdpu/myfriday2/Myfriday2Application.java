package com.gdpu.myfriday2;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@MapperScan("com.gdpu.myfriday2.dao")
@SpringBootApplication
public class Myfriday2Application {

    public static void main(String[] args) {
        SpringApplication.run(Myfriday2Application.class, args);
    }

}
