package com.company;

import java.util.Comparator;

public class BottomUpMergeSort
{
    private static boolean less(Object x, Object y, Comparator c)
    {
        return c.compare(x, y) < 0;
    }

    private static boolean isSorted(Object[] a, int low, int hi, Comparator c)
    {
        for(int i = low+1; i <= hi ; i++)
        {
            if( less(a[i], a[i-1], c) )
            {
                return false;
            }
        }
        return true;
    }

    private static void merge(Object[] a, Object[] aux, int low, int mid, int hi, Comparator c)
    {
        assert isSorted(a, low, mid, c);
        assert isSorted(a, mid+1, hi, c);

        for(int i = low; i <= hi; i++)
        {
            aux[i] = a[i];
        }

        int i = low; int j = mid+1;
        for(int k = low; k <= hi; k++)
        {
            if(i > mid) a[k] = aux[j++];
            else if(j > hi) a[k] = aux[i++];
            else if(less(aux[j], aux[i], c)) a[k] = aux[j++];
            else a[k] = aux[i++];
        }

        assert isSorted(a, low, hi, c);

    }

    public static void sort(Object[] a, Comparator c)
    {

    }
}
