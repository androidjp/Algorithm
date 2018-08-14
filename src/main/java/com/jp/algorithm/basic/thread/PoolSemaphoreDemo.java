package com.jp.algorithm.basic.thread;

import java.util.Objects;
import java.util.concurrent.Semaphore;

/**
 * 同步器 之 信号量（Semaphore）: 通常用于限制可以访问某些资源的线程数目
 * Created by androidjp on 2016/11/16.
 */
public class PoolSemaphoreDemo {
    private static final int max =5;
    private final Semaphore semaphore = new Semaphore(max,true);

    public static void main(String[] args){
        final PoolSemaphoreDemo pool = new PoolSemaphoreDemo();
        Runnable runnable = new Runnable() {
            @Override
            public void run() {
                try {
                    Object obj;
                    obj = pool.getItem();///读写数据，设置数据状态为"被占用"
                    if (obj!=null)
                    System.out.println("线程："+Thread.currentThread().getName()+"读取了数据："+obj.toString());
                    else
                        System.out.println("线程："+Thread.currentThread().getName()+"没有数据");
                    Thread.sleep(1000);
                    pool.putItem(obj);//读写数据完毕，设置数据为"空闲"状态，并容许后续的等待线程调用
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }

            }
        };
        for (int i=0;i<10;i++){
            Thread t = new Thread(runnable,"thread"+i);
            t.start();
        }
    }

    public Object getItem() throws InterruptedException {
        System.out.println(Thread.currentThread().getName()+"正在被信号量检查。。。");
        semaphore.acquire();
        System.out.println(Thread.currentThread().getName()+"被允许执行同步方法");
        return getNextAvailableItem();
    }

    public void putItem(Object x){
        if (markAsUnused(x)){
            semaphore.release();
            System.out.println("线程："+Thread.currentThread().getName()+"释放了资源："+x.toString());

        }
    }

    protected Object[] items = {"11","22","33"};
    protected boolean[] useds = new boolean[3];

    private synchronized Object getNextAvailableItem() {
        for (int i=0;i<3;i++){
            if (!useds[i]){
                useds[i] = true;
                return items[i];
            }
        }
        return null;
    }

    private synchronized boolean markAsUnused(Object item){
        for (int i=0;i<3;i++){
            if (item == items[i]){
                if (useds[i]) {///正在被使用
                    useds[i] = false;
                    return true;
                }else///没有被使用
                    return false;
            }
        }
        return false;
    }
}
