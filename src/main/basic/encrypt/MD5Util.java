package main.basic.encrypt;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.nio.MappedByteBuffer;
import java.nio.channels.FileChannel;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

/**
 * MD5 加密：（Message-Digest Algorithm 5）
 * 1. 单向加密算法，不可逆的加密
 * 2. 压缩性：任意长度字符串，算出MD5值的长度都固定
 * 3. 容易计算：MD5值很容易从原数据算出
 * 4. 抗修改性：原数据进行任何改动，MD5值就很不一样
 * 5. 强抗碰撞：已知原数据和MD5值，想找到一个具有相同MD5值的数据是非常困难的。
 *
 * 应用场景：
 * 1. 数字签名
 * 2. 一致性验证
 * 3. 安全访问认可
 *
 * 如何加大破译难度：
 * 1. 对生成md5串再次作为对象string进行重复生成md5
 * 2. 加盐：string + key（盐值），然后md5加密；使用明文的hascode作为盐 ；随机生成一串字符串作为盐
 *
 * Created by androidjp on 2017/1/16.
 */
public class MD5Util {

    public static void main(String[] args){
        String pwd = "123456";
        String pw2 = "12345789012345678";

        String res = md5(pw2);
        System.out.println(res);
        System.out.println(md5(pw2).equals(res));
    }

    /**
     * String 的MD5值
     */
    public static String md5(String str){
        if (str==null || str.length()==0)
            return null;
        MessageDigest md5 = null;
        try {
            md5 = MessageDigest.getInstance("MD5");
            byte[] bytes = md5.digest(str.getBytes());
            return bytesToHexString(bytes);
        } catch (NoSuchAlgorithmException e) {
            e.printStackTrace();
        }
        return null;
    }

    /**
     * String 的MD5值 , 进行times次加密，加大破解难度
     */
    public static String md5(String str, int times){
        if (str==null || str.length()==0)
            return null;
        String md5 = md5(str);
        for (int i=0;i<times-1;i++)
            md5 = md5(md5);
        return md5(md5);
    }

    /**
     * String 的MD5值 , 加盐，加大破解难度
     */
    public static String md5(String str, String slat){
        if (str==null || str.length()==0)
            return null;

        return md5(str+slat);
    }


    /**
     * File的MD5值
     */
    public static String md5(File file){
        if (!file.exists())return null;
        FileInputStream in = null;
        byte buffer[]  = new byte[8<<10];
        int len;
        try{
            MessageDigest md5 = MessageDigest.getInstance("MD5");
            in = new FileInputStream(file);
            while((len = in.read(buffer))!= -1){
                md5.update(buffer,0,len);
            }
            byte[] bytes = md5.digest();
            return bytesToHexString(bytes);

        } catch (NoSuchAlgorithmException | IOException e) {
            e.printStackTrace();
        }finally {
            if (null != in){
                try{
                    in.close();
                } catch (IOException ignored) {
                }
            }
        }
        return null;
    }

    /**
     * NIO 方式 对 File 进行读取操作
     *
     * MappedByteBuffer 替代了  byte[8<<10] buffer
     */
    public static String md5NIO(File file){
        if (!file.exists()) return null;
        FileInputStream in = null;
        try {
            in = new FileInputStream(file);
            MappedByteBuffer byteBuffer = in.getChannel().map(FileChannel.MapMode.READ_ONLY, 0, file.length());
            MessageDigest md5  = MessageDigest.getInstance("MD5");
            md5.update(byteBuffer);
            byte[] bytes = md5.digest();///转换出来的字节码
            ///准备输入
            return bytesToHexString(bytes);
        } catch (IOException | NoSuchAlgorithmException e) {
            e.printStackTrace();
        }finally {
            if (null != in){
                try{
                    in.close();
                } catch (IOException ignored) {
                }
            }
        }
        return null;
    }


    private static String bytesToHexString(byte[] bytes){
        StringBuilder sb = new StringBuilder(128);
        String temp;
        for (byte b:bytes){
            temp = Integer.toHexString(b & 0xff);
            if (temp.length() == 1)
                temp = "0" + temp;
            sb.append(temp);
        }
        return sb.toString();
    }
}
