package main.basic.sort;

/**
 * Created by androidjp on 16-8-10.
 */
public class BubbleSort {

    /**
     * 冒泡排序
     * 相邻元素比较和交换的过程
     * 1. 从len-1 到 0
     * 2. 从len-1 到 1
     * 3. 从len-1 到 2
     * …………
     */
    public static void bubbleSort(int[] data) {
        if (data== null|| data.length==0){
            throw  new RuntimeException("参数无效");
        }
        for (int i=0;i<data.length-1;i++){
            boolean isSwap = false;
            for(int j=data.length-1;j>i;j--){
                if (data[j] < data[j-1]){
                    //交换
                    int temp = data[j-1];
                    data[j-1] =data[j];
                    data[j] = temp;
                    ///设置为“需要交换”
                    isSwap = true;
                }
            }
            if (!isSwap)
                return;
        }
    }

}
