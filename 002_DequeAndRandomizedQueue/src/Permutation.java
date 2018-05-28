import edu.princeton.cs.algs4.StdIn;
import edu.princeton.cs.algs4.StdOut;

public class Permutation {

    public static void main(String[] args) {
        if (args.length != 1) {
            throw new java.lang.IllegalArgumentException();
        }
        int nStrings = Integer.parseInt(args[0]);
        if (nStrings > 0) {
            RandomizedQueue<String> q = new RandomizedQueue<String>();
            while (!StdIn.isEmpty()) {
                String s = StdIn.readString();
//                if (q.size() == nStrings) {
//                    q.dequeue();
//                }
                q.enqueue(s);
            }

            for (int i = 0; i < nStrings; ++i) {
                StdOut.println(q.dequeue());
            }
        }
    }

}
