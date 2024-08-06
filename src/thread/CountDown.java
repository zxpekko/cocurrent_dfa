package thread;

import java.util.concurrent.CountDownLatch;

/**
 * @Author:zxp
 * @Description:
 * @Date:21:36 2024/8/6
 */
public class CountDown {
    private CountDownLatch countDownLatch1=new CountDownLatch(1);
    private CountDownLatch countDownLatch2=new CountDownLatch(1);
    private CountDownLatch countDownLatch3=new CountDownLatch(1);
//    private int cur=1;

    public static void main(String[] args) {
        CountDown countDown = new CountDown();
        countDown.print();
    }
    public void print(){
        new Thread(this::printNums).start();
        new Thread(this::printChar).start();
        new Thread(this::printChine).start();
    }
    public void printNums(){
        System.out.println("123");
        countDownLatch1.countDown();
    }
    public void printChar(){
        try {
            countDownLatch1.await();
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }
        System.out.println("abc");
        countDownLatch2.countDown();
    }
    public void printChine(){
        try {
            countDownLatch2.await();
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }
        System.out.println("你好");
    }
}
