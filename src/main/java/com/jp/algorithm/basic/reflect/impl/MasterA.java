package com.jp.algorithm.basic.reflect.impl;


import com.jp.algorithm.basic.reflect.Master;

/**
 * Created by androidjp on 2016/11/4.
 */
public class MasterA implements Master {

    private String name;
    private long blood;

    MasterA(){

    }

    public MasterA(String name, Long blood) {
        this.name = name;
        this.blood = blood;
    }

    @Override
    public void attack() {
        System.out.println(this.getClass().getName()+"要攻击了");
        sleep(-1,1);
    }

    @Override
    public void defend() {
        System.out.println(this.getClass().getName()+"正在防守");
    }

    private void sleep(long start, long end){

    }

    protected void go(){

    }

    Object sorry(){
        return new Object();
    }


    public static void ttt(){

    }
}
