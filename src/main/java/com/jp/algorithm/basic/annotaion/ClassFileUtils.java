package com.jp.algorithm.basic.annotaion;

import sun.security.x509.X509CertInfo;

import java.io.DataInputStream;
import java.io.File;
import java.io.FileInputStream;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Created by androidjp on 2016/11/14.
 */
public class ClassFileUtils {

    private static List<File> allClassFile;

    /**
     * 获取目录下所有.class文件
     * @param url 查找目录的绝对路径
     * @return 所有Class文件（类文件）
     */
    public static List<File> getClassFiles(String url){
        allClassFile = new ArrayList<>();
        File file = new File(url);
        //如果是目录，则递归搜索该目录
        if (file.isDirectory()){
            fillClassFiles(file);
        }else{
            ///否则，如果是
            if (isClassFile(file)){
                allClassFile.add(file);
            }
        }
        return allClassFile;
    }

    /**
     * 递归函数：递归搜索目录
     * @param directory 目录
     */
    private static void fillClassFiles(File directory) {
        File[] list = directory.listFiles();
        for(File file: list){
            if (file.isDirectory()){
                fillClassFiles(file);
            }else{
                if (isClassFile(file)){
                    allClassFile.add(file);
                }
            }
        }
    }

    /**
     * 是否是class文件
     * @param file 目标文件
     * @return 是否匹配
     */
    private static boolean isClassFile(File file) {
        return getFileType(file).equals("class");
    }

    /**
     * 获取文件类型
     * @param file 目标文件（编译之后的.class文件）
     * @return 文件后缀名
     */
    private static String getFileType(File file) {
        String fileName=  file.getName();
        return fileName.substring(fileName.lastIndexOf(".")+1);
    }

    /**
     * .class文件解析出 Class对象的过程（重点）
     * 关键点：读取.class文件中的内容，找到类名，并通过Class.forName(类名)得到Class<?>对象
     * @param file .class文件
     * @return 类类型对象
     * @throws Exception 解析过程中的异常
     */
    public static Class<?> loadClass(File file) throws Exception {
        Map<Integer, Integer> offsetMap = new HashMap<Integer, Integer>();
        Map<Integer, String> classNameMap = new HashMap<Integer, String>();
        DataInputStream data = new DataInputStream(new FileInputStream(file));///读取原始数据类型
        int magic = data.readInt(); // 0xcafebabe
        int minorVersion = data.readShort();
        int majorVersion = data.readShort();
        int constant_pool_count = data.readShort();
        int[] constant_pool = new int[constant_pool_count];
        for (int i = 1; i < constant_pool_count; i++) {
            int tag = data.read();
            int tableSize;
            switch (tag) {
                case 1: // UTF
                    int length = data.readShort();
                    char[] bytes = new char[length];
                    for (int k = 0; k < bytes.length; k++)
                        bytes[k] = (char) data.read();
                    String className = new String(bytes);
                    classNameMap.put(i, className);
                    break;
                case 5: // LONG
                case 6: // DOUBLE
                    data.readLong(); // discard 8 bytes
                    i++; // Special skip necessary
                    break;
                case 7: // CLASS
                    int offset = data.readShort();
                    offsetMap.put(i, offset);
                    break;
                case 8: // STRING
                    data.readShort(); // discard 2 bytes
                    break;
                case 3: // INTEGER
                case 4: // FLOAT
                case 9: // FIELD_REF
                case 10: // METHOD_REF
                case 11: // INTERFACE_METHOD_REF
                case 12: // NAME_AND_TYPE
                    data.readInt(); // discard 4 bytes;
                    break;
                default:
                    throw new RuntimeException("Bad tag " + tag);
            }
        }
        short access_flags = data.readShort();
        int this_class = data.readShort();
        int super_class = data.readShort();
        int classNameOffset = offsetMap.get(this_class);
        String className = classNameMap.get(classNameOffset).replace("/", ".");
        Class<?> clazz = Class.forName(className);
        return clazz;
    }

}
