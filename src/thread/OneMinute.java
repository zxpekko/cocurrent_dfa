package thread;

import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.TimeUnit;

/**
 * @Author:zxp
 * @Description:
 * @Date:12:46 2024/7/16
 */
public class OneMinute {
    private int count=0;
    ScheduledExecutorService scheduledExecutorService = Executors.newSingleThreadScheduledExecutor();
//    public thread.OneMinute(){
//        this.schedule();
//    }
    public String get(){
        synchronized (this){
            if(this.count==0){
                scheduledExecutorService.shutdown();
                this.schedule();
                count++;
                return "可以访问";
            }
            else if(count<3){
                count++;
                return "可以访问";
            }
            else return "不可以访问";
        }
    }
    public void schedule(){
        Runnable task=()->{
            this.count=0;
        };
        scheduledExecutorService.scheduleAtFixedRate(task,0,1, TimeUnit.MINUTES);
    }
}
