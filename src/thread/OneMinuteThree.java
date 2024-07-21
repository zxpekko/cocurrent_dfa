package thread;

import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.TimeUnit;

/**
 * @Author:zxp
 * @Description:
 * @Date:20:17 2024/7/15
 */
public class OneMinuteThree {
    private Integer count=0;
    public OneMinuteThree(boolean auto){
        if(auto)
            this.schedule();
    }
    public synchronized String get(){
        if(this.count<3){
            this.count++;
            return "可以访问";
        }
        else return "一分钟内只允许访问三次，稍后再试。。。";
    }
    public void schedule(){
        ScheduledExecutorService scheduledExecutorService = Executors.newSingleThreadScheduledExecutor();
        Runnable task=()->{
            this.count=0;
        };
        scheduledExecutorService.scheduleAtFixedRate(task,0,1, TimeUnit.MINUTES);
    }
}
