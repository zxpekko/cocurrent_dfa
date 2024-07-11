import java.sql.Time;
import java.util.Map;
import java.util.concurrent.Executors;
import java.util.concurrent.LinkedBlockingDeque;
import java.util.concurrent.ThreadPoolExecutor;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.locks.ReentrantLock;
import java.util.concurrent.locks.ReentrantReadWriteLock;

/**
 * @Author:zxp
 * @Description:
 * @Date:16:03 2024/5/18
 */
public class ReadWriteLock {

    public static void main(String[] args) {
        ReadWriteLock readWriteLock = new ReadWriteLock();
        readWriteLock.test();
    }
    private ReentrantReadWriteLock reentrantReadWriteLock=new ReentrantReadWriteLock();
    private static final InheritableThreadLocal<Map<String,Object>> THREAD_LOCAL=new InheritableThreadLocal<>();
    public void test(){
        ThreadPoolExecutor threadPoolExecutor = new ThreadPoolExecutor(5, 10, 10, TimeUnit.SECONDS,
                new LinkedBlockingDeque<>(3), Executors.defaultThreadFactory(), new ThreadPoolExecutor.CallerRunsPolicy());
        new Thread(()->getData(Thread.currentThread(),3),"t2get").start();
        new Thread(()->getData(Thread.currentThread(),2),"t3get").start();
        new Thread(()->updateData(Thread.currentThread()),"t1update").start();

        new Thread(()->getData(Thread.currentThread(),1),"t4get").start();
    }
    public void getData(Thread thread,long time){
        reentrantReadWriteLock.readLock().lock();
        try {
            TimeUnit.SECONDS.sleep(time);
            System.out.println(thread+"读取数据");
        } catch (Exception e) {

        } finally {
            reentrantReadWriteLock.readLock().unlock();
        }
    }
    public void updateData(Thread thread){
        reentrantReadWriteLock.writeLock().lock();
        try {
            TimeUnit.SECONDS.sleep(2);
            System.out.println(thread+"操作数据");
        } catch (Exception e) {
            throw new RuntimeException(e);
        } finally {
            reentrantReadWriteLock.writeLock().unlock();
        }
    }
}
