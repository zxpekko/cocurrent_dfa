package thread;

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
        if(count==0){
            autoLoad();
            count++;
            return "可以调用";
        }
        else if(count<3){
            count++;
            return "可以调用";
        }
        return "不可以调用";
    }
    private void autoLoad(){
        Runnable runnable = () -> {
            count = 0;
        };
        scheduledExecutorService.scheduleAtFixedRate(runnable,0,1, TimeUnit.MINUTES);
    }
}