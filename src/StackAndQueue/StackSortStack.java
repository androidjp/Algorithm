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
            while(!help.empty()&& help.peek()>val){//只要辅助栈中有元素,并且这个元素的值大于
                stack.push(help.pop());
            }
            //stack:5 3 6 4 2
            //help:
            /**
             * 2
             *
             * 2<4
             * so: 2 4
             *
             * 2,4<6
             * so: 2 4 6[stack变成: 5 3]
             *
             * 2 (4,6)>3
             * so: 2 3 [stack变成: 5 6 4]
             *
             * 2,3<4
             * so: 2 3 4 [stack变成: 5 6]
             *
             * 2,3,4<6
             * so: 2 3 4 6 [stack变成: 5]
             *
             * 2 3 4 (6)>5
             * so: 2 3 4 5 [stack变成: 6]
             *
             * 2,3,4,5<6
             * so: 2 3 4 5 6 [stack为空]
             *
             * 结束
             *
             * 最后,因为此时help栈顶为6,要调整为从小到大的输出,那么 需要再进行一次栈之间的"倾倒"过程
             *
             */
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
