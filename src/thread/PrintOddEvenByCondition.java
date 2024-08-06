package thread;

import java.util.concurrent.CountDownLatch;
import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.ReentrantLock;

/**
 * @Author:zxp
 * @Description:
 * @Date:21:15 2024/8/6
 */
public class PrintOddEvenByCondition {
    public static void main(String[] args) {
        PrintOddEvenByCondition printOddEvenByCondition = new PrintOddEvenByCondition();
        printOddEvenByCondition.printNums();
    }
    private ReentrantLock lock=new ReentrantLock();
    private Condition condition=lock.newCondition();
    private CountDownLatch countDownLatch=new CountDownLatch(1);
    public void printNums(){
        new Thread(this::printOdd).start();
        new Thread(this::printEven).start();
    }
    public void printOdd(){
        for(int i=1;i<=19;i+=2){
            lock.lock();
            System.out.println(i);
            condition.signalAll();
            if(i<19){
                try {
                    condition.await();
                } catch (InterruptedException e) {
                    throw new RuntimeException(e);
                }
            }
            lock.unlock();
        }
    }
    public void printEven(){
        for(int i=2;i<=20;i+=2){
            lock.lock();
            System.out.println(i);
            condition.signalAll();
            if(i<20){
                try {
                    condition.await();
                } catch (InterruptedException e) {
                    throw new RuntimeException(e);
                }
            }
            lock.unlock();
        }
    }
}