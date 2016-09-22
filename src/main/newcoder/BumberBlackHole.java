package main.newcoder;

import java.util.Arrays;
import java.util.Scanner;

/**
 * 数字黑洞
 * 思路：char[] 和String、int之间的转换过程：
 * String 转 char： String.charAt(i)
 * char转int ： char -'0'
 * char[]进行排序：Arrays.sort(char[])
 * Created by androidjp on 16-8-4.
 */
public class BumberBlackHole {
    public static void main(String[] args){
        Scanner scanner = new Scanner(System.in);
        String num = scanner.next();///输入字符串
        int result = 0;
        while(result!= 6174){
            int left = 0;///大数
            int right= 0;///小数
            char[] smallnum = string2Chars(num,4);
            char[] bignum = new char[4];
            Arrays.sort(smallnum);///利用归并排序，因为char本身是可比较的，从小到大排。
            //先取大数
            for(int i=3;i>=0;i--){
                left = left*10 + smallnum[i] -'0';
                right = right*10 + smallnum[3-i] -'0';
                bignum[i] = smallnum[3-i];
            }
            if (left == right){
                System.out.println(new String(bignum)+" - "+ new String());
                return;
            }
            result= left - right;
            System.out.println(new String(bignum)+" - "+ new String(smallnum) +" = "+ result);
            num = ""+result;
        }
    }

    /**
     * 把字符串拆分成一个个char
     * @param s 给定的字符串
     * @param len 给定的长度
     * @return
     */
    private static char[] string2Chars(String s, int len){
        char[] num = new char[len];
        for(int i=0;i<num.length;i++){
            if(i >= s.length()){
                num[i] = '0';
            }else{
                num[i] = s.charAt(i);
            }
        }
        return num;
    }
}