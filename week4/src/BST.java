import edu.princeton.cs.algs4.ResizingArrayQueue;

import java.util.Iterator;

public class BST<Key extends Comparable<Key>, Value>
{
    private Node root;

    public BST()
    {

    }

    public Value get(Key key)
    {
        if(key == null) throw new NullPointerException();
        Node x = root;
        while(x != null)
        {
            int cmp = key.compareTo(x.key);
            if(cmp < 0) x = x.left;
            else if (cmp > 0) x = x.right;
            else return x.value;
        }
        return null;
    }


    public void insert(Key key, Value value)
    {
        root = insert(root, key, value);
    }

    private Node insert(Node x, Key key, Value value)
    {
        if(x == null) x = new Node(key, value);
        int cmp = key.compareTo(x.key);
        if(cmp < 0) x.left = insert(x.left, key, value);
        else if (cmp > 0) x.right = insert(x.right, key, value);
        else x.value = value;
        return x;
    }

    public Iterable<Key> keys()
    {
        ResizingArrayQueue<Key> queue = new ResizingArrayQueue<>();
        addInOrder(root, queue);
        return queue;
    }

    private void addInOrder(Node x, ResizingArrayQueue queue)
    {
        if(x == null) return;
        addInOrder(x.left, queue);
        queue.enqueue(x.key);
        addInOrder(x.right, queue);
    }

    private class Node
    {
        private Key key;
        private Value value;
        private Node left, right;

        Node(Key key, Value value)
        {
            this.key = key;
            this.value = value;
        }
    }

    public static void main(String[] args) {
        BST<Character, Integer> bst = new BST<>();
        bst.insert('S', 5);
        bst.insert('E', 2);
        bst.insert('X', 6);
        bst.insert('C', 1);
        bst.insert('H', 3);
        bst.insert('L', 4);

        for(Character key: bst.keys())
        {
            System.out.printf("Key: %c Value: %d%n", key, bst.get(key));
        }
    }
}
