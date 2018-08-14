package com.jp.algorithm.basic.sort;

/**
 * Created by androidjp on 16-8-10.
 */
public class InsertSort {
    /**
     * 直接插入排序
     * 每一个元素都 在已经排好的数组上 找一个插入点，刚好插入后，整体也是有序的。(注意，这个操作的背后是数组整体后移，屯出位置给新元素插入）
     *
     * @param data 数组
     * @param len  数组长度
     * @return 排好序的数组
     */
    public static int[] insertSort(int[] data, int len) {
        int temp;
        int j;

        if (data == null || len <= 0) {
            throw new RuntimeException("参数无效");
        }

        for (int i = 1; i < len; i++) {
            temp = data[i];
            j = i - 1;
            while (j >= 0 && data[j] >= temp) {
                data[j + 1] = data[j];///后一位元素 等于 前一位元素，一直后移
                j--;
            }
            data[j + 1] = temp;
        }
        return data;
    }

}
