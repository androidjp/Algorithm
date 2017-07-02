package main.basic.io;

import java.io.*;

/**
 * 序列化相关
 *
 *
 * Created by androidjp on 2017/6/23.
 */
public class SerializationTest{



    public static void main(String[] args) throws Exception{
        C c = new C();
        File file = new File("/Users/androidjp/Projects/IdeaProjects/obj.dat");
        if (!file.exists())
            file.createNewFile();
        ObjectOutputStream oos = new ObjectOutputStream(
                new FileOutputStream(file)
        );
        oos.writeObject(c);
        oos.flush();
        oos.close();

        ObjectInputStream ois = new ObjectInputStream(
                new FileInputStream(file)
        );
        C cRes = (C) ois.readObject();
        System.out.println(cRes);
        ois.close();
        FileIOUtil.readByRandomAccessFile("/Users/androidjp/Projects/IdeaProjects/tt.txt");
    }
}
class A implements Serializable{
    public A(){
        System.out.println("A");
    }
}

class B extends A{
    public B(){
        System.out.println("B");
    }
}

class C extends B{
    public C(){
        System.out.println("C");
    }
}