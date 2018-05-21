
import edu.princeton.cs.algs4.StdOut;
import edu.princeton.cs.algs4.StdRandom;
import edu.princeton.cs.algs4.StdStats;

public class PercolationStats {
    private final double[] resultArray;
    private final double _mean;
    private final double _stdDev;
    private final double _confidenceLo;
    private final double _confidenceHi;

    public PercolationStats(int n, int trials) {
        // perform trials independent experiments on an n-by-n grid
        if (n <= 0 || trials <= 0) {
            throw new IllegalArgumentException("n and trials should be greater than 0");
        }

        this.resultArray = new double[trials];

        for (int i = 0; i < trials; ++i) {
            Percolation system = new Percolation(n);
            while (!system.percolates()) {
                int row = StdRandom.uniform(n) + 1;
                int col = StdRandom.uniform(n) + 1;
                system.open(row, col);
            }
            this.resultArray[i] = (double) system.numberOfOpenSites() / (n * n);
        }
        this._mean = StdStats.mean(this.resultArray);
        this._stdDev = StdStats.stddev(this.resultArray);
        double interimResult = 1.96 * this._stdDev / Math.sqrt(trials);
        this._confidenceLo = this._mean - interimResult;
        this._confidenceHi = this._mean + interimResult;
    }

    public double mean() {
        // sample mean of percolation threshold
        return this._mean;
    }

    public double stddev() {
        // sample standard deviation of percolation threshold
        return this._stdDev;
    }

    public double confidenceLo() {
        // low endpoint of 95% confidence interval
        return this._confidenceLo;
    }

    public double confidenceHi() {
        // high endpoint of 95% confidence interval
        return this._confidenceHi;
    }

    public static void main(String[] args) {
        // test client (described below)
        PercolationStats stats = new PercolationStats(5, 10);
        stats.printResultsArray();
        StdOut.println("Mean: " + stats.mean());
        StdOut.println("Std Deviation: " + stats.stddev());
        StdOut.println("Confidence Low: " + stats.confidenceLo());
        StdOut.println("Confidence High: " + stats.confidenceHi());
    }

    private void printResultsArray() {
        for (int i = 0; i < this.resultArray.length; ++i) {
            StdOut.print(this.resultArray[i] + ", ");
        }
        StdOut.print("\n");
    }

}
