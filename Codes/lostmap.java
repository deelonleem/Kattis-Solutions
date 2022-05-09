import java.io.*;
import java.util.*;

public class lostmap {
	public static void main(String[] args){
		// Initilisation 
		Scanner sc = new Scanner(System.in);
        PrintWriter pw = new PrintWriter(new BufferedWriter(new OutputStreamWriter(System.out)));

        // Set-up
        int n = sc.nextInt(); // Number of villages
        int inf = 10000000; // Will use this value as infinity
        ArrayList<ArrayList<IntegerPair>> adjList = new ArrayList<ArrayList<IntegerPair>>(); // Graph stored via adjList 
        IntegerPair[] mst = new IntegerPair[n]; // Array to hold the final mst
        int[] visited = new int[n]; // Array to hold visited vertices
        for (int i = 0; i < n; i++){ 
        	adjList.add(new ArrayList<IntegerPair>()); // Add an empty ArrayList to the adjlist
        	mst[i] = new IntegerPair(-1, inf); // Set all the mst distances to inf first
        	for (int j = 0; j < n; j++){
        		int d = sc.nextInt();
        		if (d!=0) { adjList.get(i).add(new IntegerPair(j, d)); }
        	}	
        }

        // Main loop
        int count = 0;
        mst[0] = new IntegerPair(0,0); // Source vertex
        while (count < n){
        	IntegerPair smallest = null;
        	int minIndex = 0;
        	int minDist = inf;
        	for (int i = 0; i < n; i++){
        		IntegerPair curr = mst[i];
        		if (curr.dist < minDist && visited[i] == 0){
        			smallest = curr;
        			minIndex = i;
        			minDist = curr.dist;
        		}
        	}
        	count += 1;
        	minDist = inf;
        	visited[minIndex] = 1;
        	for (IntegerPair pair: adjList.get(minIndex)) {
        		if (visited[pair.vert] == 0 && mst[pair.vert].dist > pair.dist) {
        			mst[pair.vert] = new IntegerPair(minIndex, pair.dist);
        		}
        	}
		}
		// Print function
        for (int i = 1; i < n; i++) {
        	pw.println((mst[i].vert + 1) + " " + (i+1));
        	}
        pw.close();
        }


	public static class IntegerPair{ // Integer pair that holds destination vertex and distance to it
		int vert, dist;
		public IntegerPair(int v, int d){
			vert = v;
			dist = d;
		}
	}
}