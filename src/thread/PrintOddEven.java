package thread;

/**
 * @Author:zxp
 * @Description:
 * @Date:2:29 2024/8/5
 */
public class PrintOddEven {
    private Object lock=new Object();
    private boolean printOdd=true;

    public static void main(String[] args) {
        PrintOddEven printOddEven = new PrintOddEven();
        printOddEven.printNums();
    }
    public void printNums(){
        new Thread(this::printOdd).start();
        new Thread(this::printEven).start();
    }
    public void printOdd(){
        for(int i=1;i<=19;i+=2){
            synchronized (lock){
                while (!printOdd){
                    try {
                        lock.wait();
                    } catch (InterruptedException e) {
                        throw new RuntimeException(e);
                    }
                }
                System.out.println(i);
                printOdd=false;
                lock.notifyAll();
            }
        }
    }
    public void printEven(){
        for(int i=2;i<=20;i+=2){
            synchronized (lock){
                while (printOdd){
                    try {
                        lock.wait();
                    } catch (InterruptedException e) {
                        throw new RuntimeException(e);
                    }
                }
                System.out.println(i);
                printOdd=true;
                lock.notifyAll();
            }
        }
    }
}