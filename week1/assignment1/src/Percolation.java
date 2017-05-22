import edu.princeton.cs.algs4.WeightedQuickUnionUF;

public class Percolation {

    private final int VIRTUAL_SITE_NUM = 2;  // 2 for virtual top and bottom
    private final int NUM_ROW_COLS;

    private boolean[][] _sites;
    private int _numOpenSites;
    private WeightedQuickUnionUF _quickUnion;

    // create n-by-n grid, with all sites blocked
    public Percolation(int n)
    {
        if (n  <= 0)
        {
            throw new java.lang.IllegalArgumentException("Couldn't create percolation grid with a value of 0 or less rows/columns");
        }
        NUM_ROW_COLS = n;
        _sites = new boolean[n][n];
        _numOpenSites = 0;
        _quickUnion = new WeightedQuickUnionUF(n*n + VIRTUAL_SITE_NUM);
        for (int i = 1; i <= n; ++i)
        {
            _quickUnion.union(0, i);
            _quickUnion.union(n*n + 1-i, n*n+1);
        }
    }

    private int xyTo1D(int row, int col)
    {
        return (row - 1)*NUM_ROW_COLS + (col - 1) + 1; // +1 to ignore first index for virtual top site
    }

    private void validRowCol(int row, int col)
    {
        if (row < 1 || row > NUM_ROW_COLS)  throw new java.lang.IndexOutOfBoundsException("Row index is out of bounds.");
        if (col < 1 || col > NUM_ROW_COLS) throw new java.lang.IndexOutOfBoundsException("Column index is out of bounds.");
    }

    // open site (row, col) if it is not open already
    public void open(int row, int col)
    {
        validRowCol(row, col);
        if (!_sites[row-1][col-1]) {
            _sites[row - 1][col - 1] = true;
            _numOpenSites++;

            int[][] neighbors = {{row + 1, col}, {row - 1, col}, {row, col + 1}, {row, col - 1}};

            for (int i = 0; i < neighbors.length; i++) {
                if (!(neighbors[i][0] > NUM_ROW_COLS || neighbors[i][0] < 1 ||
                    neighbors[i][1] > NUM_ROW_COLS || neighbors[i][1] < 1)
                    && isOpen(neighbors[i][0], neighbors[i][1])) {
                        _quickUnion.union(
                                xyTo1D(row, col),
                                xyTo1D(neighbors[i][0], neighbors[i][1])
                        );
                }
            }
        }
    }

    // is site (row, col) open?
    public boolean isOpen(int row, int col)
    {
        validRowCol(row, col);
        return _sites[row-1][col-1];
    }

    // is site (row, col) full?
    public boolean isFull(int row, int col) {
        validRowCol(row, col);
        if (_sites[row-1][col-1])
        {
            return _quickUnion.connected(xyTo1D(row, col), 0);
        }
        return false;
    }

    // number of open sites
    public int numberOfOpenSites() {
        return _numOpenSites;
    }

    // does the system percolate?
    public boolean percolates() {
        return _quickUnion.connected(0, NUM_ROW_COLS*NUM_ROW_COLS+1);
    }

    // test client (optional)
    public static void main(String[] args)
    {
    }

}