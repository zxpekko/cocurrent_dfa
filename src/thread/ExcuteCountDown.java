package thread;

import java.util.concurrent.CountDownLatch;

/**
 * @Author:zxp
 * @Description:
 * @Date:22:24 2024/7/20
 */
public class ExcuteCountDown {
    public static void main(String[] args) {
        ExcuteCountDown excuteCountDown = new ExcuteCountDown();
        excuteCountDown.excute();
    }
    public void excute(){
        CountDownLatch countDownLatch1 = new CountDownLatch(1);
        CountDownLatch countDownLatch2 = new CountDownLatch(1);
        Thread thread1 = new Thread(() -> {
            countDownLatch1.countDown();
            System.out.println("Thread1");
        });
        Thread thread2 = new Thread(() -> {
            try {
                countDownLatch1.await();
                System.out.println("Thread2");
                countDownLatch2.countDown();
            } catch (InterruptedException e) {
                throw new RuntimeException(e);
            }
        });
        Thread thread3 = new Thread(() -> {
            try {
                countDownLatch2.await();
                System.out.println("Thread3");
            } catch (InterruptedException e) {
                throw new RuntimeException(e);
            }
        });
        thread1.start();
        thread2.start();
        thread3.start();

    }
}
