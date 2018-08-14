package com.jp.algorithm.basic.xml.test;


import com.jp.algorithm.basic.xml.entity.Book;
import com.jp.algorithm.basic.xml.sax.BookParserHandler;
import com.jp.algorithm.basic.xml.sax.SAXParserHandler;
import org.dom4j.Attribute;
import org.dom4j.DocumentException;
import org.dom4j.Element;
import org.dom4j.io.SAXReader;
import org.jdom2.JDOMException;
import org.jdom2.input.SAXBuilder;
import org.w3c.dom.Document;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;
import org.xml.sax.SAXException;
import org.xml.sax.helpers.AttributesImpl;

import javax.xml.parsers.*;
import javax.xml.transform.*;
import javax.xml.transform.dom.DOMSource;
import javax.xml.transform.sax.SAXTransformerFactory;
import javax.xml.transform.sax.TransformerHandler;
import javax.xml.transform.stream.StreamResult;
import java.io.*;
import java.util.Iterator;
import java.util.List;

/**
 * 性能测试比较类
 * Created by androidjp on 2017/7/1.
 */
public class ParseTester {

    public static void DOMParse(){
        ///DOM
        try {

            Document document = getDocumentBuilder().parse("books.xml");
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

        }catch ( SAXException | IOException e){
            e.printStackTrace();
        }
    }

