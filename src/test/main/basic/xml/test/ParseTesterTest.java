package test.main.basic.xml.test; 

import main.basic.xml.test.ParseTester;
import org.junit.Test;
import org.junit.Before; 
import org.junit.After; 

/** 
* ParseTester Tester. 
* 
* @author <Authors name> 
* @since <pre>七月 1, 2017</pre> 
* @version 1.0 
*/ 
public class ParseTesterTest {

@Before
public void before() throws Exception {
}

@After
public void after() throws Exception { 
} 

/** 
* 
* Method: DOMParse() 
* 
*/ 
@Test
public void testDOMParse() throws Exception { 
    System.out.println("DOM 性能测试");
    long T = System.currentTimeMillis();
    ParseTester.DOMParse();
    T = System.currentTimeMillis() - T;
    System.out.println("DOM 耗时："+ T);
}


/** 
* 
* Method: SAXParse() 
* 
*/ 
@Test
public void testSAXParse() throws Exception {
    System.out.println("SAX 性能测试");
    long T = System.currentTimeMillis();
    ParseTester.SAXParse();
    T = System.currentTimeMillis() - T;
    System.out.println("SAX 耗时："+ T);
}

/** 
* 
* Method: JDOMParse() 
* 
*/ 
@Test
public void testJDOMParse() throws Exception {
    System.out.println("JDOM 性能测试");
    long T = System.currentTimeMillis();
    ParseTester.JDOMParse();
    T = System.currentTimeMillis() - T;
    System.out.println("JDOM 耗时："+ T);
}

/** 
* 
* Method: DOM4JParse() 
* 
*/ 
@Test
public void testDOM4JParse() throws Exception {
    System.out.println("DOM4J 性能测试");
    long T = System.currentTimeMillis();
    ParseTester.DOM4JParse();
    T = System.currentTimeMillis() - T;
    System.out.println("DOM4J 耗时："+ T);
}

@Test
public void testDOMCreatXML() throws Exception{
    ParseTester.DOMCreateXML("books1.xml");
    System.out.println("DOM方式生成xml文件成功");
}

    @Test
    public void testSAXCreatXML() throws Exception{
        ParseTester.SAXCreateXML("books2.xml");
        System.out.println("SAX方式生成xml文件成功");
    }


} 
