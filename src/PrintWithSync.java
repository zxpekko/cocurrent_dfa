/**
 * @Author:zxp
 * @Description:
 * @Date:15:42 2024/7/14
 */
public class PrintWithSync {
    private static final Object lock=new Object();
    private static volatile boolean isBoolen=true;

    public static void main(String[] args) {
        new Thread(()->printNums()).start();
        new Thread(()->printChar()).start();
    }
    public static void printNums(){
        synchronized (lock){
            for(int i=1;i<=3;i++){
                while (!isBoolen) {
                    try {
                        lock.wait();
                    } catch (InterruptedException e) {
                        throw new RuntimeException(e);
                    }
                }
                System.out.print(i);
                isBoolen=false;
                lock.notifyAll();
            }
        }
    }
    public static void printChar(){
        synchronized (lock){
            for(char i='A';i<='C';i++){
                while (isBoolen){
                    try {
                        lock.wait();
                    } catch (InterruptedException e) {
                        throw new RuntimeException(e);
                    }
                }
                System.out.print(i);
                isBoolen=true;
                lock.notifyAll();
            }
        }

    }
}
