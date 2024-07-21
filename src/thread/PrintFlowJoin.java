package thread;

/**
 * @Author:zxp
 * @Description:
 * @Date:20:37 2024/7/19
 */
public class PrintFlowJoin {
    public static void main(String[] args) {
        PrintFlowJoin printFlowJoin = new PrintFlowJoin();
        printFlowJoin.print();
    }
    public void print(){
        Thread thread1 = new Thread(this::printNums);
        Thread thread2 = new Thread(() -> {
            try {
                thread1.join();
            } catch (InterruptedException e) {
                throw new RuntimeException(e);
            }
            printChars();
        });
        Thread thread3 = new Thread(() -> {
            try {
                thread2.join();
            } catch (InterruptedException e) {
                throw new RuntimeException(e);
            }
            printChinese();
        });
        thread1.start();
        thread2.start();
        thread3.start();
    }
    public void printNums(){
        for(int i=1;i<=3;i++){
            System.out.print(i);
        }
    }
    public void printChars(){
        for(char i='A';i<='C';i++){
            System.out.print(i);
        }
    }
    public void printChinese(){
        System.out.print("你好");
    }
}