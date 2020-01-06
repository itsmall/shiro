package com.example.shirojwt.lock;

import java.util.Random;
import java.util.concurrent.Semaphore;
import java.util.concurrent.TimeUnit;

/**
 * 占位
 */
public class SemaphoreDemo {

    public static void main(String[] args) {
        //模拟3个用餐位置
        Semaphore semaphore = new Semaphore(3);
        //6个人要用餐
        for (int i = 1; i <= 6; i++) {

            int finalI = i;
            int i1 = new Random().nextInt(10 - 3 + 1) + 3;

            new Thread(() -> {
                try {
                    semaphore.acquire();  //位置-1
                    System.out.println(Thread.currentThread().getName() + "\t 抢到位置！");
                    TimeUnit.SECONDS.sleep(i1);
                    System.out.println(Thread.currentThread().getName() + "\t 停车" + i1 + "秒后离开车位！");
                    semaphore.release(); //位置+1
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }, String.valueOf(i)).start();
        }
    }

}
