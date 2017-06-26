


import edu.princeton.cs.algs4.Stack;


public class Board {

    private final int[] board;
    private final int dimension;

    // construct a board from an n-by-n array of blocks
    // (where blocks[i][j] = block in row i, column j)
    public Board(int[][] blocks) {
        dimension = blocks.length;
        board = new int[blocks.length * blocks.length];

        for (int i = 0, n = blocks.length; i < n; i++) {
            for (int j = 0; j < n; j++) {
                board[xyTo1D(i, j)] = blocks[i][j];
            }
        }
    }

    private int xyTo1D(int row, int col) {
        return (row) * dimension + (col);
    }

    // board dimension n
    public int dimension() {
        return dimension;
    }

    // number of blocks out of place
    public int hamming() {
        int hamming = 0;
        for (int i = 0, n = board.length; i < n; i++) {
            if (board[i] != i + 1 && board[i] != 0) {
                hamming++;
            }
        }
        return hamming;
    }

    // sum of Manhattan distances between blocks and goal
    public int manhattan() {
        int manhattan = 0;
        for (int i = 0; i < dimension; i++) {
            for (int j = 0; j < dimension; j++) {
                int index = xyTo1D(i, j);

                if (board[index] != index + 1 && board[index] != 0) {
                    manhattan += Math.abs((board[index] - 1) / dimension - (i));
                    manhattan += Math.abs((board[index] - 1) % dimension - (j));

                }
            }
        }
        return manhattan;
    }

    // is this board the goal board?
    public boolean isGoal() {
        for (int i = 0, n = board.length; i < n; i++) {
            if (board[i] != i + 1 && board[i] != 0) {
                return false;
            }
        }
        return true;
    }

    // a board that is obtained by exchanging any pair of blocks
    public Board twin() {
        int i = 0;
        while (board[i] == 0 || board[i + 1] == 0 || (i + 1) % dimension == 0) {
            i++;
        }
        return swap(i / dimension, i % dimension, (i + 1) / dimension, (i + 1) % dimension);
    }

    private Board swap(int x1, int y1, int x2, int y2) {
        if (x1 < 0 || x1 >= dimension || y1 < 0 || y1 >= dimension) {
            return null;
        }
        int[][] swapped = new int[dimension][dimension];
        for (int i = 0; i < dimension; i++) {
            for (int j = 0; j < dimension; j++) {
                swapped[i][j] = board[xyTo1D(i, j)];
            }
        }
        //exchange
        swapped[x1][y1] = board[xyTo1D(x2, y2)];
        swapped[x2][y2] = board[xyTo1D(x1, y1)];
        return new Board(swapped);
    }


    // all neighboring boards
    public Iterable<Board> neighbors() {
        Stack<Board> neighbors = new Stack<>();
        int i = 0;
        while (board[i] != 0) {
            i++;
        }
        int x = i / dimension;
        int y = i % dimension;
        Board[] boards = {swap(x + 1, y, x, y), swap(x - 1, y, x, y), swap(x, y + 1, x, y), swap(x, y - 1, x, y)};
        for (Board b : boards) {
            if (b != null) {

                neighbors.push(b);
            }
        }
        return neighbors;
    }

    // does this board equal y?
    public boolean equals(Object y) {
        if (y == null) {
            return false;
        }
        return toString().equals(y.toString());
    }


    // string representation of this board (in the output format specified below)
    public String toString() {
        StringBuilder s = new StringBuilder();
        s.append(dimension + "\n");
        for (int i = 0; i < dimension; i++) {
            for (int j = 0; j < dimension; j++) {
                s.append(String.format("%2d ", board[xyTo1D(i, j)]));
            }
            s.append("\n");
        }
        return s.toString();
    }

    // unit tests (not graded)
    public static void main(String[] args) {
        int[][] blocks = {{1, 2, 4}, {0, 5, 3}, {7, 8, 6}};
        Board b = new Board(blocks);
        System.out.println(b.toString());

        //System.out.println("Manhattan and Hamming");
        //System.out.println(b.manhattan());
        //System.out.println(b.hamming());

        //System.out.println("Twin");
        //System.out.println(b.twin().toString());

        System.out.println("Neighbors");
        for (Board neighbor : b.neighbors()) {
            System.out.println(neighbor.toString());
        }

    }
}