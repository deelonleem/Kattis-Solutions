import java.io.*;
import java.util.*;

public class dominos {
	// Global declarations
	public static ArrayList<ArrayList<Integer>> adjList;
	public static ArrayList<Integer> toposort;
	public static int[] visited_t, visited_k; // For toposort and kosaraju

	public static void main(String[] args) {
		FastRead sc = new FastRead();
        PrintWriter pw = new PrintWriter(new BufferedWriter(new OutputStreamWriter(System.out)));
        int tests = sc.nextInt();

        for (int i = 0; i < tests; i++) {
        	int dominos = sc.nextInt();
        	int lines = sc.nextInt();

        	// Initialisations
        	int knocks = 0;
			adjList = new ArrayList<ArrayList<Integer>>();
        	toposort = new ArrayList<Integer>();
        	visited_t = new int[dominos]; // Visited during toposort
        	visited_k = new int[dominos]; // Visited during kosaraju

        	// Setting up adjList
        	for (int j = 0; j < dominos; j++) { adjList.add(new ArrayList<Integer>());} 
        	for (int k = 0; k < lines; k++) {
        		int d1 = sc.nextInt(); 
        		int d2 = sc.nextInt();
        		adjList.get(d1-1).add(d2-1); // -1 because 0 indexed
        	}

        	// Perform toposort on the adjList
        	for (int v = 0; v < dominos; v++){
        		if (visited_t[v] == 0){ DFSrec(v);}
        	}

        	// Perform kosaraju from reverse topo 
        	for (int v = dominos - 1; v >= 0; v--) {
        		int x = toposort.get(v);
        		if (visited_k[x] == 0) {
        			kosaraju(x);
        			knocks += 1;
        		}
        	}
        pw.println(knocks);
        }
	pw.close();
	}

	//DFS Topological Sort from the lecture notes
	public static void DFSrec(int u) { 
    	visited_t[u] = 1; // To avoid cycles
    	ArrayList<Integer> adjacent = adjList.get(u);
    	for (int i : adjacent) { // Iterate through neighbours of u
      		if (visited_t[i] == 0) { DFSrec(i);} // Visit it if not visited yet
    	}
    	toposort.add(u); // Post order! Append u to back of toposort
  	}

  	// Kosaraju's algo from the lecture notes
  	public static void kosaraju(int u) { 
    	visited_k[u] = 1; // To avoid cycles
    	ArrayList<Integer> adjacent = adjList.get(u);
    	for (int i : adjacent) { // Iterate through neighbours of u
      		if (visited_k[i] == 0) { kosaraju(i);} // Visit it if not visited yet
    	}
  	}

  	public static class FastRead{ // Class to optimise next() and nextInt().
		BufferedReader br; 
		StringTokenizer st; 

		public FastRead() { 
			br = new BufferedReader(new InputStreamReader(System.in));
		}

		String next(){ // Optimises the next() method.
			while (st == null || !st.hasMoreElements()){
				try {
					st = new StringTokenizer(br.readLine());
				}
				catch (IOException e){
					e.printStackTrace();
				}
			}
			return st.nextToken();
		}

		int nextInt(){ // Optimised nextInt() using next() above.
			return Integer.parseInt(next());
		}
	}

}