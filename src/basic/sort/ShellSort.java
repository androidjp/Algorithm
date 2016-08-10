package basic.sort;

/**
 * Created by androidjp on 16-8-10.
 */
public class ShellSort {

    /**
     * 希尔排序
     *
     * @param data
     * @param len
     * @return
     */
    public static int[] shellSort(int[] data, int len) {
        int d = len / 2;
        while (d > 0) {
            for (int i = d; i < len; i++) {
                int temp = data[i];//取第len/2个元素（数组后半段）拿这个元素和前一组的所有元素进行比较，看到适当的位置就插入
                int j = i - d;
                while (j >= 0 && temp < data[j]) {///每次需要腾出位置时，不是移动一个位置，而是移动d个位置
                    data[j + d] = data[j];
                    j -= d;
                }
                data[j + d] = temp;
            }
            d /= 2;
        }
        return data;
    }
}
