package thread;

import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.ReentrantLock;

/**
 * @Author:zxp
 * @Description:
 * @Date:10:12 2024/7/19
 */
public class Print20Nums {
    private final ReentrantLock lock=new ReentrantLock();
    private final Condition condition=lock.newCondition();
    public static void main(String[] args) {
        Print20Nums print20Nums = new Print20Nums();
        print20Nums.printNumsAndChars();
    }
    public void printNumsAndChars(){
        new Thread(this::printNums).start();
        new Thread(this::printChars).start();

    }
    public void printNums(){
        for(int i=1;i<=19;i+=2){
            lock.lock();
            System.out.println(i);
            condition.signal();
            if(i<19) {
                try {
                    condition.await();
                } catch (InterruptedException e) {
                    throw new RuntimeException(e);
                }
            }
            lock.unlock();
        }
    }
    public void printChars(){
        for(int i=2;i<=20;i+=2){
            lock.lock();
            System.out.println(i);
            condition.signal();
            if(i<20) {
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
