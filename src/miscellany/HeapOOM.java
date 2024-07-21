package miscellany;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

/**
 * @Author:zxp
 * @Description:
 * @Date:13:16 2024/7/15
 */
public class HeapOOM {
    public static void main(String[] args) {
//        List<Object> result=new ArrayList<>();
//        while (true){
//            result.add(new Object());
//        }
        List<Object> result=new ArrayList<>();
        while (true){
            result.add(new Object());
        }
    }
}
