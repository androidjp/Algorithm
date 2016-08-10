package StackAndQueue;

import java.util.Scanner;
import java.util.Stack;

/**
 * 用栈来排序栈
 * （用一个辅助栈）
 * 原理：
 * 首先，对于源栈每一个pop出来的元素，与辅助栈的栈顶进行对比，
 * Created by androidjp on 16-8-9.
 */
public class StackSortStack {

    public static void stackSortStack(Stack<Integer> stack){
        Stack<Integer> help = new Stack<>();

        while(!stack.empty()){
            int val = stack.pop();
            ///默认最后的栈的出栈顺序是：从小到大
            while(!help.empty()&& help.peek()>val){
                stack.push(help.pop());
            }
            help.push(val);
        }
        while(!help.empty()){
            stack.push(help.pop());
        }
    }

    public static void main(String[] args){

        Stack<Integer> stack = new Stack<>();
        Scanner scanner = new Scanner(System.in);
        int val;
        while((val = scanner.nextInt())!=-1){
            stack.push(val);
        }

        stackSortStack(stack);

        while(!stack.empty()){
            System.out.print(stack.pop()+" ");
        }

    }
}
