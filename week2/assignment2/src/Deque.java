import java.util.Iterator;
import java.util.NoSuchElementException;

public class Deque<Item> implements Iterable<Item> {

    private int n; //size of deque
    private Node first;
    private Node last;

    /**
     * Returns an iterator over items in order from front to end
     */
    public Iterator<Item> iterator()
    {
        return new DequeIterator();
    }

    private class DequeIterator implements Iterator<Item> {
        Node current;

        private DequeIterator()
        {
            current = first;
        }

        @Override
        public boolean hasNext() {
            return current != null;
        }

        @Override
        public Item next() {
            if (!hasNext()) throw new NoSuchElementException();
            Item item = current.item;
            current = current.next;
            return item;
        }

        @Override
        public void remove() {
            throw new java.lang.UnsupportedOperationException();
        }
    }

    private class Node
    {
        Item item;
        Node next;
        Node previous;
    }

    /**
     * Initialize an empty deque
     */
    public Deque()
    {
        n = 0;
        first = null;
        last = null;
    }

    /**
     * Is the deque empty?
     * @return if the deque is empty
     */
    public boolean isEmpty()
    {
        return n == 0;
    }

    /**
     * Returns the number of items on the deque
     * @return the number of items on the deque
     */
    public int size()
    {
        return n;
    }

    /**
     * Add the item to the front of the deque
     * @param item the item to add
     */
    public void addFirst(Item item)
    {
        if (item == null) throw new NullPointerException();
        Node oldFirst = first;

        first = new Node();
        first.item = item;
        if(isEmpty())
        {
            last = first;
        }
        else
        {
            oldFirst.previous = first;
            first.next = oldFirst;
        }
        ++n;
    }
    /**
     * Add the item to the end of the deque
     * @param item the item to add
     */
    public void addLast(Item item)
    {
        if (item == null) throw new NullPointerException();
        Node oldLast = last;

        last = new Node();
        last.item = item;

        if(isEmpty())
        {
            first = last;
        }
        else
        {
            last.previous = oldLast;
            oldLast.next = last;
        }
        ++n;
    }

    /**
     * Remove and return the item from the front of the deque
     * @return the item removed
     */
    public Item removeFirst()
    {
        if(isEmpty()) throw new NoSuchElementException("Stack underflow");

        Item item = first.item;
        first = first.next;
        n--;
        if(isEmpty())
        {
            last = null;
            first = null;
        }
        else
        {
            first.previous = null;
        }
        return item;
    }

    /**
     * Remove and return the item from the end of the deque
     * @return the item removed
     */
    public Item removeLast()
    {
        if(isEmpty()) throw new NoSuchElementException("Stack underflow");

        Item item = last.item;
        last = last.previous;
        n--;
        if(isEmpty()) {
            first = null;
            last = null;
        }
        else
        {
            last.next = null;
        }
        return item;
    }

    //Unit-testing (optional)
    public static void main(String[] args)
    {
        Deque<Integer> deque = new Deque<>();

        deque.addFirst(0);
        deque.size();
        deque.size();
        deque.removeLast();
        deque.addLast(4);
        deque.addFirst(5);
        deque.removeLast();
        for(int x: deque)
        {
            System.out.println(x);
        }
    }

}