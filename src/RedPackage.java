import java.util.ArrayList;
import java.util.List;
import java.util.Random;
import java.util.concurrent.*;

/**
 * @Author:zxp
 * @Description:
 * @Date:16:00 2024/5/5
 */
public class RedPackage {
    volatile int limit=100;
    int nums=5;
    int curNum=1;
    public static void main(String[] args) {
        RedPackage redPackage = new RedPackage();
        redPackage.getRedPackage();
    }
    public void getRedPackage(){
        ThreadPoolExecutor threadPoolExecutor = new ThreadPoolExecutor(5, 10, 10, TimeUnit.SECONDS,
                new LinkedBlockingDeque<>(3), Executors.defaultThreadFactory(), new ThreadPoolExecutor.CallerRunsPolicy());
        List<FutureTask<Integer>> list=new ArrayList<>();
        for(int i=0;i<nums;i++){
                FutureTask<Integer> integerFutureTask = new FutureTask<>(() -> {
                    synchronized (RedPackage.class) {
                        Random random = new Random();
                        if(curNum!=5){
                            int i1 = random.nextInt(limit / 2);
                            limit-=i1;
                            curNum++;
                            return i1;
                        }
                        else
                            return limit;
                    }
                });
                list.add(integerFutureTask);
                threadPoolExecutor.submit(integerFutureTask);
        }
        list.forEach(futuretask->{
            try {
                Integer integer = futuretask.get();
                System.out.println("这次抢到"+integer);
            } catch (InterruptedException e) {
                throw new RuntimeException(e);
            } catch (ExecutionException e) {
                throw new RuntimeException(e);
            }

        });
        threadPoolExecutor.shutdown();
    }
}
