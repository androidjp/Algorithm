package com.jp.algorithm.basic.gcd;

import java.util.Scanner;

/**
 * 最大公约数
 * Created by androidjp on 16-8-10.
 */
public class GCD {

    public static int gcd(int m, int n) {
        if (m < n) {
            int temp = m;
            m = n;
            n = temp;
        }
        if (n==0){
            return m;
        }
        else{
            return gcd(n,m%n);
        }
    }


    public static void main(String[] args){
        Scanner scan = new Scanner(System.in);
        int n = scan.nextInt();
        int m  =scan.nextInt();
        System.out.println(gcd(n,m));
    }
}
