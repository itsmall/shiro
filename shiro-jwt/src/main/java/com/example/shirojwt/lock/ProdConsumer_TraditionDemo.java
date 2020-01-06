package com.example.shirojwt.lock;

/*
题目 ：一个初始值为零的变量，两个线程对其交替操作，一个加一 一个减一，来5轮

 */

import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

class ShareData {

    private int num = 0;
    private Lock lock = new ReentrantLock();
    private Condition condition = lock.newCondition();

    public void increment() throws InterruptedException {

        lock.lock();
        try {
            //1. 判断
            while (num != 0) {
                //等待 生产
                condition.await();
            }
            //2. 干活
            num++;
            System.out.println(Thread.currentThread().getName() + "\t" + num);
            //3.通知 唤醒

            condition.signalAll();

        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            lock.unlock();
        }
    }

    public void decrement() {
        lock.lock();
        try {

            //1. 判断
            while (num == 0) {
                //等待 生产
                condition.await();
            }
            //2. 干活
            num--;
            System.out.println(Thread.currentThread().getName() + "\t" + num);
            //3.通知 唤醒
            condition.signalAll();

        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            lock.unlock();
        }

    }

}

public class ProdConsumer_TraditionDemo {

    public static void main(String[] args) {
        ShareData shareData = new ShareData();

        new Thread(() -> {
            for (int i = 1; i <= 5; i++) {
                try {
                    shareData.increment();
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        }, "aa").start();
        new Thread(() -> {
            for (int i = 1; i <= 5; i++) {
                shareData.decrement();
            }
        }, "bb").start();

    }

}
