package com.jp.algorithm.basic.primenumber;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by androidjp on 16-8-10.
 */
public class PrimeNumberUtil {

    /**
     * 输出 小于n 的所有素数
     * @param n
     */
    public static void primeNumber(int n){
        List<Integer> resList = new ArrayList<>();

        int count = 0;
        int number = 2;//待判断的素数
        int squareRoot = 1;

        while(number <=n){
            boolean isPrime = true;
            if (squareRoot * squareRoot < number) squareRoot ++;
            for(int k=0;k<resList.size() && resList.get(k) <= squareRoot;k++){
                if (number % resList.get(k) == 0){
                    isPrime = false;
                    break;
                }
            }
            if (isPrime){
                count ++;
                resList.add(number);
                output(number ,count);
            }
            number++;
        }
    }

    private static void output(int number, int count) {
        if (count%10 == 0)
            System.out.println(number);
        else
            System.out.print(number +" ");
    }
}
