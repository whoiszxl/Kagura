package com.whoiszxl.spring.cloud.weather.controller;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * \* Created with IntelliJ IDEA.
 * \* User: whoiszxl
 * \* Date: 2018/1/30
 * \* Description:
 * \
 */
@RestController
public class HelloController {

    @RequestMapping("/goodbye")
    public String hello(){
        return "Goodbye World";
    }

}