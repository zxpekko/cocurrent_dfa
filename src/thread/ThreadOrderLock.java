package thread;

import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

/**
 * @Author:zxp
 * @Description:
 * @Date:20:13 2024/7/19
 */
public class ThreadOrderLock {
    private static final Lock lock = new ReentrantLock();
    private static final Condition condition = lock.newCondition();
    private static int current = 1;

    public static void main(String[] args) {
        Thread t1 = new Thread(() -> execute(1));
        Thread t2 = new Thread(() -> execute(2));
        Thread t3 = new Thread(() -> execute(3));

        t1.start();
        t2.start();
        t3.start();
    }

    private static void execute(int threadNumber) {
        lock.lock();
        try {
            while (current != threadNumber) {
                condition.await();
            }
            System.out.println("Thread " + threadNumber);
            current++;
            condition.signalAll();
        } catch (InterruptedException e) {
            Thread.currentThread().interrupt();
        } finally {
            lock.unlock();
        }
    }
}
