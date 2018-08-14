package com.jp.algorithm.StackAndQueue;

import java.util.Stack;

/**
 * 利用栈来实现汉诺塔问题
 * Created by androidjp on 16/9/5.
 */
public class HanoiProblem {

    /**
     * 递归方式解决汉诺塔问题
     */
    public static int hanoiResolve1(int num, String left , String mid, String right){
        if(num<1)
            return 0;

        return process(num, left, mid, right, left, right);
    }

    //递归方法
    private static int process(int num, String left, String mid, String right, String from, String to) {
        ///递归终止点
        if (num == 1){
            if (from.equals("mid")||to.equals("mid")){
                System.out.println(from+"移动到"+to);
                return 1;//一步
            }else{
                System.out.println(from+"移动到mid");
                System.out.println("mid移动到"+to);
                return 2;//两步
            }
        }
        if (from.equals(mid) || to.equals(mid)){//用到中间的情况下,移动需要三个步骤
            String another = (from.equals(left)||to.equals(left))?right:left;
            int part1 = process(num-1 , left,mid,right, from, another);
            int part2 = 1;
            System.out.println("Move "+ num + " from "+ from +" to "+ to);
            int part3 = process(num-1 , left,mid,right,another,to);
            return part1+part2+part3;
        }else{///如果是 左->右 或者 右->左 的情况,就需要进行5步操作
            int part1 = process(num-1 , left, mid, right , from, to);
            int part2 = 1;
            System.out.println("Move "+ num + " from "+ from +" to "+ mid);
            int part3 = process(num -1, left, mid, right, to ,from);
            int part4 = 1;
            System.out.println("Move "+ num + " from "+ mid + " to "+ to);
            int part5 = process(num - 1, left,mid , right,from ,to);
            return part1+ part2+ part3+part4+part5;
        }
    }


    public enum Action{
        No , LToM,MToR,RToM,MToL
    }

    /**
     * 非递归方法:利用栈方式解决汉诺塔移动问题
     * @param num
     * @param left
     * @param mid
     * @param right
     * @return
     */
    public static int hanoiResolve2(int num , String left, String mid, String right){
        Stack<Integer> LS = new Stack<Integer>();
        Stack<Integer> MS = new Stack<Integer>();
        Stack<Integer> RS = new Stack<Integer>();

        LS.push(Integer.MAX_VALUE);
        MS.push(Integer.MAX_VALUE);
        RS.push(Integer.MAX_VALUE);

        for(int i = num;i>0;i--){
            LS.push(i);
        }
        Action[] record = {
            Action.No
        };

        int step = 0;
        while(RS.size()!= num+1){
            step += fStackTotStack(record, Action.MToL,Action.LToM,LS, MS, left,mid);
            step += fStackTotStack(record, Action.LToM,Action.MToL,MS, LS, mid,left);
            step += fStackTotStack(record, Action.RToM,Action.MToR,MS, RS, mid,right);
            step += fStackTotStack(record, Action.MToR,Action.RToM,RS, MS, right,mid);
        }
        return step;
    }

    //一个移动压栈的先决条件是,要压入的元素要小于原来的栈顶元素
    private static int fStackTotStack(Action[] record, Action preNoAct, Action nowAct
            , Stack<Integer> fromStack, Stack<Integer> toStack
            , String from, String to) {
        if (record[0]!=preNoAct && fromStack.peek() < toStack.peek()){
            toStack.push(fromStack.pop());
            System.out.println("Move "+ toStack.peek()+" from " + from + " to "+ to);
            record[0] = nowAct;
            return 1;
        }
        return 0;
    }
}
