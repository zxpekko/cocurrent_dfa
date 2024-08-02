package com.spring.demo.controller;

import com.spring.demo.config.MinuteThree;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;

/**
 * @Author:zxp
 * @Description:
 * @Date:1:11 2024/8/2
 */
@RestController
public class TestController {
    @Resource
    private MinuteThree minuteThree;
    @GetMapping("/test")
    public String get(){
        return minuteThree.get();
    }
}