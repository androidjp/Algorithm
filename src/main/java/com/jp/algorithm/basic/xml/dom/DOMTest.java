package com.jp.algorithm.basic.xml.dom;

import org.w3c.dom.*;
import org.xml.sax.SAXException;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;
import java.io.IOException;

/**
 * Created by androidjp on 2017/6/25.
 */
public class DOMTest {

    public static void main(String args[]){

        ///DOM
        try {
            DocumentBuilderFactory dbf = DocumentBuilderFactory.newInstance();
            DocumentBuilder db = dbf.newDocumentBuilder();
            Document document = db.parse("books.xml");
            NodeList booklist=  document.getElementsByTagName("book");
            System.out.println(booklist.getLength()+"个<book>节点");///获取所有<book>的数目
            for(int i=0;i<booklist.getLength();i++){
                Node item = booklist.item(i);
//                ///获取一个book节点的所有属性
//                NamedNodeMap attrs = item.getAttributes();
//                ///遍历属性
//                for (int j=0;j<attrs.getLength();j++){
//                    Node attr =  attrs.item(j);
//                    System.out.println("属性名："+ attr.getNodeName()+", 属性值："+ attr.getNodeValue());
//                }
//                ///指定查看某个属性，可用Element
//                Element book = (Element) booklist.item(i);
//                String attrVal = book.getAttribute("id");
//                System.out.println(attrVal);
                ///获取某个Node的子Node集合
                NodeList childeNodes = item.getChildNodes();
                for(int k=0;k<childeNodes.getLength();k++){
                    if (childeNodes.item(k).getNodeType() == Node.ELEMENT_NODE){
                        //获取 Element类型的Node节点的节点名和值
                        ///而以下两种写法有区别：
                        /// 前者：如果是 <name><a>宫崎骏</a>千寻</name>，解析会得到节点值为null，因为读取到<a>标签内部没有Element了
                        ///////而如果是 <name>千与<a>宫崎骏</a>千寻</name>，解析则得到值为：'千与'
                        System.out.println(childeNodes.item(k).getNodeName()+"  ---> " + childeNodes.item(k).getFirstChild().getNodeValue());
                        /// 而后者：上述情况则返回  宫崎骏千寻【表示，getTextContent() 会获取Element内部所有子节点的内容，以及自己内部的内容】
                        System.out.println("获取节点值也可以通过getTextContent()得到："+childeNodes.item(k).getNodeName()+"  ---> " + childeNodes.item(k).getTextContent());

                    }
                }
            }

        }catch (ParserConfigurationException | SAXException | IOException e){
            e.printStackTrace();
        }
    }

}
