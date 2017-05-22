import edu.princeton.cs.algs4.StdRandom;
import edu.princeton.cs.algs4.StdStats;

public class PercolationStats {

    private int _trials;
    private double[] _thresholds;

    // perform trials independent experiments on an n-by-n grid
    public PercolationStats(int n, int trials)
    {
        if (n <= 0 || trials <= 0)
        {
            throw new java.lang.IllegalArgumentException("Both arguments must be positive integers");
        }
        _trials = trials;
        _thresholds = new double[trials];
        for (int i = 0; i < trials; ++i)
        {
            Percolation percolation = new Percolation(n);
            while (!percolation.percolates())
            {
                percolation.open(StdRandom.uniform(n)+1, StdRandom.uniform(n)+1);
            }
            _thresholds[i] = (double)percolation.numberOfOpenSites()/(n*n);
        }
    }

    // sample mean of percolation threshold
    public double mean()
    {
        return StdStats.mean(_thresholds);
    }

    // sample standard deviation of percolation threshold
    public double stddev()
    {
        return StdStats.stddev(_thresholds);
    }

    // low  endpoint of 95% confidence interval
    public double confidenceLo()
    {
        return mean() - (1.96*stddev())/Math.sqrt(_trials);
    }

    // high endpoint of 95% confidence interval
    public double confidenceHi()
    {
        return mean() + (1.96*stddev())/Math.sqrt(_trials);
    }

    // test client (described below)
    public static void main(String[] args)
    {
        if (args.length == 2)
        {
            PercolationStats stats = new PercolationStats(Integer.parseInt(args[0]), Integer.parseInt(args[1]));
            System.out.printf("mean = %f%n", stats.mean());
            System.out.printf("stddev = %f%n", stats.stddev());
            System.out.printf("95%s confidence interval = [%f, %f]%n", "%", stats.confidenceLo(), stats.confidenceHi());
        }
        else
        {
            throw new java.lang.IllegalArgumentException("Arguments should be n and the number of trials.");
        }
    }
}
