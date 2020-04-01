package com.gdpu.myfriday2.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

/**
 * @Descriptin 路由控制器
 * @Author AlanLiang
 * Date 2020/3/27 17:56
 * Version 1.0
 **/
@Controller
@RequestMapping("/api")
public class ApiController {
    @RequestMapping("/getPage")
    public ModelAndView getPage(@RequestParam("pageName") String pageName,
                                ModelAndView mv) {
        mv.setViewName(pageName);
        return mv;
    }
}
