package basic.match;

import javax.annotation.processing.SupportedSourceVersion;
import java.util.Scanner;

/**
 * 时间复杂度：O(n+m)
 * Created by androidjp on 16-8-10.
 */
public class KMP {

    public static int[] getNext(String b){
        int len = b.length();
        int[] next= new int[len+1];
        int j=0;
        for(int i=1;i<len;i++){
            while(j>0 && b.charAt(i)!=b.charAt(j))j= next[j];
            if (b.charAt(i) == b.charAt(j)){
                j++;
                if ((i+1)<len && b.charAt(i+1) != b.charAt(j))///改进版：解决了比如：‘aaaaabcde’这样的几个字符一样的子串出现的情况
                    next[i+1] = j;
                else///如果后一个元素也前这个元素一样
                    next[i+1] = next[j];
            }
//            //未改进版
//            if(b.charAt(i) == b.charAt(j)){
//                j++;
//            }
//            next[i+1] = j;
        }

        System.out.print("next数组为：");
        for (int i =0 ;i<len;i++){
            System.out.print(next[i]+" ");
        }
        System.out.println();

        return next;
    }


    public static int kmp(String a, String b){
        int lenA = a.length();
        int lenB = b.length();
        int[] next= getNext(b);
        int j = 0;
        for(int i=0;i<lenA;i++){
           while( j>0 &&a.charAt(i) != b.charAt(j)) j = next[j];
            if(a.charAt(i) == b.charAt(j)) j++;
            if (j == lenB){///输出完全匹配时i的初始位置（因为这时i=a.length-1, 而j=b.length ，所以i要加一）
                return (i+1-j);
            }
        }
        return -1;
    }


    public static void main(String[] args){
        Scanner scan = new Scanner(System.in);

        String a = scan.next();
        String b = scan.next();

        System.out.println(kmp(a,b));

    }

}
