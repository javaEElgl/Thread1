package com.lgl;

//双重检查模式保证的线程安全问题
public class DoubleCheckSingleton2 implements Runnable{
    private volatile static DoubleCheckSingleton2 doubleCheckSingleton;

    private DoubleCheckSingleton2(){}

    public static DoubleCheckSingleton2 getInstance(){
        if(doubleCheckSingleton==null){
            //通过类锁我们只让一个线程进入创建实例
            synchronized (DoubleCheckSingleton2.class){
                //如果我们不加这个判断那么我们还是可能会产生两个实例
                System.out.println("准备产生新的实例了");
                if(doubleCheckSingleton==null) {
                    //因为类的实例化不是原子操作，可能会被JMM重排，从而，在没有完全创建好对象时。
                    //因为synchronized保证了可见性，让别的线程判断不为空就返回了对象，从而可能产生空指针问题
                    //所以我们要把实例加上volatile关键字，禁止重排序
                    doubleCheckSingleton = new DoubleCheckSingleton2();
                    System.out.println("创建好了");
                }
            }
        }
        return doubleCheckSingleton;
    }

    @Override
    public void run() {
        getInstance();
    }

    public static void main(String[] args) {
        DoubleCheckSingleton2 d=new DoubleCheckSingleton2();
        Thread t1=new Thread(d);
        Thread t2=new Thread(d);
        t1.start();
        t2.start();
    }
}
