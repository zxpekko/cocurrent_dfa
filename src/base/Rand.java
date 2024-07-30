package base;

import java.util.concurrent.ThreadPoolExecutor;

/**
 * @Author:zxp
 * @Description:
 * @Date:19:44 2024/7/29
 */
public class Rand implements Cloneable{
    public static void main(String[] args) {
        System.out.println((int) (Math.random() * 10));
        System.out.println(Math.round(-1.5));

    }

    @Override
    protected Object clone() throws CloneNotSupportedException {
        return super.clone();
    }
}
