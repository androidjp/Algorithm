package main.basic.encrypt;

/**
 * 异或加密
 *
 * 原理： A&B == C , C&B ==A
 * 运用：1. 两个值互换 2.简单加解密
 * Created by androidjp on 2017/1/17.
 */
public class XORUtil {

    public static void main(String[] args){
        String data = "abc123";
        System.out.print(new String(decrypt(encrypt(data.getBytes()))));
    }

    /**
     * 不固定key的加解密方式
     */

    public static byte[] encrypt(byte[] bytes) {
        if (bytes == null) {
            return null;
        }
        int len = bytes.length;
        int key = 0x12;
        for (int i = 0; i < len; i++) {
            bytes[i] = (byte) (bytes[i] ^ key);
            key = bytes[i];
        }
        return bytes;
    }

    public static byte[] decrypt(byte[] bytes) {
        if (bytes == null) {
            return null;
        }
        int len = bytes.length;
        int key = 0x12;
        for (int i = len - 1; i > 0; i--) {
            bytes[i] = (byte) (bytes[i] ^ bytes[i - 1]);
        }
        bytes[0] = (byte) (bytes[0] ^ key);
        return bytes;
    }

//    /**
//     * 固定key的加密方法
//     * @param bytes 要加密/解密的数据
//     * @return 加密后/解密后的数据
//     */
//    public byte[] encrypt(byte[] bytes) {
//        if (bytes == null) {
//            return null;
//        }
//        int len = bytes.length;
//        int key = 0x12;
//        for (int i = 0; i < len; i++) {
//            bytes[i] ^= key;
//        }
//        return bytes;
//    }


}
