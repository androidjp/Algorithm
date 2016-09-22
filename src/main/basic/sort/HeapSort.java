package main.basic.sort;

/**
 * Created by androidjp on 16-8-10.
 */
public class HeapSort {

    /**
     * 堆排
     * @param data
     */
    public static void heapSort(int[] data, int len){
        if(data==null || len<=0)
            throw new RuntimeException("invalid parameters");
        /**
         * 1. 初始化堆（从最小个的堆开始整理，慢慢整理到最大个的堆）
         */
        for(int i=len/2;i>=1;i--){//从最后一个拥有子结点的结点开始往上循环调整堆
            initHeap(data, i,len);
        }

        System.out.println(data[1]);

        /**
         * 2. 一个个堆顶的值的输出，并重新调整堆的过程
         */
        for(int i=len;i>=2;i--){
            //首先，将堆顶元素与数组末尾的元素换位置，然后，数组整理堆调整长度-1
            int temp = data[i];
            data[i] = data[1];
            data[1] = temp;
            System.out.println("这次换位之后，末尾的值是："+ data[i]);
            initHeap(data, 1,i-1);
        }
    }
    //构造大顶堆的过程
    private static void initHeap(int[] data, int low, int high) {
        int i = low;
        int j = i*2;
        int temp = data[i];
        while(j<=high){
            if (j<high && data[j]<data[j+1]) j++;
            if (temp < data[j]){
                data[i] = data[j];
                i = j;
                j = 2*i;
            }else
                break;
        }
        data[i] = temp;//从后往前赋值
    }
}
