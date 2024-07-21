package thread;

/**
 * @Author:zxp
 * @Description:
 * @Date:13:07 2024/7/19
 */
public class Print20NumsSync {
    private final Object lock=new Object();
    private boolean printNums=true;
    public static void main(String[] args) {
        Print20NumsSync print20NumsSync = new Print20NumsSync();
        print20NumsSync.print();
    }
    public void print(){
        new Thread(this::printNums).start();
        new Thread(this::printNumsOu).start();
    }
    public void printNums(){
        synchronized (lock){
            for(int i=1;i<=19;i+=2){
                while (!printNums){
                    try {
                        lock.wait();
                    } catch (InterruptedException e) {
                        throw new RuntimeException(e);
                    }
                }
                System.out.println(i);
                printNums=false;
                lock.notifyAll();
            }
        }
    }
    public void printNumsOu(){
        synchronized (lock){
            for(int i=2;i<=20;i+=2){
                while (printNums){
                    try {
                        lock.wait();
                    } catch (InterruptedException e) {
                        throw new RuntimeException(e);
                    }
                }
                System.out.println(i);
                printNums=true;
                lock.notifyAll();
            }
        }
    }
}