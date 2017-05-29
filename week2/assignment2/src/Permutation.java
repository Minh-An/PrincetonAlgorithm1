import edu.princeton.cs.algs4.StdIn;

public class Permutation {
    public static void main(String[] args)
    {
        if(args.length < 1) throw new java.lang.IllegalArgumentException();
        int k = Integer.parseInt(args[0]);
        RandomizedQueue<String> file = new RandomizedQueue<>();
        while(!StdIn.isEmpty())
        {
            file.enqueue(StdIn.readString());
        }

        if (k >= 0 || k <= file.size())
        {
            for(int i = 0; i < k; i++)
            {
                System.out.println(file.dequeue());
            }
        }
    }
}
