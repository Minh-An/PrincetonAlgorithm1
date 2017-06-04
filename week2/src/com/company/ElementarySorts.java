package com.company;

import java.awt.event.ComponentAdapter;
import java.lang.reflect.Array;
import java.util.Arrays;

public class ElementarySorts {

    public static void insertionSort(Comparable[] a)
    {
        for (int i = 1, n = a.length; i < n; i++)
        {
            for(int j = i; j > 0; j--)
            {
                if(less(a[j], a[j-1]))
                {
                    exch(a, j, j-1);
                }
                else break;
            }
        }
    }

    public static void shellSort(Comparable[] a)
    {
        int h = 1;
        while(h < a.length/3) h = 3*h+1;
        while(h > 0) {
            for (int i = h, n = a.length; i < n; i++) {
                for (int j = i; j >= h; j -= h) {
                    if (less(a[j], a[j - h])) {
                        exch(a, j, j - h);
                    } else break;
                }
            }
            h /= 3;
        }
    }

    public static boolean less(Comparable x1, Comparable x2)
    {
        return x1.compareTo(x2) < 0;
    }

    public static void exch(Comparable[] a, int i, int j)
    {
        Comparable copy = a[i];
        a[i] = a[j];
        a[j] = copy;
    }

    public static void main(String[] args) {
        Comparable[] a = {2,2,1};
        shellSort(a);
        System.out.println(Arrays.asList(a));
    }

}
