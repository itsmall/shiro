package com.example.shirojwt.lock;

import java.util.*;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.CopyOnWriteArrayList;
import java.util.concurrent.CopyOnWriteArraySet;

/**
 * 集合类不安全的问题
 */
public class ArrayListDemo {

    public static void main(String[] args) {

        Map<String, String> map =new ConcurrentHashMap<>();
        //Collections.synchronizedMap(new HashMap<>());
        // new HashMap<>();
        for (int i = 1; i <= 30; i++) {
            new Thread(() -> {
                map.put(Thread.currentThread().getName(),UUID.randomUUID().toString().substring(0, 8));
                System.out.println(map);
            }, String.valueOf(i)).start();
        }

    }

    private static void setNotSafe() {
        Set<String> set = new CopyOnWriteArraySet<>();
        //Collections.synchronizedSet(new HashSet<>());
        new HashSet<>();
        for (int i = 1; i <= 30; i++) {
            new Thread(() -> {
                set.add(UUID.randomUUID().toString().substring(0, 8));
                System.out.println(set);
            }, String.valueOf(i)).start();
        }
    }

    private static void listNotSafe() {
    /* List<String> list = Arrays.asList("a", "b", "c");
     list.forEach(System.out::println);*/
        //等同以上
        List<String> list = new CopyOnWriteArrayList<>();
        //new Vector<>();   线程安全
        //new ArrayList<>(); 线程不安全
        /*list.add("a");
        list.add("b");
        list.add("c");
        for (String s : list) {
            System.out.println(s);
        }*/

        for (int i = 1; i <= 3; i++) {
            new Thread(() -> {
                list.add(UUID.randomUUID().toString().substring(0, 8));
                System.out.println(list);
            }, String.valueOf(i)).start();
        }
        //java.util.ConcurrentModificationException   并发修改异常
        //解决方法
        // 1.new Vector<>();
        // 2.Collections.synchronizedList(new ArrayList<>());
        // 3.new CopyOnWriteArrayList<>();
    }
}
