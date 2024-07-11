import java.util.Map;
import java.util.concurrent.*;

/**
 * @Author:zxp
 * @Description:
 * @Date:21:50 2024/4/25
 */
public class CompletableFutureDemo1 {
    private static final InheritableThreadLocal<Map<String, Object>> THREAD_LOCAL
            = new InheritableThreadLocal<>();
    public static void main(String[] args) throws ExecutionException, InterruptedException {
        ThreadPoolExecutor executor = new ThreadPoolExecutor(5, 10, 8,
                TimeUnit.SECONDS, new LinkedBlockingDeque<>(10),
                Executors.defaultThreadFactory(), new ThreadPoolExecutor.CallerRunsPolicy());
        try {
            CompletableFuture.supplyAsync(() -> {
                System.out.println(Thread.currentThread().getName() + "----come in");
                int result = ThreadLocalRandom.current().nextInt(10);
                try {
                    Thread.sleep(1);
                } catch (InterruptedException e) {
                    throw new RuntimeException(e);
                }

                if(result>3)
                    result/=0;
                return result;
            },executor).whenComplete((v, e) -> {
                if (e == null)
                    System.out.println("计算完成，结果是" + v);
            }).exceptionally(e -> {
                e.printStackTrace();
                System.out.println("异常情况" + e.getMessage());
                return null;
            });
            System.out.println(Thread.currentThread().getName()+"告辞");
        } catch (Exception e) {
            throw new RuntimeException(e);
        } finally {
            executor.shutdown();
        }
    }
}
