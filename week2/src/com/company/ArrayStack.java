package com.company;

import java.util.NoSuchElementException;

public class ArrayStack<Item> {

    private Item[] items;
    private int n = 0;

    public ArrayStack()
    {
        items = (Item[]) new Object[1];
    }

    public int size()
    {
        return n;
    }

    public boolean isEmpty()
    {
        return n == 0;
    }

    public void push(Item item)
    {
        if (n == items.length)
        {
            resize(2 * items.length);
        }
        items[n++] = item;
    }

    public Item pop()
    {
        if(isEmpty())
        {
            throw new NoSuchElementException("Stack underflow");
        }

        Item item = items[--n];
        items[n] = null;
        if (n > 0 && n == items.length / 4)
        {
            resize(items.length/2);
        }
        return item;
    }

    private void resize(int capacity)
    {
        assert capacity >= n;

        Item[] copy = (Item[]) new Object[capacity];
        for (int i = 0; i < n; i++)
        {
            copy[i] = items[i];
        }
        items = copy;
    }

}
