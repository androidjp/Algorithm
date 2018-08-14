package com.jp.algorithm.basic.annotaion;


import com.jp.algorithm.basic.annotaion.processor.TableProcessor;

/**
 * Created by androidjp on 2016/11/14.
 */
public class Main {

    public static void main(String[] args) throws Exception {
        Person person = new Person();
        ///注解处理器（内部use了 TableInfo 和 ColumnInfo），将类转成SQL语句
        TableProcessor processor = new TableProcessor();
        String sql = processor.process(System.getProperty("user.dir"));
        System.out.println(sql);
    }
}
