package com.jp.algorithm.basic.reflect.impl;


import com.jp.algorithm.basic.reflect.BaseFactory;
import com.jp.algorithm.basic.reflect.Master;

import java.lang.reflect.Constructor;
import java.lang.reflect.InvocationTargetException;
import java.util.ArrayList;
import java.util.List;

/**
 * 负责创建类对象
 *
 * @author androidjp
 * @date 2016/11/4
 */
public class Factory extends BaseFactory<Master> {


    @Override
    public <C extends Master> C createByClass(Class<C> clazz) {
        try {
            C master = clazz.newInstance();
            return master;
        } catch (InstantiationException | IllegalAccessException e) {
            e.printStackTrace();
        }
        return null;
    }

    @Override
    public <C extends Master> C createByStr(String str) {
        try {
            Class<C> clazz = (Class<C>) Class.forName(str);
            C master = clazz.newInstance();
            return master;
        } catch (ClassNotFoundException | InstantiationException | IllegalAccessException e) {
            e.printStackTrace();
        }
        return null;
    }

    public <C extends Master> C createByClass(Class<C> clazz, Object... values){
        try {
            List<Class> paramTypeList = new ArrayList<>();
            for(Object item:values){
                paramTypeList.add(item.getClass());
            }
            Class[] classes = new Class[paramTypeList.size()];
            for (int i=0;i<paramTypeList.size();i++){
                System.out.println("类型为: "+ paramTypeList.get(i) );
                classes[i] = paramTypeList.get(i);
            }

            Constructor<C> constructor = clazz.getDeclaredConstructor(classes);
            C res = (C) constructor.newInstance(values);
            return res;
        } catch (ClassCastException | NoSuchMethodException|InstantiationException|IllegalAccessException|InvocationTargetException e) {
            e.printStackTrace();
        }
        return null;
    }

}
