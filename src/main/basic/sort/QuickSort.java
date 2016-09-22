package main.basic.sort;

import java.util.Random;

/**
 * Created by androidjp on 16-8-10.
 */
public class QuickSort {
    /**
     * 快速排序
     */
    public static void  quickSort(int[] data) {
        quickSort(data, 0, data.length-1);
    }

    private static void quickSort(int[] data, int first,  int last) {
        if(first < last){//只要有两个以上的元素（递归终止条件）
            int privotIndex = partition(data, first, last);
            quickSort(data, first,privotIndex-1);
            quickSort(data, privotIndex+1, last);
        }
    }

    private static int partition(int[] data, int first, int last) {
        int pivot = data[first];///取第一个元素为基准
//        int pivot = randomInRange(first,last);  ///或者随机取一个元素
//        然后与第一个元素交换
//        int t = data[pivot];
//        data[pivot] = data[first];
//        data[first] = t;

        int low = first +1;
        int high = last;

        while(low < high){
            /**
             * 1. 找左侧比基准大的值
             */
            while(low <= high && data[low]<= pivot){
                low++;
            }
            /**
             * 2. 找右侧比基准小的值
             */
            while (low <= high && data[high]>pivot){
                high--;
            }
            /**
             * 3. 交换
             */
            if (low < high){
                int temp = data[low];
                data[low] = data[high];
                data[high] = temp;
            }
        }
        /***
         * 所有交换完毕后，需要将这个基准点插入到一个适当的位置
         */
        while(high > first && data[high]>=pivot){
            high--;
        }
        ///找到了交换点
        if (pivot > data[high]){
            data[first] = data[high];
            data[high] = pivot;
            return high;
        }else{///不用交换，表示这一次交换后，数组基本就是有序了
            return first;
        }
    }

    /**
     * 随机取值
     */
    private static int randomInRange(int start, int end) {
        return new Random().nextInt(end-start+1)+start;
    }


}
