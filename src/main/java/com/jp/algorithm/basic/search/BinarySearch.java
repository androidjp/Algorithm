package com.jp.algorithm.basic.search;

import java.util.Scanner;

/**
 * Created by androidjp on 16-8-10.
 */
public class BinarySearch {

    public static void main(String[] args){
        int[] data = {
            1,2,3,4,5,6,7,8,9,10
        };

        Scanner scan = new Scanner(System.in);
        int x  = scan.nextInt();
        int res = binarySearch(data, data.length,x);
        System.out.println(res+"");
//        Collections.sort(new ArrayList<Integer>());
//        Arrays.sort(data,0,data.length);
//        Collections.binarySearch(new ArrayList<Integer>(),1);
//        Arrays.binarySearch(data,1);
    }

    public static int binarySearch(int[] data, int len , int x){
        int low = 0;
        int high = len-1;
        while(low <=high){
            int mid = (low+high)>>1;
            if(data[mid] == x)
                return mid;
            else{
                if (data[mid]>x)
                    high = mid -1;
                else
                    low = mid + 1;
            }
        }
        return -1;
    }
}
