package com.example.xiezhe.juc;

/**
 * @author xiezhe
 * @date 2020/3/3 9:54
 * @mondify
 * @copyright gofun
 */
public class SingletonDemo {

    private volatile static SingletonDemo singletonDemo = null;

    public SingletonDemo() {
        System.out.println("创建实例!!!");
    }
    public static SingletonDemo getInstance(){
        if(singletonDemo==null){
            singletonDemo = new SingletonDemo();
        }
        return singletonDemo;
    }

    public static void main(String[] args) {
        //System.out.println(SingletonDemo.getInstance()==SingletonDemo.getInstance());
        //System.out.println(SingletonDemo.getInstance()==SingletonDemo.getInstance());
        //System.out.println(SingletonDemo.getInstance()==SingletonDemo.getInstance());
        for (int i = 0; i < 20; i++) {
            new Thread(()->{
                SingletonDemo.getInstance();
            }).start();
        }
    }
}
