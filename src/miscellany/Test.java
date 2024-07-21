package miscellany;

import java.util.concurrent.TimeUnit;
import java.util.concurrent.locks.ReentrantLock;

/**
 * @Author:zxp
 * @Description:
 * @Date:20:00 2024/5/10
 */
public class Test {
    public static void main(String[] args) {
        System.out.println(Person.nums);
        Person person = new Person();
        person.setNums();
        System.out.println(Person.nums);
        Person person1 = new Person();
        person1.setNums();
        System.out.println(Person.nums);
        Thread thread = new Thread(() -> {
            System.out.println("hello");
        }, "t1");
        thread.start();
        ReentrantLock reentrantLock1 = new ReentrantLock();
        ReentrantLock reentrantLock2 = new ReentrantLock();
        new Thread(()->{
            reentrantLock1.lock();
            try {
                TimeUnit.SECONDS.sleep(1);
            } catch (InterruptedException e) {
                throw new RuntimeException(e);
            }
            System.out.println("t2持有了锁1");
//            reentrantLock1.unlock();
            reentrantLock2.lock();
            reentrantLock1.unlock();
        },"t2").start();
        new Thread(()->{
            reentrantLock2.lock();
            try {
                TimeUnit.SECONDS.sleep(1);
            } catch (InterruptedException e) {
                throw new RuntimeException(e);
            }
            System.out.println("t3持有了锁2");
//            reentrantLock2.unlock();
            reentrantLock1.lock();
            reentrantLock2.unlock();
        },"t3").start();
    }
}
class Person{
    public static  int nums=100;
    public void setNums(){
        nums--;
    }
}
