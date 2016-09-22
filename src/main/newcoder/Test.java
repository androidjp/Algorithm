package main.newcoder;

/**
 * 测试 各类代码的执行先后顺序
 * Created by androidjp on 16/9/9.
 */
public class Test {

    //静态成员
    private static int A;


    //成员块
    {
        System.out.println("成员块1");
    }

    //静态成员块
    static{
        System.out.println("静态代码块");
        A = 2;
    }

    //实体类,测试"饿汉"模式下,创建类的顺序
    private Point point = new Point();


    /**
     * 构造方法
     */
    public Test(){
        System.out.println("Test的构造方法");

    }

    {
        System.out.println("成员块2");
    }

    public static class Point{

        public Point(){
            System.out.println("Point的构造方法");
        }
    }

    public static void main(String[] args){
        Test test = new Test();
    }
}
