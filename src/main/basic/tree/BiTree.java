package main.basic.tree;

import java.util.ArrayDeque;
import java.util.Queue;
import java.util.Scanner;
import java.util.Stack;

/**
 * 二叉树
 * Created by androidjp on 16/8/17.
 */
public class BiTree {
    public String data;
    public BiTree left;
    public BiTree right;

    public static Scanner scanner = new Scanner(System.in);

    /**
     * 以 先序遍历 的顺序进行元素插入
     *
     * @return 创建好的二叉树
     */
    public static BiTree createTree() {
        String s = scanner.next();
        char ch = s.charAt(0);
        if (ch == '#') {
            return null;
        }
        BiTree root = new BiTree();
        root.data = s;
        root.left = null;
        root.right = null;

        //递归进行插入
        root.left = createTree();
        root.right = createTree();

        return root;
    }

    /**
     * 先序遍历二叉树
     *
     * @param tree
     */
    public static void preTraverse(BiTree tree) {
        if (tree == null)
            return;
        System.out.print(tree.data + " ");
        preTraverse(tree.left);
        preTraverse(tree.right);

    }

    /**
     * 中序遍历二叉树
     *
     * @param tree
     */
    public static void orderTraverse(BiTree tree) {
        if (tree == null)
            return;
        orderTraverse(tree.left);
        System.out.print(tree.data +" ");
        orderTraverse(tree.right);

    }

    /**
     * 后序遍历二叉树
     *
     * @param tree
     */
    public static void postTraverse(BiTree tree) {
        if (tree == null)
            return;
        postTraverse(tree.left);
        postTraverse(tree.right);
        System.out.print(tree.data + " ");
    }

    /**
     * 二叉树的深度优先遍历
     * 【二叉树不同于图,图需要标记元素是否已经被遍历过,因为可能存在环,而树则不用考虑环的问题,于是少了判断这一步】
     * 使用栈 遍历
     *
     * @param tree
     */
    public static void depthFirstTraverse(BiTree tree) {
        Stack<BiTree> stack = new Stack<>();
        stack.push(tree);
        while (!stack.empty()) {
            tree = stack.peek();
            System.out.print(tree.data + " ");
            stack.pop();
            //注意 元素入栈先后顺序: right -> left
            if (tree.right!= null){
                stack.push(tree.right);
            }
            if (tree.left != null) {
                stack.push(tree.left);
            }
        }
    }

    /**
     * 广度优先遍历(也称为:层次遍历)
     * 利用队列
     * @param tree
     */
    public static void breadFirstTraverse(BiTree tree){
        Queue<BiTree> queue = new ArrayDeque<>();
        queue.add(tree);
        while(!queue.isEmpty()){
            tree = queue.poll();
            System.out.print(tree.data + " ");
            if (tree.left!=null){
                queue.add(tree.left);
            }
            if (tree.right!=null){
                queue.add(tree.right);
            }
        }
    }


    public static void main(String[] args) {
        BiTree tree = new BiTree();
        ///(根节点 -> 左孩子 -> 右孩子)创建二叉树,如输入: 1 2a 3a # 4b # # 3b # # 2b # #
        tree = createTree();
        BiTree ptr = tree;

        //先序遍历
        preTraverse(ptr);
        System.out.println();

        //中序遍历
        ptr = tree;
        orderTraverse(ptr);
        System.out.println();

        //后序遍历
        ptr = tree;
        postTraverse(ptr);
        System.out.println();

        //深度优先遍历
        ptr = tree;
        depthFirstTraverse(ptr);
        System.out.println();

        //广度优先遍历
        ptr = tree;
        breadFirstTraverse(ptr);
        System.out.println();
    }
}
