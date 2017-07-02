package main.basic.xml.sax;


import main.basic.xml.entity.Book;
import org.xml.sax.Attributes;
import org.xml.sax.SAXException;
import org.xml.sax.helpers.DefaultHandler;

import java.util.ArrayList;
import java.util.List;

/**
 * 针对 Book类的解析XML的Handler
 * Created by androidjp on 2017/7/1.
 */
public class BookParserHandler extends DefaultHandler {

    private String val;
    private Book book;
    private List<Book> bookList;

    public List<Book> getBookList() {
        return this.bookList;
    }

    @Override
    public void startDocument() throws SAXException {
        super.startDocument();
        System.out.println("---------开始解析xml文件--------");
    }


    @Override
    public void startElement(String uri, String localName, String qName, Attributes attributes) throws SAXException {
        super.startElement(uri, localName, qName, attributes);
        if (qName.equals("bookstore")) {
            this.bookList = new ArrayList<>();
        } else if (qName.equals("book")) {
            this.book = new Book();
            //解析<book>标签中的属性
            String v = null;
            for (int i = 0; i < attributes.getLength(); i++) {
                v = attributes.getValue(i);
                switch (attributes.getQName(i)) {
                    case "id":
                        if (v.trim().equals("")) break;
                        this.book.setId(Integer.valueOf(v.trim()));
                        break;

                    default:
                        break;
                }
            }
        }
    }


    @Override
    public void characters(char[] ch, int start, int length) throws SAXException {
        super.characters(ch, start, length);
        ///获取标签内容值
        String val = new String(ch, start, length);
        if (val.trim().equals("")) return;
        this.val = val;
    }

    @Override
    public void endElement(String uri, String localName, String qName) throws SAXException {
        super.endElement(uri, localName, qName);
        switch (qName) {
            case "name":
                assert this.book != null;
                this.book.setName(this.val);
                break;
            case "author":
                assert this.book != null;
                this.book.setAuthor(this.val);
                break;
            case "id":
                this.book.setId(Integer.valueOf(this.val));
                break;
            case "year":
                this.book.setYear(Integer.valueOf(this.val));
                break;
            case "book":
                this.bookList.add(this.book);
                this.book = null;
                break;
        }
    }

    @Override
    public void endDocument() throws SAXException {
        super.endDocument();
        System.out.println("---------结束解析xml文件--------");
    }
}
