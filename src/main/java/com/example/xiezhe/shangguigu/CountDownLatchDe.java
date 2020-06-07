package com.example.xiezhe.shangguigu;

import java.util.concurrent.CountDownLatch;

/**
 * @author xiezhe
 * @date 2020/5/21 16:49
 * @mondify TODO
 * @copyright gofun
 */
public class CountDownLatchDe {

    public static void main(String[] args) throws InterruptedException {
        CountDownLatch countDownLatch = new CountDownLatch(6);
        for (int i = 0; i <= 6; i++) {
            new Thread(()->{
                System.out.println(Thread.currentThread().getName()+"\t 离开教室");
                countDownLatch.countDown();
            },i+"").start();
        }
        countDownLatch.await();
        System.out.println(Thread.currentThread().getName()+"\t *****班长最后关门走人");

    }


    public static void close() throws InterruptedException {
        CountDownLatch countDownLatch = new CountDownLatch(6);
        for (int i = 0; i <= 6; i++) {
            new Thread(()->{
                System.out.println(Thread.currentThread().getName()+"\t 离开教室");
                countDownLatch.countDown();
            },i+"").start();
        }
        countDownLatch.await();
        System.out.println(Thread.currentThread().getName()+"\t *****班长最后关门走人");

    }
}
