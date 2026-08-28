package com.atguigu.springboot.controller;


import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Date;

@RestController
public class HelloController {
    @RequestMapping("hello")
    public String hello() {
        return "hello,web project" + new Date();
    }
}
