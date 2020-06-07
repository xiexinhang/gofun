package com.example.xiezhe.shangguigu;

import java.util.concurrent.TimeUnit;
import java.util.concurrent.atomic.AtomicReference;

/**
 * 手写一个自旋锁
 * @author xiezhe
 * @date 2020/4/22 18:24
 * @mondify
 * @copyright gofun
 */
public class SpinLockDemoNew {

    AtomicReference<Thread> atomicReference = new AtomicReference<>();

    /**
     * 获取锁
     */
    public void myLock(){
        Thread thread = Thread.currentThread();
        System.out.println(thread.getName()+"获取锁!!!");
        while (!atomicReference.compareAndSet(null,thread)){

        }
        System.out.println(thread.getName()+"获取锁!!!获取成功");
    }

    /**
     * 释放锁
     */
    public void myUnLock(){
        Thread thread = Thread.currentThread();
        atomicReference.compareAndSet(thread,null);
        System.out.println(thread.getName()+"释放锁!!!");
    }
    public static void main(String[] args) {
//        原子引用线程
        SpinLockDemoNew spinLockDemo = new SpinLockDemoNew();
        new Thread(()->{
            spinLockDemo.myLock();
            try{ TimeUnit.SECONDS.sleep(5); } catch (InterruptedException e) {e.printStackTrace();}
            spinLockDemo.myUnLock();
        },"AA").start();

        try{ TimeUnit.SECONDS.sleep(1); } catch (InterruptedException e) {e.printStackTrace();}

        new Thread(()->{
            spinLockDemo.myLock();
            try{ TimeUnit.SECONDS.sleep(5); } catch (InterruptedException e) {e.printStackTrace();}
            spinLockDemo.myUnLock();
        },"BB").start();
    }
}
