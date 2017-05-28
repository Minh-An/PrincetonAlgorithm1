package com.company;

public class Main {

    public static void main(String[] args) {
        System.out.println(gcd(3, 6));
    }

    //Stack-based implementation of finding the greatest common divisor
    public static int gcd(int p, int q)
    {
        ArrayStack<Integer> stack = new ArrayStack<>();
        stack.push(p);
        stack.push(q);
        while (true)
        {
            q = stack.pop();
            p = stack.pop();
            if(q == 0) {
                return p;
            }
            stack.push(q);
            stack.push(p%q);
        }
    }

    //Recursive implementation of finding the greatest common divisor
    public static int gcd2(int p, int q)
    {
        if (q == 0)
        {
            return p;
        }
        else
        {
            return gcd2(q, p%q);
        }
    }

}
