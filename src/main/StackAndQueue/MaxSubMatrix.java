package main.StackAndQueue;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;

/**
 * 最大子矩阵的大小
 *
 * 给定一个整形矩阵map,其中的值只有0和1两种。
 * 求由所有为1的格子组成的子矩阵中,最大的那一个子矩阵。
 *
 * 思路:
 * 启用一个height[m]数组,用于保存每一行作为底,这一行上m列的连续'1'的个数。
 * 并将height[]看出一个矩形图,中每一个数字,代表相应高度的矩形。实际上,求的是这个矩形图中的面积最大矩形。
 *
 * Created by androidjp on 16/9/11.
 */
public class MaxSubMatrix {

    /**
     * 获取最大子矩阵
     * @param map 总矩阵
     * @return 最大子矩阵的'1'的个数
     */
    public static int getMaxSubMatrix(int[][] map){

        int width = map[0].length;

        int[] height = new int[width];
        ///判断柱状图每一个柱子,判断这根柱子左边和右边是否可以扩展(条件:看左边和右边的柱子是否不低于它本身)
        return 0;
    }
}
