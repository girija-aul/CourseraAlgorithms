
import edu.princeton.cs.algs4.StdOut;
import edu.princeton.cs.algs4.StdRandom;
import edu.princeton.cs.algs4.WeightedQuickUnionUF;

public class Percolation {

    private final WeightedQuickUnionUF grid;
    private final boolean arr[];
    private final int size;
    private final int virtualTopNode;
    private final int virtualBottomNode;
    private int nOpenSites = 0;

    public Percolation(int n) {
        if (n <= 0) {
            throw new IllegalArgumentException(Integer.toString(n));
        }
        this.size = n;

        int totalElements = n * n;

        // Below statement internally is assigning indices values to array elements
        grid = new WeightedQuickUnionUF(totalElements + 2);

        virtualTopNode = totalElements;
        virtualBottomNode = totalElements + 1;

        arr = new boolean[totalElements];
        // All the elements are blocked initially
        for (int i = 0; i < totalElements; ++i) {
            arr[i] = false;
        }

        // First row is connected to virtualTopNode
        for (int i = 0; i < size; ++i) {
            grid.union(i, virtualTopNode);
        }

        // Last row is connected to virtualBottomNode
        for (int i = totalElements - n; i < totalElements; ++i) {
            grid.union(i, virtualBottomNode);
        }
    }

    public void open(int row, int col) {
        int id = getArrayIndexFromRowCol(row, col);
        if (!arr[id]) {
            this.nOpenSites++;
            arr[id] = true;

            // check for top neighbor
            if (row > 1) {
                int topNeighbor = getArrayIndexFromRowCol(row - 1, col);
                if (arr[topNeighbor]) {
                    grid.union(id, topNeighbor);
                }
            }

            // check for bottom neighbor
            if (row < this.size) {
                int bottomNeighbor = getArrayIndexFromRowCol(row + 1, col);
                if (arr[bottomNeighbor]) {
                    grid.union(id, bottomNeighbor);
                }
            }

            // check for left neighbor
            if (col > 1) {
                int leftNeighbor = getArrayIndexFromRowCol(row, col - 1);
                if (arr[leftNeighbor]) {
                    grid.union(id, leftNeighbor);
                }
            }

            // check for right neighbor
            if (col < this.size) {
                int rightNeighbor = getArrayIndexFromRowCol(row, col + 1);
                if (arr[rightNeighbor]) {
                    grid.union(id, rightNeighbor);
                }
            }
        }
    }

    public boolean isOpen(int row, int col) {
        int id = getArrayIndexFromRowCol(row, col);

        return arr[id];
    }

    public boolean isFull(int row, int col) {
        int id = getArrayIndexFromRowCol(row, col);

        if (isOpen(row, col) && this.grid.connected(id, this.virtualTopNode)) {
            return true;
        }
        return false;
    }

    public int numberOfOpenSites() {
        return this.nOpenSites;
    }

    public boolean percolates() {
        if (this.nOpenSites > 0 && this.grid.connected(this.virtualTopNode, this.virtualBottomNode)) {
            return true;
        }
        return false;
    }

    private void print() {
        for (int i = 0; i < this.size; ++i) {
            for (int j = 0; j < this.size; ++j) {
                StdOut.print(arr[i * this.size + j] ? "1 " : "0 ");
            }
            StdOut.print("\n");
        }
    }

    public static void main(String[] args) {
        // TODO Auto-generated method stub
        int size = 2;
        Percolation sample = new Percolation(size);

        sample.print();
        StdOut.println("Initial Configuration and percolate: " + sample.percolates());

        do {
            // Generate random row and col
            int row, col;
            do {
                row = StdRandom.uniform(size) + 1;
                col = StdRandom.uniform(size) + 1;
            } while (sample.isOpen(row, col));

            StdOut.println("Marking (" + row + ", " + col + ") open");
            sample.open(row, col);
            sample.print();
            StdOut.println("Percolate: " + sample.percolates());
        } while (!sample.percolates());

    }

    private int getArrayIndexFromRowCol(int row, int col) {
        if ((row < 1) || (col < 1) || (row > size) || (col > size)) {
            throw new IllegalArgumentException("row and col must be in the range 1 to " + this.size);
        }

        return ((row - 1) * size + col - 1);
    }

}
