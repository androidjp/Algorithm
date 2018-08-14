package com.jp.algorithm.StackAndQueue;

import java.util.HashMap;
import java.util.Scanner;
import java.util.Stack;

/**构造数组的MaxTree
 * 构造的二叉树条件:
 * 1. 数组必须没有重复元素
 * 2. 数组中每一个值对应二叉树一个结点
 * 3. 包括MaxTree树在内的所有子树中,最大的元素都在树根
 * Created by androidjp on 16/9/11.
 */
public class MaxTree {




    public static void main(String[] args){
        Scanner scanner = new Scanner(System.in);
        int n = scanner.nextInt();
        int[] a = new int[n];
        for(int i=0;i<n;i++){
            a[i] = scanner.nextInt();
        }
        Node tree = getMaxTree(a);
        if (tree!=null){
            System.out.print(tree.value+" ");
        }
//        if (tree.left!=null){
//            System.out.print(tree.left.value+" ");
//        }
//        if (tree.right!=null){
//            System.out.print(tree.right.value+" ");
//        }
        }



    public static  Node getMaxTree(int[] a){
        Node[] nodes = new Node[a.length];
        ///1.首先,构造Node结点数组
        for(int i=0;i!=a.length;i++){
            nodes[i] = new Node(a[i]);
            System.out.print(nodes[i].value+" ");
        }


        Stack<Node> nodeStack = new Stack<Node>();
        HashMap<Node, Node> lBigMap = new HashMap<Node ,Node>();
        HashMap<Node, Node> rBigMap = new HashMap<Node ,Node>();

        //首先,找出数组中所有结点往左的第一个比它大的数
        for(int i=0;i!= nodes.length;i++){
            Node curNode = nodes[i];
            if ((!nodeStack.empty()) && nodeStack.peek().value < curNode.value){
                System.out.println(curNode.value+"比栈顶的"+ nodeStack.peek().value+"大");
                popStackPutMap(nodeStack, lBigMap);
            }
            nodeStack.push(curNode);
        }
        while(!nodeStack.empty()){
            popStackPutMap(nodeStack, lBigMap);
        }

        ///找出数组中所有结点往右的第一个比他大的数
        for(int i=nodes.length-1;i!= -1;i--){
            Node curNode = nodes[i];
            if ((!nodeStack.empty()) && nodeStack.peek().value < curNode.value){
                popStackPutMap(nodeStack, rBigMap);
            }
            nodeStack.push(curNode);
        }
        while(!nodeStack.empty()){
            popStackPutMap(nodeStack, rBigMap);
        }

        ///开始串联整棵树(从根节点开始)
        /**
         * 这个时候, lBigMap中存储了数组中每个元素左边第一个比他大的元素,rBigMap同理
         */
        Node head = null;
        for(int i=0;i<nodes.length;i++){
            Node curNode = nodes[i];
            Node left = lBigMap.get(curNode);
            Node right = rBigMap.get(curNode);
            if (left == null && right == null){//表示这个元素是最大的
                head = curNode;
            }else if (left == null){//如果这个元素的左边没有比他大的元素,就只看他的右子树
                if (right.left ==null){
                    right.left = curNode;
                }else{
                    right.right =curNode;
                }
            }else if (right == null){//元素的右边没有比他大的元素了,就只看他的左边
                if (left.left == null){
                    left.left = curNode;
                }else{
                    left.right = curNode;
                }
            }else {
                Node parent = left.value < right.value ? left:right;//找到相对较小的那个元素
                if (parent.left == null){
                    parent.left = curNode;
                }else{
                    parent.right = curNode;
                }
            }
        }

        return head;
    }

    /**
     * 将栈顶的元素存入对应的map中【其实是结点和结点之间的连线】
     * @param nodeStack
     * @param map
     */
    private static void popStackPutMap(Stack<Node> nodeStack, HashMap<Node, Node> map) {
        //元素出栈
        Node node = nodeStack.pop();

        if(nodeStack.empty()){
            map.put(node ,null);
        }else{
            map.put(node, nodeStack.peek());///将出栈元素的左侧元素作为此元素的孩子
        }
    }

}
