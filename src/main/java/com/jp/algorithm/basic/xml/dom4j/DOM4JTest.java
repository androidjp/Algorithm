package com.jp.algorithm.basic.xml.dom4j;

import org.dom4j.Attribute;
import org.dom4j.Document;
import org.dom4j.DocumentException;
import org.dom4j.Element;
import org.dom4j.io.SAXReader;

import java.io.*;
import java.util.Iterator;
import java.util.List;

/**
 * Created by androidjp on 2017/7/1.
 */
public class DOM4JTest {

    public static void main(String[] args) throws DocumentException, FileNotFoundException, UnsupportedEncodingException {
        ///创建SAXReader，传入目标xml文件
        SAXReader saxReader = new SAXReader();
        ///Document对象
//        Document document =  saxReader.read("books.xml");
        ///解决中文乱码
        Document document = saxReader.read(new InputStreamReader(new FileInputStream("books.xml"),"utf-8"));
        ///根Element
        Element rootElement = document.getRootElement();

        Iterator<Element> iterator = rootElement.elementIterator();
        while(iterator.hasNext()){
            Element book  = iterator.next();
            System.out.println("---------------------开始读一个"+book.getName()+"----------------------");
            ///获取所有属性值
            List<Attribute> attrList = book.attributes();
            for (Attribute attr:attrList){
                System.out.println("属性："+ attr.getName()+", 值："+attr.getValue());
            }
            ///获取所有子节点内容
            Iterator<Element> it = book.elementIterator();
            while(it.hasNext()){
                Element element = it.next();
                System.out.println("子节点："+element.getName()+", 内容："+ element.getStringValue());
            }
        }

    }
}
