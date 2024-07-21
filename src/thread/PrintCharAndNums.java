package thread;

import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.ReentrantLock;

/**
 * @Author:zxp
 * @Description:
 * @Date:20:31 2024/7/18
 */
//public class PrintCharAndNums {
//    private ReentrantLock lock=new ReentrantLock();
//    private Condition printNums=lock.newCondition();
//    private Condition printChars=lock.newCondition();
//    private boolean printNumsBoolen=true;
//    public static void main(String[] args) {
//        PrintCharAndNums printCharAndNums = new PrintCharAndNums();
//        printCharAndNums.printNumsAndChars();
//    }
//    public void printNumsAndChars(){
//        new Thread(this::printNums).start();
//        new Thread(this::printChars).start();
//    }
//    public void printNums(){
//        for(int i=1;i<=3;i++){
//            lock.lock();
//            while (!printNumsBoolen){
//                try {
//                    printNums.await();
//                } catch (InterruptedException e) {
//                    throw new RuntimeException(e);
//                }
//            }
//            System.out.print(i);
//            printNumsBoolen=false;
//            lock.unlock();
//            printChars.signalAll();
//        }
//    }
//    public void printChars(){
//        for(char i='A';i<='C';i++){
//            lock.lock();
//            while (printNumsBoolen){
//                try {
//                    printChars.await();
//                } catch (InterruptedException e) {
//                    throw new RuntimeException(e);
//                }
//            }
//            System.out.print(i);
//            printNumsBoolen=true;
//            lock.unlock();
//            printNums.signalAll();
//        }
//    }
//}
public class PrintCharAndNums{
    private final ReentrantLock lock=new ReentrantLock();
    private Condition condition=lock.newCondition();
    public static void main(String[] args) {
        PrintCharAndNums printCharAndNums = new PrintCharAndNums();
        printCharAndNums.printNumsAndChars();
    }
    public void printNumsAndChars(){
        new Thread(this::printNums).start();
        new Thread(this::printChars).start();
    }
    public void printNums(){
        for(int i=1;i<=3;i++){
            lock.lock();
            System.out.print(i);
            condition.signal();
            if(i<3) {
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
        for(char i='A';i<='C';i++){
            lock.lock();
            System.out.print(i);
            condition.signal();
            if(i<'C') {
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