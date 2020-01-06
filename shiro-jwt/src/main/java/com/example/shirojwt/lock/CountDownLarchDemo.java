package com.example.shirojwt.lock;

import lombok.Getter;

import java.util.concurrent.CountDownLatch;

/**
 * 倒计时
 */
enum CounttryEnum {
    ONE(1, "齐国"), TWO(2, "楚国"), THREE(3, "燕国"), FOUR(4, "赵国"), FIVE(5, "魏国"), SIX(6, "韩国");

    @Getter
    private Integer retCode;
    @Getter
    private String retMessage;

    CounttryEnum(Integer retCode, String retMessage) {
        this.retCode = retCode;
        this.retMessage = retMessage;
    }

    public static CounttryEnum forEach_CounttryEnum(int index) {
        CounttryEnum[] enums = CounttryEnum.values();
        for (CounttryEnum element : enums) {

            if (index == element.getRetCode()) return element;
        }
        return null;
    }

}

public class CountDownLarchDemo {

    public static void main(String[] args) {

        CountDownLatch countDownLatch = new CountDownLatch(6);

        for (int i = 1; i <= 6; i++) {
            new Thread(() -> {
                System.out.println(Thread.currentThread().getName() + "\t 被灭！");
                countDownLatch.countDown();
            }, CounttryEnum.forEach_CounttryEnum(i).getRetMessage()).start();
        }

        try {
            countDownLatch.await();
        } catch (InterruptedException e) {
            e.printStackTrace();
        } finally {
            System.out.println(Thread.currentThread().getName() + "\t 秦国统一天下！");
            System.out.println();
            System.out.println();
            System.out.println(CounttryEnum.ONE);
            System.out.println(CounttryEnum.ONE.getRetCode());
            System.out.println(CounttryEnum.ONE.getRetMessage());
        }

    }

    private static void closeDoor() {
        CountDownLatch countDownLatch = new CountDownLatch(6);

        for (int i = 1; i <= 6; i++) {
            new Thread(() -> {
                System.out.println(Thread.currentThread().getName() + "\t 离开教室！");
                countDownLatch.countDown();
            }, String.valueOf(i)).start();
        }

        try {
            countDownLatch.await();
        } catch (InterruptedException e) {
            e.printStackTrace();
        } finally {
            System.out.println(Thread.currentThread().getName() + "\t 最后关门走人！");

        }
    }
}
