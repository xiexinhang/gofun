package com.example.xiezhe.shangguigu;

import java.util.concurrent.Semaphore;

/**
 * @author xiezhe
 * @date 2020/5/23 17:46
 * @mondify TODO
 * @copyright gofun
 */
public class SemaphoreDemo1 {

    public static void main(String[] args) {
        //3个车位
        Semaphore semaphore = new Semaphore(3);

        //6辆车
        for (int i = 1; i <= 6; i++) {
            new Thread(()->{
                try {
                    semaphore.acquire();
                    System.out.println(Thread.currentThread().getName()+"\t 抢到车位!!");
                    Thread.sleep(3000);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }finally {
                    semaphore.release();
                    System.out.println(Thread.currentThread().getName()+"\t 停了3秒钟释放车位!!!!");
                }
            },i+"").start();
        }
    }
}
