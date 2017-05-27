package com.company;

public class Main {

    public static void main(String[] args) {
        ArrayStack<String> stack = new ArrayStack<String>();
        stack.push("5");
        System.out.println(stack.pop());
        stack.push("1");
        stack.push("2");
        stack.push("4");
        System.out.println(stack.pop());
        stack.push("3");
        System.out.println(stack.pop());
        System.out.println(stack.pop());
        System.out.println(stack.pop());

    }
}
