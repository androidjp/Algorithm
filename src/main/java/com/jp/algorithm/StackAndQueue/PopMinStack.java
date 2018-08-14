package com.jp.algorithm.StackAndQueue;

import java.util.Stack;

/**
 * 可以输出最小元素的栈（getMin()功能的栈）
 *
 * Created by androidjp on 16-8-8.
 */
public class PopMinStack {
    private Stack<Integer> dataStack;
    private Stack<Integer> minStack;


    public PopMinStack() {
        dataStack = new Stack<Integer>();
        minStack = new Stack<Integer>();
    }

    /**
     * 插入规则A(如果minStack的栈顶元素大于等于新元素，那么新元素才进minStack)
     * @param newNum
     */
    public void pushA(Integer newNum){
        if(this.minStack.isEmpty()){
            this.minStack.push(newNum);
        }else if (newNum <= getMin()){
            this.minStack.push(newNum);
        }
        this.dataStack.push(newNum);
    }


    /**
     * 入栈规则B（minStack和dataStack一样大，只是，如果新元素大于minStack栈顶，那么，minStack重入一个它的栈顶值一样的元素）
     * @param newNum
     */
    public void pushB(Integer newNum){
        if(this.minStack.isEmpty()){
            this.minStack.push(newNum);
        }else if (newNum <= getMin()){
            this.minStack.push(newNum);
        }else{
            this.minStack.push(getMin());
        }
        this.dataStack.push(newNum);
    }



    /**
     * 出栈规则A（直接输出栈顶元素，如果需要minStack出栈，才出栈）
     * @return
     */
    public Integer popA(){
        if (this.dataStack.isEmpty())
            throw new RuntimeException("栈空！");

        Integer value = this.dataStack.pop();
        if (getMin()==value){
            this.minStack.pop();
        }
        return value;

    }

    /**
     * 出栈规则B（简单）
     * @return
     */
    public Integer popB(){
        if (this.dataStack.isEmpty())
            throw new RuntimeException("栈空！");
        this.minStack.pop();
        return this.dataStack.pop();
    }


    public int getMin(){
        if (this.minStack.isEmpty())
            throw  new RuntimeException("栈空！");
        return this.minStack.peek();
    }


}
