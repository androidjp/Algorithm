package com.jp.algorithm.basic.encrypt;

import javax.crypto.Cipher;
import javax.crypto.KeyGenerator;
import javax.crypto.SecretKey;
import javax.crypto.spec.IvParameterSpec;
import javax.crypto.spec.SecretKeySpec;
import java.security.SecureRandom;
import java.util.Base64;

/**
 * 高级加密标准（英语：Advanced Encryption Standard，缩写：AES）
 * <p>
 * 1. 项目中除了登陆，支付等接口采用rsa非对称加密，之外的采用aes对称加密
 * 2. 此算法用于取代原来的DES算法
 *
 * @author androidjp
 * @date 2017/1/17
 */
public class AesUtil {

    public static void main(String[] args) {

        String data = "ABC123";

        //生成一个动态key
        String secretKey = generateKey();
        System.out.println("AES动态secretKey ---->" + secretKey);

        //AES加密
        long start = System.currentTimeMillis();
        String encryStr = encrypt(secretKey, data);
        long end = System.currentTimeMillis();
        System.out.println("AES加密耗时 cost time---->" + (end - start));
        System.out.println("AES加密后json数据 ---->" + encryStr);
        System.out.println("AES加密后json数据长度 ---->" + encryStr.length());

        //AES解密
        start = System.currentTimeMillis();
        String decryStr = decrypt(secretKey, encryStr);
        end = System.currentTimeMillis();
        System.out.println("AES解密耗时 cost time---->" + (end - start));
        System.out.println("AES解密后json数据 ---->" + decryStr);
    }

    private final static String HEX = "0123456789ABCDEF";
    //AES是加密方式 CBC是工作模式 PKCS5Padding是填充模式
    private static final String CBC_PKCS5_PADDING = "AES/CBC/PKCS5Padding";
    //AES 加密
    private static final String AES = "AES";
    //// SHA1PRNG 强随机种子算法, 要区别4.2以上版本的调用方法
    private static final String SHA1PRNG = "SHA1PRNG";


    //
    //* 生成随机数，可以当做动态的密钥 加密和解密的密钥必须一致，不然将不能解密
    //
    public static String generateKey() {
        try {
            SecureRandom localSecureRandom = SecureRandom.getInstance(SHA1PRNG);
            byte[] bytes_key = new byte[20];
            localSecureRandom.nextBytes(bytes_key);
            String str_key = toHex(bytes_key);
            return str_key;
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }

    // 对密钥进行处理
    private static byte[] getRawKey(byte[] seed) throws Exception {
        KeyGenerator kgen = KeyGenerator.getInstance(AES);
        //for android
        SecureRandom sr = null;
//        // 在4.2以上版本中，SecureRandom获取方式发生了改变
//        if (android.os.Build.VERSION.SDK_INT >= 17) {
//            sr = SecureRandom.getInstance(SHA1PRNG, "Crypto");
//        } else {
//            sr = SecureRandom.getInstance(SHA1PRNG);
//        }
        // for Java
        sr = SecureRandom.getInstance(SHA1PRNG);
        sr.setSeed(seed);
        //256 bits or 128 bits,192bits
        kgen.init(128, sr);
        //AES中128位密钥版本有10个加密循环，192比特密钥版本有12个加密循环，256比特密钥版本则有14个加密循环。
        SecretKey skey = kgen.generateKey();
        byte[] raw = skey.getEncoded();
        return raw;
    }

    //
    //* 加密
    //
    public static String encrypt(String key, String cleartext) {
        if (cleartext == null || cleartext.length() == 0) {
            return null;
        }
        try {
            byte[] result = encrypt(key, cleartext.getBytes());

            return new String(Base64.getEncoder().encode(result));
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }

    //
    //* 加密
    //
    private static byte[] encrypt(String key, byte[] clear) throws Exception {
        byte[] raw = getRawKey(key.getBytes());
        SecretKeySpec skeySpec = new SecretKeySpec(raw, AES);
        Cipher cipher = Cipher.getInstance(CBC_PKCS5_PADDING);
        cipher.init(Cipher.ENCRYPT_MODE, skeySpec, new IvParameterSpec(new byte[cipher.getBlockSize()]));
        byte[] encrypted = cipher.doFinal(clear);
        return encrypted;
    }


    //解密
    public static String decrypt(String key, String encrypted) {
        if (encrypted == null || encrypted.length() == 0) {
            return null;
        }
        try {
            byte[] enc = Base64.getDecoder().decode(encrypted);
            byte[] result = decrypt(key, enc);
            return new String(result);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }

    //
    //* 解密
    //
    private static byte[] decrypt(String key, byte[] encrypted) throws Exception {
        byte[] raw = getRawKey(key.getBytes());
        SecretKeySpec skeySpec = new SecretKeySpec(raw, AES);
        Cipher cipher = Cipher.getInstance(CBC_PKCS5_PADDING);
        cipher.init(Cipher.DECRYPT_MODE, skeySpec, new IvParameterSpec(new byte[cipher.getBlockSize()]));
        byte[] decrypted = cipher.doFinal(encrypted);
        return decrypted;
    }

    //二进制转字符
    protected static String toHex(byte[] buf) {
        if (buf == null) {
            return "";
        }
        StringBuffer result = new StringBuffer(2 * buf.length);
        for (byte aBuf : buf) {
            appendHex(result, aBuf);
        }
        return result.toString();
    }

    private static void appendHex(StringBuffer sb, byte b) {
        sb.append(HEX.charAt((b >> 4) & 0x0f)).append(HEX.charAt(b & 0x0f));
    }
}

