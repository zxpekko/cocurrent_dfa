package threadexception;

/**
 * @Author:zxp
 * @Description:
 * @Date:14:23 2024/7/17
 */
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.*;

public class AsyncTaskExample {
    private static final int RETRY_COUNT = 3;

    public static void main(String[] args) {
        ExecutorService executorService = Executors.newFixedThreadPool(5);
        List<Callable<Boolean>> tasks = new ArrayList<>();

        for (int i = 0; i < 5; i++) {
            int categoryId = i;
            tasks.add(() -> {
                return queryTagsWithRetry(categoryId, RETRY_COUNT);
            });
        }

        try {
            List<Future<Boolean>> futures = executorService.invokeAll(tasks);
            for (Future<Boolean> future : futures) {
                try {
                    if (!future.get()) {
                        System.out.println("Task failed after retries");
                    }
                } catch (ExecutionException e) {
                    e.printStackTrace();
                }
            }
        } catch (InterruptedException e) {
            e.printStackTrace();
        } finally {
            executorService.shutdown();
        }
    }

    private static Boolean queryTagsWithRetry(int categoryId, int retryCount) {
        while (retryCount > 0) {
            try {
                // 模拟查询操作
                if (categoryId % 2 == 0) {
                    throw new RuntimeException("Query failed");
                }
                System.out.println("Query success for category: " + categoryId);
                return true;
            } catch (Exception e) {
                System.out.println("Retrying for category: " + categoryId);
                retryCount--;
                if (retryCount == 0) {
                    return false;
                }
            }
        }
        return false;
    }
}
