package com.example.shirojwt.lock;

import java.util.concurrent.TimeUnit;
import java.util.concurrent.atomic.AtomicInteger;
import java.util.concurrent.locks.ReentrantLock;

class MyData {
    //volatile 可见性 有序性 没有原子性
    volatile int numNo = 0;

    public void addTo60() {
        this.numNo = 60;
    }

    public void addPlusPlus() {
        numNo++;
    }

    AtomicInteger atomicInteger = new AtomicInteger();

    public void addAtomic() {
        atomicInteger.getAndIncrement();
    }

}

public class Test {
    static ReentrantLock reentrantLock = new ReentrantLock();

    public static void main(String[] args) {

        MyData myData = new MyData();

        for (int i = 1; i <= 20; i++) {

            new Thread(() -> {
                for (int j = 1; j <= 1000; j++) {
                    //不保证原子性
                    myData.addPlusPlus();
                    //保证原子性
                    myData.addAtomic();
                }
            }, String.valueOf(i)).start();
        }

        //默认 2个线程  main 、 后台线程
        //等待计算完成后 在查看最终值
        while (Thread.activeCount() > 2) {
            Thread.yield();
        }
        System.out.println(Thread.currentThread().getName() + "\t finally numNo value : " + myData.numNo);
        System.out.println(Thread.currentThread().getName() + "\t finally atomicInteger value : " + myData.atomicInteger);


    }

    private static void TestThread() {
        Thread t1 = new Thread() {
            @Override
            public void run() {
                TestSync();
            }
        };
        t1.setName("t1");
        Thread t2 = new Thread() {
            @Override
            public void run() {
                TestSync();
            }
        };
        t2.setName("t2");
        t1.start();
        t2.start();
        // System.out.println("main");
    }

    /**
     * 可以保证可见性，及时通知其他线程 ，主物理内存的值已经被修改
     */
    private static void seeOKByVolatile() {
        MyData myData = new MyData();

        new Thread(() -> {
            System.out.println(Thread.currentThread().getName() + "\t come in");

            try {
                TimeUnit.SECONDS.sleep(3);
            } catch (InterruptedException e) {
                e.printStackTrace();
            } finally {
                myData.addTo60();
                System.out.println(Thread.currentThread().getName() + "\t updated numNo value:  " + myData.numNo);
            }
        }, "aaa").start();

        while (myData.numNo == 0) {

        }
        System.out.println(Thread.currentThread().getName() + "\t missions is over  numNo: " + myData.numNo);
    }

    public static void TestSync() {
//        synchronized ()
        reentrantLock.lock();
        System.out.println(Thread.currentThread().getName());
        try {
            Thread.sleep(2000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        } finally {
            reentrantLock.unlock();
        }

    }

}
