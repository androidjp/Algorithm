package com.jp.algorithm.basic.xml.sax;

import com.jp.algorithm.basic.xml.entity.Book;
import org.xml.sax.SAXException;

import javax.xml.parsers.ParserConfigurationException;
import javax.xml.parsers.SAXParser;
import javax.xml.parsers.SAXParserFactory;
import java.io.IOException;
import java.util.List;

/**
 * Created by androidjp on 2017/6/26.
 */
public class SAXTest {

    public static void main(String[] args){
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
        } catch (SAXException e) {
            e.printStackTrace();
        } catch (ParserConfigurationException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
