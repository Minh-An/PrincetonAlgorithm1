package com.company;

import java.util.Comparator;
import java.util.Random;

public class MergeSort {

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

    private static void sort(Object[] a, Object[] aux, int low, int hi, Comparator c)
    {
        if(hi <= low) return;
        int mid = low + (hi-low)/2;
        sort(a, aux, low, mid, c);
        sort(a, aux, mid+1, hi, c);
        merge(a, aux, low, mid, hi, c);
    }

    public static void sort(Object a[], Comparator c)
    {
        int n = a.length;
        Object[] aux = new Object[n];
        sort(a, aux, 0, n-1, c);
        assert isSorted(a, 0, n-1, c);
    }

    public static void main(String[] args) {
        Random rand = new Random();
        Student[] students = new Student[10];
        for(int t = 0, trials = 100; t < trials; t++) {
            for (int i = 0; i < 10; i++) {
                int grade = rand.nextInt(10);
                String name = "Student" + rand.nextInt(10);
                students[i] = new Student(name, grade);
            }
            sort(students, Student.BY_NAME);
            sort(students, Student.BY_GRADE);
        }
    }

}

class Student
{
    public static final Comparator<Student> BY_NAME = new ByName();
    public static final Comparator<Student> BY_GRADE = new ByGrade();

    private String name;
    private int grade;

    public Student(String name, int grade) {
        this.name = name;
        this.grade = grade;
    }

    public String getName()
    {
        return name;
    }

    public int getGrade()
    {
        return grade;
    }

    private static class ByName implements Comparator<Student>
    {

        @Override
        public int compare(Student x, Student y) {
            if (x == null || y == null) throw new NullPointerException();
            return x.name.compareTo(y.name);
        }
    }

    private static class ByGrade implements Comparator<Student>
    {

        @Override
        public int compare(Student x, Student y) {
            if (x == null || y == null) throw new NullPointerException();
            return x.grade - y.grade;
        }
    }

}