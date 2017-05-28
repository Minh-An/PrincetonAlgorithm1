package com.company;

import java.util.Iterator;
import java.util.NoSuchElementException;

public class LinkedListStack<Item> implements Iterable<Item> {

    private int n; //size of the stack
    private Node first; //first node in stack

    /**
     * Returns an iterator to iterate through elements of the stack in LIFO order
     * @return an iterator to iterate through elements of the stack in LIFO order
     */
    @Override
    public Iterator<Item> iterator() {
        return new LinkedListIterator();
    }

    //an iterator that iterates through a linked list
    private class LinkedListIterator implements Iterator<Item> {
        @Override
        public boolean hasNext() {
            return false;
        }

        @Override
        public Item next() {
            return null;
        }
    }

    public LinkedListStack() {
        n = 0;
        first = new Node();
    }

    private class Node
    {
        Item item;
        Node next;
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
     * Pushes an item to the front of the stack
     * @param item the item to add to the stack
     */
    public void push(Item item)
    {
        Node oldFirst = first;
        first = new Node();
        first.item = item;
        first.next = oldFirst;
        n++;
    }

    /**
     * Removes(pops) and returns the last item added in the stack
     * @return the item that was just popped
     * @throws NoSuchElementException if stack is empty
     */
    public Item pop()
    {
        if(isEmpty()) throw new NoSuchElementException("Stack underflow");
        Item item = first.item;
        first = first.next;
        n--;
        return item;
    }

    /**
     * Returns but doesn't remove the last item added in the stack
     * @return the item last added in the stack
     * @throws java.util.NoSuchElementException if  stack is empty
     */
    public Item peek() {
        if(isEmpty()) throw new NoSuchElementException("Stack underflow");
        return first.item;
    }

}
