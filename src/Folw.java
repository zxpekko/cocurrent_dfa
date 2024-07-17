/**
 * @Author:zxp
 * @Description:
 * @Date:12:38 2024/7/16
 */
public class Folw {
    private static final Object lock=new Object();
    private static boolean printNum=true;
    public static void main(String[] args) {
        Folw folw = new Folw();
        folw.printNumAndChar();
    }
    public void printNumAndChar(){
        new Thread(this::printNum).start();
        new Thread(this::printChar).start();
    }
    public void printNum(){
        synchronized (lock){
            for(int i=1;i<=3;i++){
                while (!printNum){
                    try {
                        lock.wait();
                    } catch (InterruptedException e) {
                        throw new RuntimeException(e);
                    }
                }
                System.out.print(i);
                printNum=false;
                lock.notifyAll();
            }
        }
    }
    public void printChar(){
        synchronized (lock){
            for(char i='A';i<='C';i++){
                while (printNum){
                    try {
                        lock.wait();
                    } catch (InterruptedException e) {
                        throw new RuntimeException(e);
                    }
                }
                System.out.print(i);
                printNum=true;
                lock.notifyAll();
            }
        }
    }
}
