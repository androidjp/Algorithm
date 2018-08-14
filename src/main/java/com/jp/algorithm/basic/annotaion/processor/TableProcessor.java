package com.jp.algorithm.basic.annotaion.processor;


import com.jp.algorithm.basic.annotaion.ClassFileUtils;
import com.jp.algorithm.basic.annotaion.TableInfo;

import java.io.File;
import java.util.List;

/**
 *
 * @author androidjp
 * @date 2016/11/14
 */
public class TableProcessor implements IProcessor {

    @Override
    public String process(String url) throws Exception {
        ///① 利用工具类，深度优先遍历的形式获取所有的文件
        List<File> classFiles = ClassFileUtils.getClassFiles(url);
        StringBuilder sql = new StringBuilder();
        ///② 对每一个文件都进行类的获取，转成类列表
        for (File file : classFiles){
            Class<?> clazz = ClassFileUtils.loadClass(file);
            ///③ 分析Class类中的注解，并生成SQL语句，存储到StringBuilder中
            TableInfo tableInfo = new TableInfo();
            tableInfo = tableInfo.parse(clazz);
            if (tableInfo!=null){
                sql.append(tableInfo.toString());
            }
        }
        return sql.toString();
    }
}
