import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.concurrent.*;

/**
 * @Author:zxp
 * @Description:Future接口的演示
 * @Date:21:41 2024/4/27
 */
public class FutureTest {
    public static void main(String[] args) {
        ThreadPoolExecutor executor = new ThreadPoolExecutor(5, 10, 8,
                TimeUnit.SECONDS, new LinkedBlockingDeque<>(10),
                Executors.defaultThreadFactory(), new ThreadPoolExecutor.CallerRunsPolicy());
        List<FutureTask<Map<Integer,Integer>>> futureTaskList=new ArrayList<>();
        HashMap<Integer,Integer> totalMap=new HashMap<>();
        for(int i=0;i<3;i++){
            int cur=i;
            FutureTask<Map<Integer, Integer>> mapFutureTask = new FutureTask<>(() -> getLabelBOList(cur));
            futureTaskList.add(mapFutureTask);
            executor.submit(mapFutureTask);
        }
        futureTaskList.forEach(mapFutureTask -> {
            try {
                while (true){
                    if(mapFutureTask.isDone()){
                        Map<Integer, Integer> integerIntegerMap = mapFutureTask.get();
                        totalMap.putAll(integerIntegerMap);
                        break;
                    }
                    else {
                        TimeUnit.MILLISECONDS.sleep(500);
                        System.out.println("询问是否完成");
                    }
                }
//                Map<Integer, Integer> integerIntegerMap = mapFutureTask.get();
//                totalMap.putAll(integerIntegerMap);
            } catch (InterruptedException e) {
                throw new RuntimeException(e);
            } catch (ExecutionException e) {
                throw new RuntimeException(e);
            }
        });
        totalMap.forEach((key,value)->{
            System.out.println("key "+key+" value "+value);
        });
        executor.shutdown();
    }
    public static Map<Integer, Integer> getLabelBOList(Integer id){
        try {
            System.out.println("进入休眠"+(id+1)+"秒钟");
            Thread.sleep((id+1)*1000);
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }
        Map<Integer,Integer> map=new HashMap<>();
        map.put(id,id);
        return map;
    }
}
