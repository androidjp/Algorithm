package com.jp.algorithm.basic.reflect;

import java.lang.reflect.Field;

/**
 * Created by androidjp on 2016/10/28.
 */
public abstract class BaseFactory<T> {

    /**
     * 通过Class对象的无参构造函数构建对象
     * @param clazz 类类型
     * @return T对象
     */
    protected abstract <C extends T> C createByClass(Class<C> clazz);

    /**
     * 通过String字符串创建对象
     * @param str 类名的字符串表示C
     * @return T对象
     */
    protected abstract <C extends T> C  createByStr(String str);

}
