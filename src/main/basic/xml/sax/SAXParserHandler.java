package main.basic.xml.sax;

import org.xml.sax.Attributes;
import org.xml.sax.SAXException;
import org.xml.sax.helpers.DefaultHandler;

/**
 * Created by androidjp on 2017/6/26.
 */
public class SAXParserHandler extends DefaultHandler{
    /**
     * 遍历XML文件的开始标签
     */
    @Override
    public void startElement(String uri, String localName, String qName, Attributes attributes) throws SAXException {
        super.startElement(uri, localName, qName, attributes);

        if (qName.equals("book")){
            ///已知book元素下有属性'id',则可以：
//            String val = attributes.getValue("id");
//            System.out.println("book的属性值："+ val);

            ///不知有什么属性：直接遍历属性值
            for (int i=0;i<attributes.getLength();i++){
                System.out.println(qName+"元素的第"+i+"个属性键值：["+attributes.getQName(i)+","+attributes.getValue(i)+"]");
            }
        }
        else{
            System.out.println("节点：<"+qName+">");
        }
    }

    /**
     * 遍历节点内容值
     */
    @Override
    public void characters(char[] ch, int start, int length) throws SAXException {
        super.characters(ch, start, length);
        String value = new String(ch, start,length);
        if (value.trim().equals(""))
            return;
        System.out.println(value);
    }

    /**
     * 遍历XML文件的结束标签
     */
    @Override
    public void endElement(String uri, String localName, String qName) throws SAXException {
        super.endElement(uri, localName, qName);
    }

    ///标识解析开始
    @Override
    public void startDocument() throws SAXException {
        super.startDocument();
        System.out.println("SAX --- startDocument()");

    }

    ///标识解析结束
    @Override
    public void endDocument() throws SAXException {
        super.endDocument();
        System.out.println("SAX --- endDocument()");
    }
}
