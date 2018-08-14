package com.jp.algorithm.basic.thread;

import java.util.concurrent.BrokenBarrierException;
import java.util.concurrent.CyclicBarrier;

/**
 * Created by androidjp on 2016/11/16.
 */
public class CyclicBarrierTest {

    public static void main(String[] args){
        CyclicBarrier barrier = new CyclicBarrier(2, new Runnable() {
            @Override
            public void run() {
                System.out.println("人齐了！！");
            }
        });
        new Thread(new Tour(barrier,"小明走路",1)).start();
        new Thread(new Tour(barrier,"小花开车",2)).start();
    }

    static class Tour implements Runnable{
        ///走路到：广州、深圳、珠海的时间
        private int walkTime[] = {5,5,5};
        //开车到：广州、深圳、珠海的时间
        private int driveTime[] = {3,3,3};
        private CyclicBarrier barrier;
        private String name;
        private int way;
        public Tour(CyclicBarrier barrier,String name,int way) {
            this.barrier = barrier;
            this.name = name;
            this.way = way;
        }
        @Override
        public void run() {
            try{
                Thread.sleep(1000*((way==1)?walkTime[0]:driveTime[0]));
                System.out.println(this.name+"到广州了");
                barrier.await();
                Thread.sleep(1000*((way==1)?walkTime[1]:driveTime[1]));
                System.out.println(this.name+"到深圳了");
                barrier.await();
                Thread.sleep(1000*((way==1)?walkTime[2]:driveTime[2]));
                System.out.println(this.name+"到珠海了");
                barrier.await();

            } catch (InterruptedException e) {
                e.printStackTrace();
            } catch (BrokenBarrierException e) {
                e.printStackTrace();
            }
        }
    }
}
