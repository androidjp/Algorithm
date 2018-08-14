package com.jp.algorithm.basic.xml.jdom;


//import com.sun.tools.doclint.HtmlTag;
import org.jdom2.Attribute;
import org.jdom2.Document;
import org.jdom2.Element;
import org.jdom2.JDOMException;
import org.jdom2.input.SAXBuilder;

import java.io.*;
import java.util.List;

/**
 * 需要jdom.jar 包
 *
 * Created by androidjp on 2017/7/1.
 */
public class JDOMTest {

    public  static void main(String[] args) throws IOException, JDOMException {
        //创建一个SAXBuilder
        SAXBuilder saxBuilder = new SAXBuilder();
        //创建一个输入流并将xml文件传入
        InputStream is = new FileInputStream("books.xml");
        /// 处理中文乱码
        InputStreamReader isr = new InputStreamReader(is, "UTF-8");
        //再将这个输入流传入saxBuilder对象
//        Document document =  saxBuilder.build(is);
        /// 处理中文乱码
        Document document = saxBuilder.build(isr);
        ///通过document对象获取xml文件的根节点
        Element element = document.getRootElement();
        ///在获取这个根节点的所有子节点
        List<Element> children = element.getChildren();
        for (Element child:children){
            ///输出这个节点
            System.out.println("解析<"+child.getName()+">节点");
            //获取节点属性键值
            List<Attribute> attributes = child.getAttributes();
            for (Attribute attr:attributes){
                System.out.println("属性键值对--- "+ attr.getName()+":"+attr.getValue());
            }
            ///获取节点的所有子节点和内容
            List<Element> elementList = child.getChildren();
            for (Element item:elementList){
                System.out.println("子节点--- "+ item.getName()+":"+ item.getValue());
            }
        }

        ///对于 xml文件第一行：encoding属性，若不为'utf8'格式，则可能出现乱码的情况
        ///如：<?xml version="1.0" encoding="ISO-8859-1"?>
        ///乱码解决方式一： 修改xml文件中的`encoding` 值为 `UTF-8`
        ///解决方式二：在进行输入流创建和转换时候，可以额外给定读取数据的编码格式
        ///如： InputStreamReader isr = new InputStreamReader(is , "UTF-8");
    }
}
