package com.jp.algorithm.string;

import java.util.Scanner;

/**
 * 句子单词反转
 * Created by androidjp on 16/9/1.
 */
public class StringReverse {

    public static void main(String[] args) {

        Scanner scan = new Scanner(System.in);
        String s = scan.nextLine();


        char[] chs = s.toCharArray();

        rotateWord(chs);


    }

    /**
     * 调换字符串(①字符串整体逆序 ②字符串每个单词【也就是子串】进行逆序)
     * @param chs 字符串
     */
    private static void rotateWord(char[] chs) {
        if (chs == null || chs.length == 0)
            return;

        //① 字符串整体逆序
        reverse(chs, 0, chs.length - 1);
        int l = -1;
        int r = -1;

        //② 判断出每一个单词,并对其逆序
        for (int i = 0; i < chs.length; i++) {
            if (chs[i] != ' ') {
                l = ( i == 0 || chs[i - 1] == ' ' )? i : l;
                r = (i == chs.length - 1 || chs[i + 1] == ' ' )? i : r;
            }
            if (l != -1 && r != -1) {
                reverse(chs, l, r);
                l = -1;
                r = -1;
            }
        }


        System.out.print(String.valueOf(chs));//会省略字符串终止符号
        System.out.print(chs.toString());//会把字符串终止符号也转换成字符串并输出
    }


    private static void reverse(char[] chs, int l, int r) {
        char temp = 0;
        while (l < r) {
            temp = chs[l];
            chs[l] = chs[r];
            chs[r] = temp;
            l++;
            r--;
        }
    }
}
