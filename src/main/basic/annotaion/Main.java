package main.basic.annotaion;

import main.basic.annotaion.processor.TableProcessor;

import java.io.File;
import java.io.FileFilter;
import java.io.IOException;
import java.net.URL;
import java.net.URLDecoder;
import java.util.ArrayList;
import java.util.Enumeration;
import java.util.List;

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
