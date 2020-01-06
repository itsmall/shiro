package com.example.shirojwt.lock;

import java.util.concurrent.atomic.AtomicInteger;

/**
 * 什么是CAS       compareAndSet
 * 比较并交换
 */
public class CASDemo {

    public static void main(String[] args) {

        int i = 1, i1 = 2;
        CAS(i, i1);

        CAS(0, i1);

    }

    private static void CAS(int i, int i1) {
        AtomicInteger atomicInteger = new AtomicInteger();

        if (atomicInteger.compareAndSet(i, i1)) {
            System.out.println("修改后的值" + atomicInteger.get());
        }

    }

}
