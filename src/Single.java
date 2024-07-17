import org.omg.CORBA.PUBLIC_MEMBER;

/**
 * @Author:zxp
 * @Description:
 * @Date:14:22 2024/4/22
 */
public class Single {//饿汉
    //饿汉式
    private Single(){

    }
    private static Single instance=new Single();
    public static Single getInstance(){
        return instance;
    }
}
class LazyMan{//懒汉
    private LazyMan(){

    }
    private static LazyMan instance;
    public static LazyMan getInstance(){
        synchronized (LazyMan.class){
            if(instance==null)
                instance=new LazyMan();//sync同步代码块，线程安全。
        }
        return instance;
    }
}
class Hungry{//饿汉式
    private Hungry(){

    }
    private static Hungry instance=new Hungry();
    public static Hungry getInstance(){
        return instance;
    }
}
class LazyMan1{//线程安全的懒汉式单例模式。
    private LazyMan1(){

    }
    private static LazyMan1 instance;
    public static LazyMan1 getInstance(){
        synchronized (LazyMan1.class){
            if(instance==null){
                instance=new LazyMan1();
            }
        }
        return instance;
    }
}
class Hungry1{
    private static Hungry1 instance=new Hungry1();
    private Hungry1(){}
    public static Hungry1 getInstance(){
        return instance;
    }
}
class LazyMan2{
    private static LazyMan2 instance;
    private LazyMan2(){

    }
    public static LazyMan2 getInstance(){
        synchronized (LazyMan2.class){
            if(instance==null)
                instance=new LazyMan2();
        }
        return instance;
    }
}
class Hungry2{
    private Hungry2(){

    }
    private static Hungry2 instance=new Hungry2();
    public static Hungry2 getInstance(){
        return instance;
    }
}
class LazyMan3{
    private LazyMan3(){

    }
    private static LazyMan3 instance;
    public static LazyMan3 getInstance(){
        synchronized (LazyMan3.class){
            if(instance==null)
                instance=new LazyMan3();
        }
        return instance;
    }
}