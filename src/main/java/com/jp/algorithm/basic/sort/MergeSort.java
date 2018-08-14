package com.jp.algorithm.basic.sort;

/**
 * Created by androidjp on 16-8-10.
 */
public class MergeSort {


    /**
     * 归并排序
     */
    public static void mergeSort(int[] data) {
        ///递归条件
        if (data.length >1){

            /**
             * 1. 拆分两半
             */
            int[] firstHalf = new int[data.length/2];
            System.arraycopy(data,0,firstHalf,0,firstHalf.length);
            mergeSort(firstHalf);

            int[] secondHalf = new int[data.length - data.length/2];
            System.arraycopy(data,data.length/2,secondHalf,0,secondHalf.length);
            mergeSort(secondHalf);
            /**
             * 2. 合并两个部分
             */
            int[] temp = merge(firstHalf, secondHalf);

            /**
             * 重要：copy回原来的数组
             */
            System.arraycopy(temp, 0 , data, 0 , temp.length);
        }
    }
    ///合并
    private static int[] merge(int[] a, int[] b) {
        int[] temp = new int[a.length+ b.length];
        int i = 0;
        int j = 0;
        int k = 0;

        while(i<a.length && j<b.length){
            if (a[i] <b[j]){
                temp[k++]= a[i++];
            }else{
                temp[k++] = b[j++];
            }
        }
        while(i < a.length)
            temp[k++] = a[i++];

        while(j < b.length)
            temp[k++] = b[j++];

        return temp;
    }

}
