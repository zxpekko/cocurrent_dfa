package thread;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.concurrent.*;

/**
 * @Author:zxp
 * @Description:
 * @Date:18:59 2024/4/24
 */
public class Cocurrent {
    public static void main(String[] args) {
        Cocurrent cocurrent = new Cocurrent();
        cocurrent.cocurrentTest();
//        Thread t1 = new Thread(() -> cocurrent.getResult(1), "t1");
    }
    public void cocurrentTest(){
        ThreadPoolExecutor executor = new ThreadPoolExecutor(5, 10, 8,
                TimeUnit.SECONDS, new LinkedBlockingDeque<>(10),
                Executors.defaultThreadFactory(), new ThreadPoolExecutor.CallerRunsPolicy());
        List<FutureTask<Map<Integer,Integer>>> futureTaskList=new ArrayList<>();
        Map<Integer,Integer> map=new HashMap<>();
        for(int i=0;i<3;i++){
            int index=i;
            FutureTask<Map<Integer, Integer>> mapFutureTask = new FutureTask<>(() -> getResult(index));
            futureTaskList.add(mapFutureTask);
            executor.submit(mapFutureTask);
//            FutureTask<String> stringFutureTask = new FutureTask<>(() -> {
//                return "hello";
//            });
        }

        futureTaskList.forEach(mapFutureTask -> {
            try {
                Map<Integer, Integer> integerIntegerMap = mapFutureTask.get();
                map.putAll(integerIntegerMap);
            } catch (InterruptedException e) {
                throw new RuntimeException(e);
            } catch (ExecutionException e) {
                throw new RuntimeException(e);
            }
        });
        map.forEach((key,value)->{
            System.out.println("key "+key+","+"value "+value);
        });
        executor.shutdown();
    }
    public Map<Integer, Integer> getResult(int index){
        System.out.println(Thread.currentThread()+""+index);
        System.out.println(index+"开始执行");
        HashMap<Integer,Integer> result=new HashMap<>();
        result.put(index,index+1);
        System.out.println(index+"执行完毕");
        return result;
    }
}