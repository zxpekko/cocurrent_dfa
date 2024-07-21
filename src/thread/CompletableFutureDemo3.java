package thread;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.concurrent.*;

/**
 * @Author:zxp
 * @Description:
 * @Date:15:05 2024/5/7
 */
public class CompletableFutureDemo3 {
    public static void main(String[] args) {
        CompletableFutureDemo3 completableFutureDemo3 = new CompletableFutureDemo3();
        completableFutureDemo3.cocurrent();
    }

    public void cocurrent(){
        HashMap<Integer, Integer> result= new HashMap<>();
        ThreadPoolExecutor threadPoolExecutor = new ThreadPoolExecutor(5, 10, 10, TimeUnit.SECONDS,
                new LinkedBlockingDeque<>(3), Executors.defaultThreadFactory(), new ThreadPoolExecutor.CallerRunsPolicy());
        List<CompletableFuture<Map<Integer,Integer>>> list=new ArrayList<>();
        for(int i=0;i<3;i++){
            int cur=i;
            CompletableFuture.supplyAsync(()->getValue(cur),threadPoolExecutor).whenComplete((v,e)->{
                if(e==null)
                    result.putAll(v);
                if(e==null&&result.size()==3){
                    result.forEach((key,value)->{
                        System.out.println("key"+key+" value"+value);
                    });
                }
            }).exceptionally(e->{
                e.printStackTrace();
                System.out.println(e.getMessage());
                return null;
            });
        }
        threadPoolExecutor.shutdown();
        for(int i=0;i<3;i++){
            System.out.println("hello");
        }
        new Thread(()->{
            System.out.println("hello");
        }).start();

//        System.out.println(result.size());
//        while (result.size()<3){
//
//        }


    }
    public Map<Integer,Integer> getValue(int index){
        try {
            TimeUnit.SECONDS.sleep(1);
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }
        HashMap<Integer, Integer> hashMap = new HashMap<>();
        hashMap.put(index,index+1);
        return hashMap;
    }
}
