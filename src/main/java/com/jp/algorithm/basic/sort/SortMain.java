package com.jp.algorithm.basic.sort;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.util.Scanner;

import static com.jp.algorithm.basic.sort.InsertSort.insertSort;

/**
 *
 * @author androidjp
 * @date 16-8-10
 */
public class SortMain {


    public static  void main(String[] args){
//        Scanner scan = new Scanner(System.in);
        Scanner scan;
        try {
            FileInputStream fis = new FileInputStream(new File("/home/androidjp/file.txt"));
            scan = new Scanner(fis, "utf-8");
            int len = 0;
            len = scan.nextInt();

            int[] data = new int[len];

            for (int i = 0; i < len; i++) {
                data[i] = scan.nextInt();
            }



            ///1：插入排序
            insertSort(data,len);

            ///2：希尔排序
//            shellSort(data,len);

            ///3：基数排序
//            radixSort(data, len, 10, 2);

            ///4. 快速排序
//            quickSort(data);

            //5. 归并排序
//            mergeSort(data);

            //6. 冒泡排序
//            bubbleSort(data);

            //7. 选择排序
//            selectSort(data);

            //8. 堆排序
//            int[] data2 = new int[data.length+1];
//            System.arraycopy(data,0,data2, 1,data.length);
//            heapSort(data2 ,10);
//            for (int i = 1; i < data2.length; i++) {
//                System.out.print(data2[i] + " ");
//            }
//            System.out.println();




            for (int i = 0; i < len; i++) {
                System.out.print(data[i] + " ");
            }

        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
    }


}
