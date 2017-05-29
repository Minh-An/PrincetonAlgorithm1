import edu.princeton.cs.algs4.StdRandom;

import java.util.Iterator;
import java.util.NoSuchElementException;

public class RandomizedQueue<Item> implements Iterable<Item> {

    private int nullCount;
    private int last; // end of queue
    private Item[] queue;

    /**
     * Construct an empty randomized queue
     */
    public RandomizedQueue()
    {
        nullCount = 0;
        last = 0;
        queue = (Item[]) new Object[2];
    }

    /**
     * Is the queue empty?
     * @return true if the queue is empty
     */
    public boolean isEmpty()
    {
        return last == 0;
    }

    /**
     * Returns the size of the queue
     * @return the number of items on the queue
     */
    public int size()
    {
        return last - nullCount;
    }

    /**
     * Adds the item to the queue
     * @param item the item to add
     */
    public void enqueue(Item item)
    {
        if(item == null) throw new NullPointerException();
        if (last == queue.length-1)
        {
            resize(2 * queue.length);
        }
        queue[last++] = item;
    }

    /**
     * Removes and returns a random item
     * @return the item removed
     */
    public Item dequeue()
    {
        if(isEmpty())
        {
            throw new NoSuchElementException("Stack underflow");
        }
        int index = -1;
        Item item = null;
        while(item == null)
        {
            index = StdRandom.uniform(last);
            item = queue[index];
        }
        queue[index] = null;
        nullCount++;
        if (!isEmpty() && size() == queue.length / 4)
        {
            resize(queue.length/2);
        }
        return item;
    }

    /**
     * Returns (but does not remove) a random item
     * @return a random item in the queue
     */
    public Item sample()
    {
        if(isEmpty())
        {
            throw new NoSuchElementException("Stack underflow");
        }
        int index;
        Item item = null;
        while(item == null)
        {
            index = StdRandom.uniform(last);
            item = queue[index];
        }
        return item;
    }

    /**
     * Returns an independent iterator over items in random order
     * @return an independent iterator over itms in random order
     */
    public Iterator<Item> iterator()
    {
        return new RandomizedIterator();
    }

    //iterator that goes through the queue in a random order
    private class RandomizedIterator implements Iterator<Item> {

        private int current;
        private Item items[];

        public RandomizedIterator()
        {
            current = 0;

            items = (Item[]) new Object[size()];
            int j = 0;
            for (int i = 0, n = queue.length; i < n; i++)
            {
                if(queue[i] != null)
                {
                    items[j] = queue[i];
                    j++;
                }
            }
            StdRandom.shuffle(items);
        }

        @Override
        public boolean hasNext() {
            return current < items.length;
        }

        @Override
        public Item next() {
            if(!hasNext()) throw new NoSuchElementException();
            return items[current++];
        }

        @Override
        public void remove() {
            throw new UnsupportedOperationException();
        }
    }

    //resize the array holding the items in the queue
    private void resize(int capacity)
    {
        assert capacity >= size();

        Item[] copy = (Item[]) new Object[capacity];
        int j = 0;
        for (int i = 0; i < last; i++)
        {
            if(queue[i] != null) {
                copy[j] = queue[i];
                j++;
            }
        }
        last = j;
        nullCount = 0;
        queue = copy;
    }

    // unit testing (optional)
    public static void main(String[] args)
    {
        RandomizedQueue<Integer> rq = new RandomizedQueue<>();
        rq.isEmpty();
        rq.enqueue(25);
        rq.enqueue(39);
        rq.dequeue();
        System.out.println( rq.size());   //     ==> 1
        rq.enqueue(39);
        System.out.println(rq.dequeue()); //     ==> 39
        System.out.println(rq.dequeue()) ;//    ==> 39
        rq.enqueue(33);
        System.out.println(rq.dequeue());//     ==> 33
        rq.enqueue(35);


    }

}