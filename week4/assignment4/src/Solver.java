import edu.princeton.cs.algs4.In;
import edu.princeton.cs.algs4.Stack;
import edu.princeton.cs.algs4.MinPQ;
import edu.princeton.cs.algs4.StdOut;

public class Solver {

    private final Stack<Board> solution;
    private final boolean isSolvable;

    // find a solution to the initial board (using the A* algorithm)
    public Solver(Board initial)
    {
        if(initial == null)
        {
            throw new NullPointerException();
        }

        solution = new Stack<>();
        solution.push(initial);

        MinPQ<Move> boards = new MinPQ<>();
        Move lastMove = new Move(initial, null);

        MinPQ<Move> twinBoards = new MinPQ<>();
        Move lastTwin = new Move(initial.twin(), null);

        while(!solution.peek().isGoal())
        {
            if(lastTwin.board.isGoal())
            {
                isSolvable = false;
                return;
            }

            for (Board b: lastMove.board.neighbors())
            {
                if(lastMove.previous == null || !b.equals(lastMove.previous.board))
                {
                    boards.insert(new Move(b, lastMove));
                }
            }
            lastMove = boards.delMin();
            solution.push(lastMove.board);
            for(Board t: lastTwin.board.neighbors())
            {
                if(lastTwin.previous == null || !t.equals(lastTwin.previous.board)) {
                    twinBoards.insert(new Move(t, lastTwin));
                }
            }
            lastTwin = twinBoards.delMin();
        }
        isSolvable = true;

    }

    private class Move implements Comparable<Move>
    {
        private Board board;
        private Move previous;
        private int moves = 0;
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
            return solution.size()-1;
        }
        return -1;
    }

    // sequence of boards in a shortest solution; null if unsolvable
    public Iterable<Board> solution()
    {
        if(isSolvable)
        {
            Stack<Board> copy = new Stack<>();
            for(Board b: solution)
            {
                copy.push(b);
            }
            return copy;
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

        // print solution to standard output
        if (!solver.isSolvable())
            StdOut.println("No solution possible");
        else {
            StdOut.println("Minimum number of moves = " + solver.moves());
            for (Board board : solver.solution())
                StdOut.println(board);
        }
    }

}