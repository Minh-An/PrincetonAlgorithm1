import edu.princeton.cs.algs4.In;
import edu.princeton.cs.algs4.Stack;
import edu.princeton.cs.algs4.MinPQ;
import edu.princeton.cs.algs4.StdOut;

public class Solver {

    private Move solutionMove;
    private final boolean isSolvable;

    // find a solutionMove to the initial board (using the A* algorithm)
    public Solver(Board initial)
    {
        if(initial == null)
        {
            throw new NullPointerException();
        }


        MinPQ<Move> boards = new MinPQ<>();
        solutionMove =  new Move(initial, null);
        boards.insert(solutionMove);

        MinPQ<Move> twinBoards = new MinPQ<>();
        Move twinMove = new Move(initial.twin(), null);
        twinBoards.insert(twinMove);

        while(!solutionMove.board.isGoal())
        {
            if(twinMove.board.isGoal())
            {
                isSolvable = false;
                return;
            }


            for (Board b: solutionMove.board.neighbors())
            {
                if(solutionMove.previous == null || !b.equals(solutionMove.previous.board))
                {
                    boards.insert(new Move(b, solutionMove));
                }
            }
            solutionMove = boards.delMin();

            for(Board t: twinMove.board.neighbors())
            {
                if(twinMove.previous == null || !t.equals(twinMove.previous.board)) {
                    twinBoards.insert(new Move(t, twinMove));
                }
            }
            twinMove = twinBoards.delMin();

        }
        isSolvable = true;

    }

    private class Move implements Comparable<Move>
    {
        public Board board;
        private Move previous;
        public int moves = 0;
        private int priority;

        public Move(Board board, Move previous) {
            this.board = board;
            this.previous = previous;
            if (previous != null) {
                moves = previous.moves + 1;
            }
            priority = moves + board.manhattan();
        }

        @Override
        public int compareTo(Move o) {
            if(priority < o.priority) return -1;
            if (priority > o.priority) return 1;
            else return board.manhattan() - o.board.manhattan();
        }
    }

    // is the initial board solvable?
    public boolean isSolvable()
    {
        return isSolvable;
    }

    // min number of moves to solve initial board; -1 if unsolvable
    public int moves()
    {
        if(isSolvable)
        {
            return solutionMove.moves;
        }
        return -1;
    }

    // sequence of boards in a shortest solutionMove; null if unsolvable
    public Iterable<Board> solution()
    {
        if(isSolvable)
        {
            Move copy = solutionMove;
            Stack<Board> solution = new Stack<>();
            while(copy != null)
            {
                solution.push(copy.board);
                copy = copy.previous;
            }

            return solution;
        }
        return null;
    }

    // solve a slider puzzle
    public static void main(String[] args)
    {
        In in = new In(args[0]);
        int n = in.readInt();
        int[][] blocks = new int[n][n];
        for (int i = 0; i < n; i++)
            for (int j = 0; j < n; j++)
                blocks[i][j] = in.readInt();
        Board initial = new Board(blocks);

        // solve the puzzle
        Solver solver = new Solver(initial);

        // print solutionMove to standard output
        if (!solver.isSolvable())
            StdOut.println("No solutionMove possible");
        else {
            StdOut.println("Minimum number of moves = " + solver.moves());
            for (Board board : solver.solution())
                StdOut.println(board);
        }
    }

}