    public static List<Book> SAXParse(){
        SAXParserFactory factory = SAXParserFactory.newInstance();
        ///通过factory获取SAXParser实例
        try{
            SAXParser saxParser = factory.newSAXParser();
            SAXParserHandler saxParserHandler  = new SAXParserHandler();
            BookParserHandler bookParserHandler = new BookParserHandler();
//            saxParser.parse("books.xml",saxParserHandler);
            saxParser.parse("books.xml",bookParserHandler);
            List<Book> bookList = bookParserHandler.getBookList();
            for (Book item:bookList){
                System.out.println(item);
            }
            return bookList;
        } catch (SAXException e) {
            e.printStackTrace();
        } catch (ParserConfigurationException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
        return null;
    }

    public static void JDOMParse() throws IOException, JDOMException {
        //创建一个SAXBuilder
        SAXBuilder saxBuilder = new SAXBuilder();
        //创建一个输入流并将xml文件传入
        InputStream is = new FileInputStream("books.xml");
        /// 处理中文乱码
        InputStreamReader isr = new InputStreamReader(is, "UTF-8");
        //再将这个输入流传入saxBuilder对象
//        Document document =  saxBuilder.build(is);
        /// 处理中文乱码
        org.jdom2.Document document = saxBuilder.build(isr);
        ///通过document对象获取xml文件的根节点
        org.jdom2.Element element = document.getRootElement();
        ///在获取这个根节点的所有子节点
        List<org.jdom2.Element> children = element.getChildren();
        for (org.jdom2.Element child:children){
            ///输出这个节点
            System.out.println("解析<"+child.getName()+">节点");
            //获取节点属性键值
            List<org.jdom2.Attribute> attributes = child.getAttributes();
            for (org.jdom2.Attribute attr:attributes){
                System.out.println("属性键值对--- "+ attr.getName()+":"+attr.getValue());
            }
            ///获取节点的所有子节点和内容
            List<org.jdom2.Element> elementList = child.getChildren();
            for (org.jdom2.Element item:elementList){
                System.out.println("子节点--- "+ item.getName()+":"+ item.getValue());
            }
        }

        ///对于 xml文件第一行：encoding属性，若不为'utf8'格式，则可能出现乱码的情况
        ///如：<?xml version="1.0" encoding="ISO-8859-1"?>
        ///乱码解决方式一： 修改xml文件中的`encoding` 值为 `UTF-8`
        ///解决方式二：在进行输入流创建和转换时候，可以额外给定读取数据的编码格式
        ///如： InputStreamReader isr = new InputStreamReader(is , "UTF-8");




    }

    public static void DOM4JParse() throws FileNotFoundException, UnsupportedEncodingException, DocumentException {
        ///创建SAXReader，传入目标xml文件
        SAXReader saxReader = new SAXReader();
        ///Document对象
//        Document document =  saxReader.read("books.xml");
        ///解决中文乱码
        org.dom4j.Document document = saxReader.read(new InputStreamReader(new FileInputStream("books.xml"),"utf-8"));
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


    /**
     *  以下是四种方式生成xml或rss文件
     */


    /**
     * DOM 方式 生成XML文件
     * @param filePath
     * @throws IOException
     * @throws SAXException
     * @throws TransformerConfigurationException
     */
    public static void DOMCreateXML(String filePath) throws IOException, SAXException, TransformerConfigurationException {
        DocumentBuilder db = getDocumentBuilder();
        Document document = db.newDocument();
        document.setXmlStandalone(true);///设置隐藏xml文件第一行的`standalone`属性（相当于：`standalone = yes`）
        ///创建根节点
        org.w3c.dom.Element bookstore = document.createElement("bookstore");
        ///根节点中添加子节点
        org.w3c.dom.Element book  = document.createElement("book");
        org.w3c.dom.Element name = document.createElement("name");
        name.setTextContent("小王子");
        book.setAttribute("id","1");
        book.appendChild(name);
        bookstore.appendChild(book);
        ///再将根节点添加在Document对象中
        document.appendChild(bookstore);

        ///到此为止，一棵DOM树就已经创建完毕

        ///通过Transformer 将Document 转换为 文件
        TransformerFactory transformerFactory  = TransformerFactory.newInstance();
        Transformer transformer = transformerFactory.newTransformer();
        try {
            ///设置换行
            transformer.setOutputProperty(OutputKeys.INDENT, "yes");
            transformer.transform(new DOMSource(document), new StreamResult(new File(filePath)));
        } catch (TransformerException e) {
            e.printStackTrace();
            System.err.println("转换文件出错");
        }
    }

    /**
     * SAX 方式 生成XML文件
     * @param filePath
     */
    public static void SAXCreateXML(String filePath) throws TransformerConfigurationException, IOException, SAXException {
        //利用SAX解析xml文件，得到books.xml 中的内容列表
        List<Book> books = SAXParse();

        ///创建SAXTransformerFactory
        SAXTransformerFactory stff = (SAXTransformerFactory) SAXTransformerFactory.newInstance();
        TransformerHandler handler = stff.newTransformerHandler();
        Transformer tf = handler.getTransformer();
        ///设置编码格式
        tf.setOutputProperty(OutputKeys.ENCODING,"UTF-8");
        ///设置换行符
        tf.setOutputProperty(OutputKeys.INDENT, "yes");
        ///创建目标文件
        File file = new File(filePath);
        if (!file.exists()) file.createNewFile();

        //创建Result对象
        Result result = new StreamResult(new OutputStreamWriter(new FileOutputStream(file),"UTF-8"));
        // 并让Result对象与Handler 相连
        handler.setResult(result);

        ///利用handler对象进行xml内容的编写
        ///1. 打开Document文档
        handler.startDocument();

        ///可重用的attribute设置对象
        AttributesImpl attr = new AttributesImpl();
        handler.startElement("","", "bookstore",attr);

        for (Book book:books){
            attr.clear();
            attr.addAttribute("","", "id","", book.getId()+"" );
            handler.startElement("","", "book", attr);

            attr.clear();
            handler.startElement("","", "name",attr);
            if (book.getName()!=null)
                handler.characters(book.getName().toCharArray(), 0, book.getName().length());
            handler.endElement("", "", "name");

            handler.startElement("", "", "author", attr);
            if (book.getAuthor()!=null)
            handler.characters(book.getAuthor().toCharArray(), 0,book.getName().length());
            handler.endElement("","","author");

            handler.startElement("", "", "year", attr);
            handler.characters(String.valueOf(book.getYear()).toCharArray(),0, String.valueOf(book.getYear()).length());
            handler.endElement("","", "year");

            handler.endElement("","","book");
        }

        handler.endElement("", "", "bookstore");
        /// 最后，关闭Document文档
        handler.endDocument();
    }


    /**
     * DOM 获取 DocumentBuilder对象
     * @return
     */
    private static DocumentBuilder getDocumentBuilder() {
        DocumentBuilderFactory dbf = DocumentBuilderFactory.newInstance();
        DocumentBuilder db = null;
        try{
            db = dbf.newDocumentBuilder();
        } catch (ParserConfigurationException e) {
            e.printStackTrace();
            System.err.println("getDocumentBuilder()抛出异常");
        }
        return db;
    }


}
