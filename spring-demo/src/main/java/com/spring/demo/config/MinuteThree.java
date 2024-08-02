package com.spring.demo.config;

import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.TimeUnit;

/**
 * @Author:zxp
 * @Description:
 * @Date:21:33 2024/7/30
 */
public class MinuteThree {
    private int count=0;
    private ScheduledExecutorService scheduledExecutorService = Executors.newSingleThreadScheduledExecutor();
    public String get(){
        System.out.println("count"+count);
        if(count==0){
            autoLoad();
            count++;
            System.out.println("可以调用并执行autoload,count"+count);
            return "可以调用";
        }
        else if(count<3){
            count++;
            System.out.println("可以调用不再执行");
            return "可以调用";
        }
        return "不可以调用";
    }
    private void autoLoad(){
        System.out.println("此处执行");
        Runnable runnable = () -> {
            if(count>1)
                count=0;
        };
        scheduledExecutorService.scheduleAtFixedRate(runnable,0,1, TimeUnit.MINUTES);
    }
}