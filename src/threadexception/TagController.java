package threadexception;

/**
 * @Author:zxp
 * @Description:
 * @Date:14:58 2024/7/17
 */
//import org.springframework.web.bind.annotation.GetMapping;
//import org.springframework.web.bind.annotation.PathVariable;
//import org.springframework.web.bind.annotation.RestController;

import java.util.Arrays;
import java.util.List;
import java.util.concurrent.*;

//@RestController
public class TagController {

    private static final int RETRY_COUNT = 3;
    private ExecutorService executorService = Executors.newFixedThreadPool(5);
    private ConcurrentHashMap<Integer, List<String>> categoryTagsMap = new ConcurrentHashMap<>();

//    @GetMapping("/tags/{categoryId}")
    public CompletableFuture<String> getTags( int categoryId) {

        return CompletableFuture.supplyAsync(() -> {
            // 返回一个立即响应，告知前端任务已接收
            processQueryTagsAsync(categoryId);
            return "Request received, processing in background";
        });
    }

    private void processQueryTagsAsync(int categoryId) {
        executorService.submit(() -> {
            boolean success = queryTagsWithRetry(categoryId, RETRY_COUNT);
            if (success) {
                // 通知前端（可以使用WebSocket、事件推送等）
                notifyFrontend(categoryId, "Tags successfully retrieved");
            } else {
                notifyFrontend(categoryId, "Failed to retrieve tags after retries");
            }
        });
    }

    private boolean queryTagsWithRetry(int categoryId, int retryCount) {
        while (retryCount > 0) {
            try {
                // 模拟查询操作
                if (categoryId % 2 == 0) {
                    throw new RuntimeException("Query failed");
                }
                System.out.println("Query success for category: " + categoryId);
                // 假设查询成功后将数据存储到缓存中
                categoryTagsMap.put(categoryId, Arrays.asList("tag1", "tag2", "tag3"));
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

    private void notifyFrontend(int categoryId, String message) {
        // 通过WebSocket或其他方式通知前端
        System.out.println("Notify frontend for category: " + categoryId + " - " + message);
    }
}
