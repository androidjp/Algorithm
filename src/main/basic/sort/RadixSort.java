package main.basic.sort;

/**
 * 基数排序
 * Created by androidjp on 16-8-10.
 */
public class RadixSort {


    /**
     * 基数排序（桶排序），某些时候，效率会高一点
     * 效率与：进制 、位数 、数组元素个数 、元素的最高位数   有关
     */
    public static int[] radixSort(int[] data, int len, int jinzhi, int weishu) {

        if (data == null || len <= 0 || jinzhi <= 0 || weishu < 1)
            throw new RuntimeException("Invalid Parameters");

        ///这里就需要将这个数组中所有元素都变成一个Node类
        class Node {
            int num;
            Node next;
        }

        Node myHead = new Node();
        myHead.next = null;
        Node myTail = myHead;

        ///数组 转 链表
        for (int i = 0; i < len; i++) {
            Node item = new Node();
            item.num = data[i];
            item.next = null;
            myTail.next = item;
            myTail = item;
        }

        ///===========================================

        Node[] heads = new Node[jinzhi];
        Node[] tails = new Node[jinzhi];

        Node tempHead = null;

        for (int i = 0; i < weishu; i++) {
            /**
             * 1. 首先，初始化大数组
             */
            for (int j = 0; j < jinzhi; j++) {
                heads[j] = null;
                tails[j] = null;
            }
            tempHead = myHead;
            /**
             * 2. 然后，放桶
             */
            while (tempHead.next != null) {//证明有数组
                tempHead = tempHead.next;
                int k = getDigit(tempHead.num, i);
                if (heads[k] == null) {///直接数组头就开始存放元素，这样就少了(进制)个Node空间
                    heads[k] = tempHead;
                    tails[k] = heads[k];
                } else {
                    tails[k].next = tempHead;
                    tails[k] = tails[k].next;
                }
            }
            /**
             * 3. 重新 连成链表
             */
            myHead.next = null;
            myTail = myHead;
            ///注意，取出每一个小链表来构建大链表的过程中：
            for (int j = 0; j < jinzhi; j++) {
                if (heads[j] !=null){
                    if (myHead.next == null){
                        myHead.next = heads[j];
                    }else{
                        //注意这里，当大链表不为空的情况，记得修复好myTail.next去连接
                        myTail.next = heads[j];
                    }
                    myTail = tails[j];
                }
            }
            myTail.next = null;
            tempHead = null;
        }
        /**
         * 4. 最终，重新：链表 转 数组
         */
        myTail = myHead;
        int k = 0;
        while (myTail.next != null) {
            myTail = myTail.next;
            data[k++] = myTail.num;
        }
        //返回数组
        return data;
    }

    public static int getDigit(int x, int d) {
        int a[] = {1, 10, 100, 1000, 10000}; // 如果实例的最大数是百位数，那就只要到100就可以了
        return ((x / a[d]) % 10);
    }

}
