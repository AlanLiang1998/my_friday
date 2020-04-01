package com.gdpu.myfriday2.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

/**
 * @Descriptin TODO
 * @Author AlanLiang
 * Date 2020/4/1 11:08
 * Version 1.0
 **/
@Controller
public class SecurityController {
    @GetMapping("/login.html")
    public String login() {
        return "login";
    }

    @GetMapping(value = "/403.html")
    public String noPermission() {
        return "error/403";
    }
}
