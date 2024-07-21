package miscellany;

import java.util.LinkedList;
import java.util.Queue;

/**
 * @Author:zxp
 * @Description:
 * @Date:13:42 2024/7/15
 */
public class MyStack<T> {
    private Queue<T> queue;
    private Queue<T> tempQueue;
    public MyStack(){
        queue=new LinkedList<>();
        tempQueue=new LinkedList<>();
    }
    public void push(T element){
        queue.offer(element);
    }
    public T pop(){
        if(queue.isEmpty())
            throw new RuntimeException("stack is empty");
        while (queue.size()>1)
            tempQueue.offer(queue.poll());
        T poll = queue.poll();
        Queue<T> temp=queue;
        queue=tempQueue;
        tempQueue=temp;
        return poll;
    }
    public boolean isEmpty(){
        return queue.isEmpty();
    }
}
