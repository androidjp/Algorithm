package main.basic.annotaion.processor;

import main.basic.annotaion.ClassFileUtils;
import main.basic.annotaion.TableInfo;

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
public class TableProcessor implements IProcessor {

    @Override
    public String process(String url) throws Exception {
        ///① 利用工具类，深度优先遍历的形式获取所有的文件
        List<File> classFiles = ClassFileUtils.getClassFiles(url);
        ///② 新建缓存类
        StringBuilder sql = new StringBuilder();
        ///③ 对所有的文件都进行类的获取，转成类列表
        for (File file : classFiles){
            Class<?> clazz = ClassFileUtils.loadClass(file);
            TableInfo tableInfo = new TableInfo();
            tableInfo = tableInfo.parse(clazz);
            if (tableInfo!=null){
                sql.append(tableInfo.toString());
            }
        }
        return sql.toString();
    }


}
