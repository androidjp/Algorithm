package main.basic.reflect;

import main.basic.reflect.impl.Factory;
import main.basic.reflect.impl.MasterA;

import java.lang.reflect.Field;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by androidjp on 2016/11/4.
 */
public class Test {

    public static void main(String[] args){
        Factory factory = new Factory();
        MasterA masterA = factory.createByClass(MasterA.class);
        masterA.attack();
        masterA.defend();

        Master masterB = factory.createByStr("main.basic.reflect.impl.MasterB");
        masterB.attack();
        masterB.defend();

        ClassUtils.getConstructorMsg(masterA);
        ClassUtils.getFieldList(masterA);
        ClassUtils.getMethodList(masterA);

        Master masterAA = factory.createByClass(MasterA.class,"小明怪兽",1000L);

        masterAA.attack();

        if (Master.class.isInstance(factory)){
            System.out.println("我是MasterA的实例对象!");
        }

        try {
            Field field = masterA.getClass().getDeclaredField("name");
            field.setAccessible(true);
            field.set(masterA,"花花");

        } catch (NoSuchFieldException e) {
            e.printStackTrace();
        } catch (IllegalAccessException e) {
            e.printStackTrace();
        }
    }




}
