import com.sun.xml.internal.ws.addressing.WsaActionUtil;

/**
 * @Author:zxp
 * @Description:
 * @Date:13:55 2024/7/15
 */
public class Prime {
    public static void main(String[] args) {
        int count=0;
        for(int i=101;i<200;i++){
            if(judgePrime(i)){
                System.out.println(i);
                count++;
            }

        }
        System.out.println("一共有"+count+"个质数");
    }
    public static boolean judgePrime(int num){
        for(int i=2;i<=Math.sqrt(num);i++){
            if(num%i==0)
                return false;
        }
        return true;
    }
}
