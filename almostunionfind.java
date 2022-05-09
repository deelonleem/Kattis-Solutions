import java.io.*;
import java.util.*;

public class almostunionfind {
    public static void main(String[] args) throws IOException {
        Scanner sc = new Scanner(System.in);
        while (sc.hasNextInt()){
            int n = sc.nextInt();
            int m = sc.nextInt();
            UnionFind ufds = new UnionFind(n);
            for (int k = 0; k < m; k++){
                int command = sc.nextInt();
                if (command == 1){
                    int i = sc.nextInt();
                    int j = sc.nextInt();
                    ufds.unionSets(i, j);
                }
                else if (command == 2){
                    int i = sc.nextInt();
                    int j = sc.nextInt();
                    ufds.moveSets(i, j);
                }
                else if (command == 3){
                    int x = sc.nextInt();
                    int size = ufds.size[ufds.findSet(x)];
                    long sum = ufds.sum[ufds.findSet(x)];
                    System.out.println(size + " " + sum);
                }
            }
        }
    }

    static class UnionFind{
        public int[] p; 
        public long[] sum;
        public int[] size;

        // Setter function
        public UnionFind(int n){
            p = new int[2*n+1];
            sum = new long[2*n+1];
            size = new int[2*n+1];
            for (int i = 1; i < n+1; i++){ // 1 to n+1 since it is 1-indexed
                p[i] = n + i;
                p[n+i] = n + i;
                sum[n+i] = i;
                size[n+i] = 1;
            }
        }

        // Returns the representative of the set element i is in.
        public int findSet(int i) { 
                if (p[i] == i) {return i;}
                else {
                    p[i] = findSet(p[i]);
                    return p[i]; 
                } 
        }

        // Boolean function to determine if two elements are from the same set
        public Boolean isSameSet(int i, int j) { return findSet(i) == findSet(j); }

        // Union function - Union two sets containing i and j 
        // We ignore rank here, we always add i to j
         public void unionSets(int i, int j) {
            if (!isSameSet(i, j)) { // If same set already, do nothing
                int i_rep = findSet(i);
                int j_rep = findSet(j);
                sum[j_rep] += sum[i_rep];
                size[j_rep] += size[i_rep];
                p[i_rep] = j_rep; // Change the entire rep of the set i to j
            }
        }

        // Move function - Move an element i to the set with j
        public void moveSets(int i, int j) { 
            if (!isSameSet(i, j)){ // If same set already, do nothing
                int i_rep = findSet(i);
                int j_rep = findSet(j);
                // Add i to j, remove i from its set.
                sum[j_rep] += i; 
                size[j_rep]++;
                sum[i_rep] -= i;
                size[i_rep]--;
                p[i] = j_rep; // Only change the rep of 1 element i
            }
        }
    }
}