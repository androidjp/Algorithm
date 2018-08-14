package com.jp.algorithm.basic.reflect;

import sun.reflect.generics.tree.ReturnType;

import java.lang.reflect.*;

/**
 * Java 类相关操作 + 反射相关操作
 * Created by androidjp on 2016/11/4.
 */
public class ClassUtils {

    /**
     * 获取对象的构造函数
     *
     * @param obj 目的对象
     */
    public static void getConstructorMsg(Object obj) {
        Class clazz = obj.getClass();
        ///封装了构造函数的信息
        Constructor[] constructors = clazz.getDeclaredConstructors();
        for (Constructor item:constructors){
            System.out.print(item.getName()+"(");
            Class[] paramTypes = item.getParameterTypes();
            for (Class type: paramTypes){
                System.out.print(type.getName()+",");
            }
            System.out.println(")");
        }
    }


    public static Field[] getFieldList(Object obj){
        return getFieldList(obj.getClass());
    }

    /**
     * 获取所有成员变量的信息
     *
     * @param clazz
     * @return
     */
    public static Field[] getFieldList(Class clazz) {
        Field[] fields = clazz.getDeclaredFields();
        for (Field fieid : fields) {
            Type type = fieid.getType();
            System.out.println(Modifier.toString(fieid.getModifiers())+" "+type.getTypeName() + " " + fieid.getName());
        }
        return fields;///所有自己声明的成员变量信息
    }


    public static Method[] getMethodList(Object obj){
        return getMethodList(obj.getClass());
    }

    /**
     * 获取所有方法的信息
     *
     * @param clazz
     * @return
     */
    public static Method[] getMethodList(Class clazz) {
        Method[] methods = clazz.getDeclaredMethods();
        for (Method method : methods) {
            ///分析每一个方法
            getMethodMsg(method);
        }
        return methods;
    }


    /***
     * 获取Method的返回值类型和参数类型等信息
     * @param method
     */
    public static void getMethodMsg(Method method) {
        StringBuilder sb = new StringBuilder();

        ///获取访问权限
        sb.append(Modifier.toString(method.getModifiers())+" ");

        ///返回值类型
        Class<?> returnType = method.getReturnType();
        ////参数类型
        Class[] paramsTypeList = method.getParameterTypes();

        sb.append(returnType.getName()).append(" ").append(method.getName()).append("(");

        for (Class paramsType : paramsTypeList) {
            sb.append(paramsType.getName()).append(",");
        }
        sb.append(")");

        ///删除多余的","号
        if (sb.lastIndexOf(",") == sb.length() - 1) {
            sb.delete(sb.length() - 2, sb.length() - 1);
        }

        System.out.println(sb.toString());
    }


    ///--------------------------------------------------------------
    /// 生成类
    ///--------------------------------------------------------------


}
