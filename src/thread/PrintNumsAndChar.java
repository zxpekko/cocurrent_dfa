package thread;

import com.sun.deploy.trace.Trace;

/**
 * @Author:zxp
 * @Description:
 * @Date:0:39 2024/8/2
 */
public class PrintNumsAndChar {
    private final Object lock=new Object();
    private boolean printNums= true;

    public static void main(String[] args) {
        PrintNumsAndChar printNumsAndChar = new PrintNumsAndChar();
        printNumsAndChar.print();
    }
    public void print(){
        new Thread(this::printNums).start();
        new Thread(this::printChars).start();
    }
    public void printNums(){
        for(int i=1;i<=3;i++){
            synchronized (lock){
                while (!printNums){
                    try {
                        lock.wait();
                    } catch (InterruptedException e) {
                        throw new RuntimeException(e);
                    }
                }
                System.out.print(i);
                lock.notifyAll();
                printNums=false;
            }
        }
    }
    public void printChars(){
        for(char c='A';c<='C';c++){
            synchronized (lock){
                while (printNums){
                    try {
                        lock.wait();
                    } catch (InterruptedException e) {
                        throw new RuntimeException(e);
                    }
                }
                System.out.print(c);
                lock.notifyAll();
                printNums=true;
            }
        }
    }
}