package thread;

import java.util.concurrent.Executors;

/**
 * @Author:zxp
 * @Description:
 * @Date:12:40 2024/7/21
 */
public class ExcuteOrder {
    private final Object lock=new Object();
    private int current=1;
    public static void main(String[] args) {
        ExcuteOrder excuteOrder = new ExcuteOrder();
        excuteOrder.printOrder();
    }
    public void printOrder(){
        new Thread(()->printNums(3)).start();
        new Thread(()->printChars(2)).start();
        new Thread(()->printCH(1)).start();
    }
    public void printNums(int orderNum){
        synchronized (lock){
            while (orderNum!=current) {
                try {
                    lock.wait();
                } catch (InterruptedException e) {
                    throw new RuntimeException(e);
                }
            }
            for(int i=1;i<=3;i++){
                System.out.print(i);
            }
            current++;
            lock.notifyAll();
        }
    }
    public void printChars(int orderNum){
        synchronized (lock){
            while (orderNum!=current){
                try {
                    lock.wait();
                } catch (InterruptedException e) {
                    throw new RuntimeException(e);
                }
            }
            for(char i='A';i<='C';i++){
                System.out.print(i);
            }
            current++;
            lock.notifyAll();
        }
    }
    public void printCH(int orderNum){
        synchronized (lock){
            while (orderNum!=current){
                try {
                    lock.wait();
                } catch (InterruptedException e) {
                    throw new RuntimeException(e);
                }
            }
            System.out.print("你好");
            current++;
            lock.notifyAll();
        }
    }
}

