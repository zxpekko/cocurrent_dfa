/**
 * @Author:zxp
 * @Description:
 * @Date:15:41 2024/7/14
 */
public class PrintTwoNums {
    private static final Object lock=new Object();
    private static boolean printSingle=true;

    public static void main(String[] args) {
        new Thread(()->printSingleNums()).start();
        new Thread(()->printDoubleNums()).start();
    }
    public static void printSingleNums(){
        synchronized (lock){
            for(int i=1;i<=99;i+=2){
                while (!printSingle){
                    try {
                        lock.wait();
                    } catch (InterruptedException e) {
                        throw new RuntimeException(e);
                    }
                }
                System.out.println(i);
                printSingle=false;
                lock.notifyAll();
            }
        }
    }
    public static void printDoubleNums(){
        synchronized (lock){
            for(int i=2;i<=100;i+=2){
                while (printSingle){
                    try {
                        lock.wait();
                    } catch (InterruptedException e) {
                        throw new RuntimeException(e);
                    }
                }
                System.out.println(i);
                printSingle=true;
                lock.notifyAll();
            }
        }
    }
}
