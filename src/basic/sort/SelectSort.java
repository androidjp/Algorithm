package basic.sort;

/**
 * Created by androidjp on 16-8-10.
 */
public class SelectSort {

    /**
     * 选择排序
     */
    public static void selectSort(int[] data){
        if (data == null || data.length <= 0)
            throw new RuntimeException("Invalid Paramemers");
        ////每次从前到后选择最小的元素，把它交换上来
        for(int i=0;i<data.length-1;i++){
            int minIndex  = i;
            for(int j = i+1;j<data.length;j++){
                if (data[j] < data[minIndex]){
                    minIndex = j;
                }
            }
            if (minIndex != i) {
                //交换
                int temp = data[minIndex];
                data[minIndex] =data[i];
                data[i] = temp;
            }
        }
    }
}
