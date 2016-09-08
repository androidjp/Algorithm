package StackAndQueue;

import java.util.LinkedList;
import java.util.Scanner;

/**
 * 窗口最大值数组
 * 时间复杂度O(N), 使用双端队列去辅助记录数组下标
 * Created by androidjp on 16/9/5.
 */
public class MaxWindow {

    public static int[] getMaxWindow(int[] arr , int w){
        if (arr == null ||  w <1 || arr.length < w)
            return null;
        LinkedList<Integer> qmax = new LinkedList<Integer>();
        int[] res = new int[arr.length - w +1];
        int index = 0;
        for(int i =0;i<arr.length;i++){
            while(!qmax.isEmpty() && arr[qmax.peekLast()]<=arr[i]){
                ///如果qmax不为空,并且,qmax队尾元素对应的数组元素 不大于 遍历到的这个数组元素
                qmax.pollLast();//那么 , 该下标出队
            }
            qmax.addLast(i);
            if (qmax.peekFirst() == i - w){//如果对头下标已经不在窗口范围内
                qmax.pollFirst();//删除对头元素
            }
            if ( i>=w-1){//当遍历完窗口中所有元素时,此时得到的对头元素对应的数组元素,即为该窗口最大值
                res[index++] = arr[qmax.peekFirst()];
            }
        }
        return res;
    }

}
