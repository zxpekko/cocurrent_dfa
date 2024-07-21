package miscellany;

import java.util.Stack;

/**
 * @Author:zxp
 * @Description:
 * @Date:13:49 2024/7/15
 */
public class MyQueue<T> {
    private Stack<T> stackIn;
    private Stack<T> stackOut;
    public MyQueue(){
        stackIn=new Stack<>();
        stackOut=new Stack<>();
    }
    public void push(T element){
        stackIn.push(element);
    }
    public T pop(){
        if(stackOut.isEmpty()){
            while (!stackIn.isEmpty()){
                stackOut.push(stackIn.pop());
            }
        }
        return  stackOut.pop();
    }
    public boolean isEmpty(){
        return stackIn.isEmpty()&&stackOut.isEmpty();
    }
}
