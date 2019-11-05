package com.lgl;

//饱汉式单例模式出现线程安全问题
public class DoubleCheckSingleton implements Runnable{
    private DoubleCheckSingleton doubleCheckSingleton;

    private DoubleCheckSingleton(){}

    public DoubleCheckSingleton getInstance(){
        if(doubleCheckSingleton==null){
            try {
                Thread.sleep(100);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            System.out.println("创建一个新的实例了");
            doubleCheckSingleton=new DoubleCheckSingleton();
        }
        return doubleCheckSingleton;
    }

    @Override
    public void run() {
        getInstance();
    }

    public static void main(String[] args) {
        DoubleCheckSingleton d=new DoubleCheckSingleton();
        Thread t1=new Thread(d);
        Thread t2=new Thread(d);
        t1.start();
        t2.start();
    }
}
