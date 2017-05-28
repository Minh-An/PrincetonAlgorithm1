package com.company;

import java.util.Iterator;
import java.util.NoSuchElementException;

public class ArrayStack<Item> implements Iterable<Item>
{
    private Item[] items;
    private int n = 0;

    /**
     * Returns an iterator for this stack iterating through items in LIFO order
     * @return an iterator for this stack iterating through items in LIFO order
     */
    @Override
    public Iterator<Item> iterator() {
        return new ReverseArrayIterator();
    }

    //an iterator iterating through the array in reverse (LIFO) order
    private class ReverseArrayIterator implements Iterator<Item> {

        private int i;

        public ReverseArrayIterator()
        {
            i = n-1;
        }

        @Override
        public boolean hasNext() {
            return i >= 0;
        }

        @Override
        public void remove()
        {
            throw new UnsupportedOperationException();
        }

        @Override
        public Item next() {
            if(!hasNext()) throw new NoSuchElementException();
            return items[i--];
        }
    }

    /**
     * Initializes empty stack
     */
    public ArrayStack()
    {
        items = (Item[]) new Object[2];
    }

    /**
     * Returns the number of items in the stack
     * @return the number of items in the stack
     */
    public int size()
    {
        return n;
    }

    /**
     * Is the stack empty?
     * @return true if the stack is empty, false otherwise
     */
    public boolean isEmpty()
    {
        return n == 0;
    }

    /**
     * Pushes an item to the end of the stack
     * @param item the item to add to the stack
     */
    public void push(Item item)
    {
        if (n == items.length)
        {
            resize(2 * items.length);
        }
        items[n++] = item;
    }

    /**
     * Removes(pops) and returns the last item added in the stack
     * @return the item that was just popped
     * @throws NoSuchElementException if stack is empty
     */
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

    /**
     * Returns but doesn't remove the last item added in the stack
     * @return the item last added in the stack
     * @throws java.util.NoSuchElementException if  stack is empty
     */
    public Item peek() {
        if (isEmpty()) throw new NoSuchElementException("Stack underflow");
        return items[n-1];
    }

    //resize the array holding the items in the stack
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