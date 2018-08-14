package com.jp.algorithm.basic.thread;

import java.util.concurrent.Exchanger;

/**
 * Created by androidjp on 2016/11/16.
 */
public class ExchangerTest {

    public static void main(String[] args){
        Exchanger<String> exchanger = new Exchanger<>();
        new Thread(new Man(exchanger)).start();
        new Thread(new Woman(exchanger)).start();

    }



    static class Man implements Runnable{

        private String msg;
        private Exchanger<String> exchanger;

        public Man(Exchanger<String> exchanger) {
            this.exchanger = exchanger;
        }
        @Override
        public void run() {
            msg = "我爱你，mm";
            System.out.println("j写信给p说："+msg);

            try {
                String res = exchanger.exchange(msg);

                System.out.println("j收到了p的回信说："+res);

            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }

    static class Woman implements Runnable{

        private String msg;
        private Exchanger<String> exchanger;

        public Woman(Exchanger<String> exchanger) {
            this.exchanger = exchanger;
        }
        @Override
        public void run() {
            msg = "我爱你，小子";
            System.out.println("p写信给j说："+msg);

            try {
                System.out.println("p在思考");
                Thread.sleep(5000);
                String res = exchanger.exchange(msg);

                System.out.println("p收到了j的回信说："+res);

            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }
}
