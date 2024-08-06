package com.spring.demo.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * @Author:zxp
 * @Description:
 * @Date:1:10 2024/8/2
 */
@Configuration
public class MinuteThreeConfig {
    @Bean
    public MinuteThree minuteThree(){
        return new MinuteThree();
    }
}