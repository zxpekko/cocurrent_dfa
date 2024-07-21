package thread;

import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.ReentrantLock;

/**
 * @Author:zxp
 * @Description:
 * @Date:19:40 2024/7/19
 */
public class LockConditionFlow {
    private int currentNums=1;
    private ReentrantLock lock=new ReentrantLock();
    private Condition condition=lock.newCondition();

    public static void main(String[] args) {
        LockConditionFlow lockConditionFlow = new LockConditionFlow();
        lockConditionFlow.printFlow();
    }
    public void printFlow(){
        new Thread(()->printNums(1)).start();
        new Thread(()->printChars(2)).start();
        new Thread(()->printChinese(3)).start();
    }
    public void printNums(int excuNum){
        lock.lock();
        while (excuNum!=currentNums){
            try {
                condition.await();
            } catch (InterruptedException e) {
                throw new RuntimeException(e);
            }
        }
            for(int i=1;i<=3;i++){
                System.out.print(i);
            }
            currentNums++;
        condition.signalAll();
        lock.unlock();
    }
    public void printChars(int excuNums){
        lock.lock();
        while (excuNums!=currentNums){
            try {
                condition.await();
            } catch (InterruptedException e) {
                throw new RuntimeException(e);
            }
        }
        for(char i='A';i<='C';i++){
            System.out.print(i);
        }
        currentNums++;
        condition.signal();
        lock.unlock();
    }
    public void printChinese(int excuNums){
        lock.lock();
        while (excuNums!=currentNums){
            try {
                condition.await();
            } catch (InterruptedException e) {
                throw new RuntimeException(e);
            }
        }
        System.out.print("你好");
        currentNums++;
        condition.signalAll();
        lock.unlock();
    }
}