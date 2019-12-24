package com.raines.raineslearn.dataStructure;

public class Stack<T> {

    private Object[] data = null;

    private int size = 0;

    private int top = -1;

    public static void main(String[] args) {
        int a = 0;
        int b = 1;
        int c = 3;
        Stack<Integer> stack = new Stack<>();
        stack.push(a);
        stack.push(b);
        stack.push(c);
        System.out.println(stack.pop());
        System.out.println(stack.pop());
        System.out.println(stack.pop());
        System.out.println(stack.pop());
    }

    public Stack(){
        this(10);
    }

    public Stack(int size){
        this.size = size;
        data = new Object[size];
    }

    public void push(T o){
        if (top == size-1){
            throw new  RuntimeException("大于容量");
        }
        data[++top] = o;
    }

    public T pop(){
        if (top == -1){
            throw new RuntimeException("空栈");
        }
        return (T) data[top--];
    }

    public int length(){
        return top+1;
    }



}