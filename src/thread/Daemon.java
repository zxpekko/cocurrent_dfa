package thread;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.*;

/**
 * @Author:zxp
 * @Description:
 * @Date:14:35 2024/4/22
 */
public class Daemon {
    public static void main(String[] args) {
        ThreadPoolExecutor threadPoolExecutor = new ThreadPoolExecutor(5, 10, 5,
                TimeUnit.SECONDS, new LinkedBlockingDeque<>(20),
                Executors.defaultThreadFactory(), new ThreadPoolExecutor.AbortPolicy());
        Daemon daemon = new Daemon();
        daemon.test();

    }
    public void test(){
        ThreadPoolExecutor threadPoolExecutor = new ThreadPoolExecutor(5, 10, 5,
                TimeUnit.SECONDS, new LinkedBlockingDeque<>(20),
                Executors.defaultThreadFactory(), new ThreadPoolExecutor.AbortPolicy());
        List<FutureTask<Integer>> futureTaskList=new ArrayList<>();
        List<Integer> result=new ArrayList<>();
        for(int i=0;i<3;i++){
            int index=i+1;
            FutureTask<Integer> futureTask = new FutureTask<>(() -> task(index));
            futureTaskList.add(futureTask);
            threadPoolExecutor.submit(futureTask);
        }
        futureTaskList.forEach(subFutureTask->{
            try {
                Integer integer = subFutureTask.get();
                result.add(integer);
            } catch (InterruptedException e) {
                throw new RuntimeException(e);
            } catch (ExecutionException e) {
                throw new RuntimeException(e);
            }

        });
        System.out.println(result);
        threadPoolExecutor.shutdown();
    }
    public Integer task(int index){
        System.out.println(index+"执行了");
        return index;
    }
}
