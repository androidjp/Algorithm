package main.basic.encrypt;

import java.io.*;
import java.util.Base64;

/**
 * 各种数据加密算法，最终都会对加密后的二进制数据进行Base64编码
 * <p>
 * Base64 加密：不是加密算法，而是编码算法
 * 1. Base64是网络最常见的用于传输8bit字节代码的编码方式之一
 * 2. Base64算法：只是对数据内容进行编码，使之容易传输
 * 3. 完全可逆
 * 4. Base64编码本质上是一种将二进制数据转成文本数据的方案。
 * 5. 对于非二进制数据，是先将其转换成二进制形式，然后每连续6比特（2的6次方=64）计算其十进制值，根据该值在A--Z,a--z,0--9,+,/ 这64个字符中找到对应的字符，最终得到一个文本字符串。
 * <p>
 * 基本规则：
 * 1. 标准base64只有64个字符+用作后缀等号
 * 2. Base64 是把3byte变成4byte可打印字符，所以Base64编码后的串一定%4==0(注意：不算后缀等号)
 * 3. "="号一定作后缀，有0~2个
 * <p>
 * Base64 编码的用处：降低传输过程中出现的错误
 * 在计算机中任何数据都是按ascii码存储的，
 * 而ascii码的128～255之间的值是不可见字符。
 * 而在网络上交换数据时，比如说从A地传到B地，
 * 往往要经过多个路由设备，
 * 由于不同的设备对字符的处理方式有一些不同，
 * 这样那些不可见字符就有可能被处理错误，
 * 这是不利于传输的。
 * 所以就先把数据先做一个Base64编码，统统变成可见字符
 * ，这样出错的可能性就大降低了。
 * Created by androidjp on 2017/1/16.
 */
public class base64Util {

    public static void main(String[] args){
        String pwd = "123456";

        String res = base64(pwd);
        System.out.println(res);
        System.out.println(new String(base64Decode(res)));
    }

    public static String base64(String str) {
        return Base64.getEncoder().encodeToString(str.getBytes());
    }

    public static byte[] base64Decode(String code) {
        return Base64.getDecoder().decode(code);
    }

    public static String base64File(String filePath) {

        if (filePath == null || filePath.length() == 0) return null;
        File file = new File(filePath);
        if (!file.exists())
            return null;
        InputStream is = null;
        try {
            is = new FileInputStream(file);
            byte[] buffer = new byte[(int) file.length()];
            is.read(buffer);
            is.close();
            return Base64.getEncoder().encodeToString(buffer);
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
        return null;
    }

    public static boolean base64FileDecode(String filePath, String code){
        if (filePath == null || filePath.length() == 0)
            return false;
        File file = new File(filePath);
        OutputStream os = null;
        try {
            byte[] decodeBytes = Base64.getDecoder().decode(code);
            if (!file.exists())
                file.createNewFile();
            os = new FileOutputStream(file);
            os.write(decodeBytes);
            os.close();
            return true;
        } catch (IOException e) {
            e.printStackTrace();

        } finally {
            if (os!=null)
                try {
                    os.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            return false;
        }
    }

}
