package com.lgl;

import java.sql.SQLOutput;

public class SychronizedDemo1 implements Runnable {

    static int a = 0;

    public static void main(String[] args) {
        SychronizedDemo1 s = new SychronizedDemo1();
        SychronizedDemo1 s2=new SychronizedDemo1();
        Thread t1 = new Thread(s);
        Thread t2 = new Thread(s2);
        t1.start();
        t2.start();
        try {
            t1.join();
            t2.join();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        System.out.println("两个线程运行结束" + a);
    }

    @Override
    public void run() {

        //Sychronized锁定的是当前对象的对象锁，如果两个线程都传入的是同一个target对象
        //那么可以起到互斥锁的作用，不然是没有互斥锁的作用的
        for (int i = 0; i < 100000; i++)
            synchronized (this) {
                a++;
            }
    }
}
