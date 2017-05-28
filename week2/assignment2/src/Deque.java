import java.util.Iterator;
import java.util.NoSuchElementException;

public class Deque<Item> implements Iterable<Item> {

    int n; //size of deque
    Node first;
    Node last;

    /**
     * Returns an iterator over items in order from front to end
     */
    public Iterator<Item> iterator()
    {
        return new DequeIterator();
    }

    private class DequeIterator implements Iterator<Item> {
        Node current = first;

        @Override
        public boolean hasNext() {
            return current != null;
        }

        @Override
        public Item next() {
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
        Node oldFirst = first;
        first = new Node();
        first.item = item;
        first.next = oldFirst;
        if(isEmpty()) last = first;
        n++;
    }
    /**
     * Add the item to the end of the deque
     * @param item the item to add
     */
    public void addLast(Item item)
    {
        Node oldLast = last;
        last = new Node();
        last.item = item;
        last.previous = oldLast;
        if(isEmpty())
        {
            first = last;
        }
        else
        {
            oldLast.next = last;
        }
        n++;
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
        if(isEmpty()) last = null;
        n--;
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
        if(isEmpty()) first = null;
        n--;
        return item;
    }

    //Unit-testing (optional)
    public static void main(String[] args)
    {
        Deque<Integer> deque = new Deque<>();
        deque.addFirst(5);
        deque.addFirst(20);
        deque.addLast(15);
        deque.removeFirst();
        deque.removeLast();
        deque.addFirst(4);
        deque.addFirst(3);
        deque.addFirst(2);
        deque.addFirst(1);
        deque.addLast(6);
        deque.addLast(7);
        deque.addLast(8);
        deque.addLast(9);
        deque.addLast(10);

        for (int i: deque)
        {
            System.out.println(i);
        }

    }

}