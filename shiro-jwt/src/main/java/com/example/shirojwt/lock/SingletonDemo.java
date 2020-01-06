package com.example.shirojwt.lock;

public class SingletonDemo {

    private static volatile SingletonDemo instance = null;

    private SingletonDemo() {
        System.out.println(Thread.currentThread().getName() + "\t 我是构造方法（）");
    }

    //DCL模式
    public static SingletonDemo getSingletonDemo() {
        if (instance == null) {
            synchronized (SingletonDemo.class) {
                if (instance == null) {
//                    memory=allocate();
                    instance = new SingletonDemo();
                }
            }
        }
        return instance;
    }

    public static void main(String[] args) {

     /*   //单线程
        System.out.println(SingletonDemo.getSingletonDemo() == SingletonDemo.singletonDemo);
        System.out.println(SingletonDemo.getSingletonDemo() == SingletonDemo.singletonDemo);
        System.out.println(SingletonDemo.getSingletonDemo() == SingletonDemo.singletonDemo);
        System.out.println(SingletonDemo.getSingletonDemo() == SingletonDemo.singletonDemo);
        */

        //多线程
        for (int i = 1; i <= 10; i++) {

            new Thread(() -> {
                SingletonDemo.getSingletonDemo();
            }, String.valueOf(i)).start();

        }

    }
}
