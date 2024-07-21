package miscellany;

import javax.annotation.Resource;
import java.util.Collections;
import java.util.HashMap;
import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.TimeUnit;

/**
 * @Author:zxp
 * @Description:
 * @Date:18:59 2024/7/15
 */
public class Controller {

    private Integer count=0;
    public Controller(boolean auto){
        if(auto)
            this.schedule();
    }
    public Controller(){}
//    @RestController("/get")
    public synchronized String get(){
        if(count<3){
            count++;
            return "可以访问";

        }
        else return "一分钟内只能访问3次";
    }
    public void schedule(){
        ScheduledExecutorService scheduledExecutorService = Executors.newSingleThreadScheduledExecutor();
        Runnable task=()->{
            this.count=0;
        };
        scheduledExecutorService.scheduleAtFixedRate(task,0,1, TimeUnit.MINUTES);
    }
}
