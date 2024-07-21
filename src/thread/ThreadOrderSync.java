package thread;

/**
 * @Author:zxp
 * @Description:
 * @Date:20:25 2024/7/19
 */
public class ThreadOrderSync {
    private final  Object lock=new Object();
    private int current=1;

    public static void main(String[] args) {
        ThreadOrderSync threadOrderSync = new ThreadOrderSync();
        threadOrderSync.print();
    }
    public void print(){
        new Thread(()->printNums(3)).start();
        new Thread(()->printChars(2)).start();
        new Thread(()->printChine(1)).start();
    }
    public void printNums(int execNums){
        synchronized (lock){
            while (execNums!=current){
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
    public void printChars(int execNums){
        synchronized (lock){
            while (execNums!=current){
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
    public void printChine(int execNums){
        synchronized (lock){
            while (execNums!=current){
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
