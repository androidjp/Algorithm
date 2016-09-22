package main.StackAndQueue;

import java.util.Iterator;
import java.util.Stack;

/**
 * 利用递归和栈，将栈进行逆序
 * Created by androidjp on 16-8-8.
 */
public class LoopStackReverse {

    /**
     * 递归地获取栈底元素，并出栈
     */
    public static int getAndRemoveLastElement(Stack<Integer> stack){
        int result = stack.pop();
        if (stack.isEmpty()){///如果是栈底的元素，则返回
            return result;
        }else{///否则，刚刚拿出来的元素不是栈底元素，那么，先从递归寻找的结果中找到这个元素，并返回，然后重新将刚刚拿出的非栈底元素放回栈
            int last = getAndRemoveLastElement(stack);
            stack.push(result);
            return last;
        }
    }


    /**
     * 递归地入栈栈底元素（从新到旧）
     */
    public static void reverse(Stack<Integer> stack){
        if (stack.isEmpty())
            return;

        int i = getAndRemoveLastElement(stack);///获取栈底元素
        reverse(stack);//递归获取新栈底元素，并让新栈底元素先入栈
        stack.push(i);//然后再将原来的栈底元素入栈
        //从而实现逆序
    }



    public static void main(String[] args){
        Stack<Integer> stack = new Stack<Integer>();
        stack.push(1);
        stack.push(2);
        stack.push(3);


        Iterator iterator = stack.iterator();
        while(iterator.hasNext()){
            System.out.print(iterator.next()+" ");
        }

        /**
         * 逆序之后
         */
        reverse(stack);

        while(!stack.empty()){
            System.out.print(stack.pop()+" ");
        }
    }

}
