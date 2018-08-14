package com.jp.algorithm.basic.thread;

import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

/**
 * 与Object.wait() 和 Object.notify()、Object.notifyAll() 对类似的一套线程间协作方式：
 * concurrent包下的Condition相关方法：Condition.await() 和 Condition.signalAll()
 * Created by androidjp on 2016/11/16.
 */
public class ConditionTest {

    private Account[] accounts;
    private Lock dealLock = new ReentrantLock();
    private Condition moneyCondition = dealLock.newCondition();///资金不足的情况

    public void deal(int fromAccount,int toAccount,Long money){
        if (this.dealLock.tryLock()){
            try {
                while(accounts[fromAccount].money<money){
                    ///如果存款不足，就不能转这么多帐了
                    try {
                        moneyCondition.await();
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                }

                ///到有了存款了，那么，就可以继续执行转账
                accounts[fromAccount].money -= money;
                accounts[toAccount].money += money;

                ///可以唤醒其他等待做这件事的线程了
                moneyCondition.signalAll();
            }   finally {
                this.dealLock.unlock();
            }
        }
    }

//    public synchronized void deal(int fromAccount, int toAccount, Long money) {
//        while (accounts[fromAccount].money < money) {
//            ///如果存款不足，就不能转这么多帐了
//            try {
//                this.wait();
//            } catch (InterruptedException e) {
//                e.printStackTrace();
//            }
//        }
//        ///到有了存款了，那么，就可以继续执行转账
//        accounts[fromAccount].money -= money;
//        accounts[toAccount].money += money;
//
//        this.notifyAll();
//    }
}

class Account {
    public Long money;
}
