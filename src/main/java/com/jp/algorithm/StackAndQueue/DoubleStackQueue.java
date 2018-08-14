package com.jp.algorithm.StackAndQueue;

import java.util.Stack;

/**
 * 使用两个栈 组成的 队列 ，并支持队列基本操作（add，poll，peek）
 * <p>
 * 分析：必须满足两点：
 * 1. 如果stackPush要给stackPop压数据，那么，需要一下子全部压入stackPop
 * 2. 如果stackPop不为空，stackPush不允许压元素给stackPop
 * <p>
 * Created by androidjp on 16-8-8.
 */
public class DoubleStackQueue {

    private Stack<Integer> stackPush;
    private Stack<Integer> stackPop;

    public DoubleStackQueue() {
        stackPush = new Stack<Integer>();
        stackPop = new Stack<Integer>();
    }

    /**
     * 入队
     */
    public void add(Integer value) {
        stackPush.push(value);
    }


    /**
     * 出队（此处需要满足上面所说的两种情况）
     */
    public Integer poll() {
        if (stackPop.empty() && stackPush.empty())
            throw new RuntimeException("栈空");
        else if (stackPop.empty()) {///1. 只有当pop栈为空，push栈才可以倒过来，并且是全倒
            while (!stackPush.empty()) {
                stackPop.push(stackPush.pop());
            }
        }
        return stackPop.pop();
    }

    public Integer peek() {
        if (stackPop.empty() && stackPush.empty())
            throw new RuntimeException("栈空");
        else if (stackPop.empty()) {///1. 只有当pop栈为空，push栈才可以倒过来，并且是全倒
            while (!stackPush.empty()) {
                stackPop.push(stackPush.pop());
            }
        }
        return stackPop.peek();
    }


    public static void main(String[] args){

    }


}
