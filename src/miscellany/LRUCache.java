package miscellany;

import java.util.LinkedHashMap;
import java.util.List;

/**
 * @Author:zxp
 * @Description:
 * @Date:20:27 2024/7/15
 */
public class LRUCache {
    private int cap;
    private LinkedHashMap<Integer,Integer> cache=new LinkedHashMap<>();
    public LRUCache(int capacity){
        this.cap=capacity;
    }
    public int get(int key){
        if(cache.containsKey(key)){
            makeRecently(key);
            return cache.get(key);
        }
        return -1;
    }
    public void put(int key,int value){
        if(cache.containsKey(key)){
            makeRecently(key);
            cache.put(key,value);
            return;
        }
        else if(cache.size()>=this.cap){
            Integer oldest = cache.keySet().iterator().next();
            cache.remove(oldest);
        }
        cache.put(key,value);
    }
    public void makeRecently(int key){
        int value=cache.get(key);
        cache.remove(key);
        cache.put(key,value);
    }
}
