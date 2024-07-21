package thread;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.*;

/**
 * @Author:zxp
 * @Description:
 * @Date:22:10 2024/5/4
 */
public class CocurrentTest {
    public static void main(String[] args) {
        CocurrentTest cocurrentTest = new CocurrentTest();
        cocurrentTest.Cocurrent();
    }
    public void Cocurrent(){
//        FutureTask<String> stringFutureTask = new FutureTask<>(() -> {
//            return "hello";
//        });
        ThreadPoolExecutor threadPoolExecutor = new ThreadPoolExecutor(5, 10, 10, TimeUnit.SECONDS,
                new LinkedBlockingDeque<>(3), Executors.defaultThreadFactory(), new ThreadPoolExecutor.CallerRunsPolicy());
        List<FutureTask<String>> futureTaskList=new ArrayList<>();
        for(int i=0;i<3;i++){
            int cur=i;
            FutureTask<String> stringFutureTask = new FutureTask<>(() -> {
                TimeUnit.SECONDS.sleep(2);
                System.out.println(cur+"执行了");
                return "hello"+cur;
            });
            futureTaskList.add(stringFutureTask);
            threadPoolExecutor.submit(stringFutureTask);
        }
        futureTaskList.forEach(futureTask -> {
            try {
                while (!futureTask.isDone()){
                    Thread.sleep(1000);
                    System.out.println("询问是否执行完成");
                }
                String s = futureTask.get();
                System.out.println(s);
            } catch (InterruptedException e) {
                throw new RuntimeException(e);
            } catch (ExecutionException e) {
                throw new RuntimeException(e);
            }finally {
                threadPoolExecutor.shutdown();
            }
        });
    }

}
