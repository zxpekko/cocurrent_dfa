package thread;

/**
 * @Author:zxp
 * @Description:
 * @Date:22:12 2024/7/15
 */
public class Shunxu {
    public static void main(String[] args) {
        Thread thread1 = new Thread(() -> {
            System.out.println("1");
        });
        Thread thread2 = new Thread(() -> {
            try {
                thread1.join();
            } catch (InterruptedException e) {
                throw new RuntimeException(e);
            }
            System.out.println("2");
        });
        Thread thread3 = new Thread(() -> {
            try {
                thread2.join();
            } catch (InterruptedException e) {
                throw new RuntimeException(e);
            }
            System.out.println("3");
        });
        thread1.start();
        thread3.start();
        thread2.start();
        Shunxu shunxu = new Shunxu();
        shunxu.Shunxu();

    }
    public void Shunxu(){
        Thread thread1 = new Thread(() -> {
            try {
                Thread.sleep(200);
            } catch (InterruptedException e) {
                throw new RuntimeException(e);
            }
            get1();
        });
        Thread thread2 = new Thread(() -> {
            try {
                thread1.join();
                Thread.sleep(200);
            } catch (InterruptedException e) {
                throw new RuntimeException(e);
            }
            get2();
        });
        Thread thread3 = new Thread(() -> {
            try {
                thread2.join();
                Thread.sleep(200);
            } catch (InterruptedException e) {
                throw new RuntimeException(e);
            }
            get3();
        });
        thread1.start();
        thread2.start();
        thread3.start();
    }
    public void get1(){
        System.out.println("1");
    }
    public void get2(){
        System.out.println("2");
    }
    public void get3(){
        System.out.println("3");
    }
}
