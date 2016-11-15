package main.basic.reflect.impl;

import main.basic.reflect.Master;

/**
 * Created by androidjp on 2016/11/4.
 */
public class MasterB implements Master {
    @Override
    public void attack() {
        System.out.println(this.getClass().getName()+"要攻击了");
    }

    @Override
    public void defend() {
        System.out.println(this.getClass().getName()+"正在防守");
    }
}
