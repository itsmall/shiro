package com.example.shirojwt.lock;

import java.util.concurrent.BlockingQueue;
import java.util.concurrent.SynchronousQueue;
import java.util.concurrent.TimeUnit;

/**
 * 1.队列
 * <p>
 * 2.阻塞队列
 * 2.1  阻塞队列有没有好的一面
 * <p>
 * 2.2 不得不阻塞，你如何管理
 */
public class BlockingQueueDemo {
    public static void main(String[] args) throws InterruptedException {

//        List list = new ArrayList();

        BlockingQueue<String> blockingQueue = new SynchronousQueue<>();

        new Thread(() -> {
            try {
                System.out.println(Thread.currentThread().getName() + "\t put 1");
                blockingQueue.put("1");
                System.out.println(Thread.currentThread().getName() + "\t put 2");
                blockingQueue.put("2");
                System.out.println(Thread.currentThread().getName() + "\t put 3");
                blockingQueue.put("3");
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }, "aaa").start();

        new Thread(() -> {

            try {
                TimeUnit.SECONDS.sleep(5);
                System.out.println(Thread.currentThread().getName() + "\t" + blockingQueue.take());
                TimeUnit.SECONDS.sleep(5);
                System.out.println(Thread.currentThread().getName() + "\t" + blockingQueue.take());
                TimeUnit.SECONDS.sleep(5);
                System.out.println(Thread.currentThread().getName() + "\t" + blockingQueue.take());
            } catch (InterruptedException e) {
                e.printStackTrace();
            }

        }, "bbb").start();

//        BlockingQueue<String> blockingQueue = new ArrayBlockingQueue<>(3);

      /*
      //暂时阻塞
        System.out.println(blockingQueue.offer("a", 2l, TimeUnit.SECONDS));
        System.out.println(blockingQueue.offer("a", 2l, TimeUnit.SECONDS));
        System.out.println(blockingQueue.offer("a", 2l, TimeUnit.SECONDS));
        System.out.println(blockingQueue.offer("a", 2l, TimeUnit.SECONDS));
        System.out.println(blockingQueue.poll( 2l, TimeUnit.SECONDS));
        System.out.println(blockingQueue.poll( 2l, TimeUnit.SECONDS));
        System.out.println(blockingQueue.poll( 2l, TimeUnit.SECONDS));
*/


        /*
        //一直阻塞
        blockingQueue.put("a");
        blockingQueue.put("a");
        blockingQueue.put("a");
        blockingQueue.take();
        blockingQueue.put("a");
        blockingQueue.take();
        blockingQueue.take();
        blockingQueue.take();*/




      /*  //插入成功 true  失败 false
        System.out.println(blockingQueue.offer("a"));
        System.out.println(blockingQueue.offer("a"));
        System.out.println(blockingQueue.offer("a"));
        // System.out.println(blockingQueue.offer("a"));

        //同element
        System.out.println(blockingQueue.peek());

        //移除成功返回值  失败返回null
        System.out.println(blockingQueue.poll());
        System.out.println(blockingQueue.poll());
        System.out.println(blockingQueue.poll());
        System.out.println(blockingQueue.poll());
*/


/*
        System.out.println(blockingQueue.add("a"));
        System.out.println(blockingQueue.add("b"));
        System.out.println(blockingQueue.add("c"));
        //IllegalStateException: Queue full  add异常
        //System.out.println(blockingQueue.add("d"));

        //队列空不空，有-->是谁
        System.out.println(blockingQueue.element());

        System.out.println(blockingQueue.remove());
        System.out.println(blockingQueue.remove());
        System.out.println(blockingQueue.remove());
        // NoSuchElementException       remove异常
        //System.out.println(blockingQueue.remove());*/

    }
}
