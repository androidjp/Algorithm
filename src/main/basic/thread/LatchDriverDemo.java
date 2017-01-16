package main.basic.thread;

import java.util.concurrent.CountDownLatch;

/**
 * 同步器之---倒计时门栓
 * Created by androidjp on 2016/11/16.
 */
public class LatchDriverDemo {

    public static void main(String[] args) throws InterruptedException {
        CountDownLatch startSignal = new CountDownLatch(1);
        CountDownLatch doneSignal = new CountDownLatch(5);

        System.out.println("主线程创建子线程，并让子线程开启startSignal计数器，等待主线程将startSignal打开");
        for (int i=0;i<5;i++){
            new Thread(new Worker(startSignal,doneSignal),"t"+i).start();
        }

        Thread.sleep(4000);
        System.out.println("主线程"+Thread.currentThread().getName()+"开启startSignal计数器");
        startSignal.countDown();///startSignal变成0，所有线程开始工作

        System.out.println("主线程"+Thread.currentThread().getName()+"开始倒计时5个数");
        doneSignal.await();

        ////然后，子线程开始做。。。。。。。

        System.out.println("所有任务完成！");


    }
}
class Worker implements Runnable{

    private final CountDownLatch startSignal;
    private final CountDownLatch doneSignal;

    public Worker(CountDownLatch startSignal, CountDownLatch doneSignal) {
        this.startSignal = startSignal;
        this.doneSignal = doneSignal;
    }

    @Override
    public void run() {
        try {
            System.out.println(Thread.currentThread().getName()+"开始调用await()方法，等待计数器startSignal被主线程打开");
            startSignal.await();
            /*
              do sth
             */
            System.out.println(Thread.currentThread().getName()+"做事ing");


            System.out.println(Thread.currentThread().getName()+"将主线程的计数器减一");
            doneSignal.countDown();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
}