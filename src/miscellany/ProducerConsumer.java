package miscellany;

import java.util.LinkedList;
import java.util.Queue;
import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

/**
 * @Author:zxp
 * @Description:
 * @Date:20:11 2024/7/18
 */

public class ProducerConsumer {
    private final Queue<Integer> queue = new LinkedList<>();
    private final int maxSize = 10;
    private final Lock lock = new ReentrantLock();
    private final Condition notFull = lock.newCondition();
    private final Condition notEmpty = lock.newCondition();

    public static void main(String[] args) {
        ProducerConsumer pc = new ProducerConsumer();

        Thread producer = new Thread(pc.new Producer());
        Thread consumer = new Thread(pc.new Consumer());

        producer.start();
        consumer.start();
    }

    class Producer implements Runnable {
        @Override
        public void run() {
            while (true) {
                lock.lock();
                try {
                    while (queue.size() == maxSize) {
                        notFull.await(); // 等待队列有空闲位置
                    }
                    int item = (int) (Math.random() * 100);
                    queue.offer(item);
                    System.out.println("Produced: " + item);
                    notEmpty.signalAll(); // 通知消费者有数据可用
                } catch (InterruptedException e) {
                    Thread.currentThread().interrupt();
                } finally {
                    lock.unlock();
                }
                try {
                    Thread.sleep((int) (Math.random() * 1000));
                } catch (InterruptedException e) {
                    Thread.currentThread().interrupt();
                }
            }
        }
    }

    class Consumer implements Runnable {
        @Override
        public void run() {
            while (true) {
                lock.lock();
                try {
                    while (queue.isEmpty()) {
                        notEmpty.await(); // 等待队列有数据
                    }
                    int item = queue.poll();
                    System.out.println("Consumed: " + item);
                    notFull.signalAll(); // 通知生产者可以继续生产
                } catch (InterruptedException e) {
                    Thread.currentThread().interrupt();
                } finally {
                    lock.unlock();
                }
                try {
                    Thread.sleep((int) (Math.random() * 1000));
                } catch (InterruptedException e) {
                    Thread.currentThread().interrupt();
                }
            }
        }
    }
}
