package com.lgl;

public class SingletonEnumTest {
    public static void main(String[] args) {
        SingletonEnum instance1 = SingletonEnum.INSTANCE;
        SingletonEnum instance2 = SingletonEnum.INSTANCE;
        System.out.println(instance1==instance2);
    }
}
