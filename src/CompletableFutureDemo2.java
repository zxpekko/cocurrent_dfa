import java.util.concurrent.*;

/**
 * @Author:zxp
 * @Description:
 * @Date:22:23 2024/5/4
 */
public class CompletableFutureDemo2 {
    public static void main(String[] args) {
        CompletableFutureDemo2 completableFutureDemo2 = new CompletableFutureDemo2();
        completableFutureDemo2.Cocurrent();
    }
    public void Cocurrent(){
        ThreadPoolExecutor threadPoolExecutor = new ThreadPoolExecutor(5, 10, 10, TimeUnit.SECONDS,
                new LinkedBlockingDeque<>(3), Executors.defaultThreadFactory(), new ThreadPoolExecutor.CallerRunsPolicy());
        CompletableFuture<String> stringCompletableFuture = CompletableFuture.supplyAsync(() -> {
            return "hello";
        }, threadPoolExecutor).whenComplete((v,e)->{
            if(e==null)
                System.out.println(v);
        }).exceptionally(e->{
            e.printStackTrace();
            return e.getMessage();
        });
        threadPoolExecutor.shutdown();
    }
}
