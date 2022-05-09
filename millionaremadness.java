import java.io.*;
import java.util.*;

public class millionaremadness {
	public static void main(String[] args) {
		FastRead sc = new FastRead();
        PrintWriter pw = new PrintWriter(new BufferedWriter(new OutputStreamWriter(System.out)));

        // Setting up the vault as a 2D array
        int length = sc.nextInt();
        int width = sc.nextInt();
        int[][] vault = new int[length][width];
        for (int i = 0; i < length; i++){
        	for (int j = 0; j < width; j++){
        		vault[i][j] = sc.nextInt();
        	}
        }
        int[][] visited = new int[length][width]; // Default set to 0
        PriorityQueue<Step> pq = new PriorityQueue<Step>();
        int result = 0;
        pq.offer(new Step(0,0,0)); // Starting step

        // Main loop, while final step is not visited
        while (visited[length-1][width-1] == 0){
        	Step next = pq.poll(); // Poll the next lowest step in pq 
        	int row = next.r;
        	int col = next.c;
        	int cur_height = vault[row][col];
        	if (next.diff > result){ result = next.diff; } // Update the result with the height diff
        	visited[row][col] = 1; // So we don't come back here

        	int back = row - 1;
        	int front = row + 1;
        	int left = col - 1;
        	int right = col + 1;

        	// Add next possible steps to our pq
        	if (back >= 0 && visited[back][col] == 0) { // Move back
        		pq.offer(new Step(back, col, vault[back][col] - cur_height));
        	}
        	if (front < length && visited[front][col] == 0) { // Move front
        		pq.offer(new Step(front, col, vault[front][col] - cur_height));
        	}
        	if (left >= 0 && visited[row][left] == 0) { // Move left
        		pq.offer(new Step(row, left, vault[row][left] - cur_height));
        	}
        	if (right < width && visited[row][right] == 0) { // Move right
        		pq.offer(new Step(row, right, vault[row][right] - cur_height));
        	}
        }
        pw.print(result);
		pw.close();
	}

	public static class Step implements Comparable<Step>{
		int r, c, diff;

		public Step(int r, int c, int diff){
			this.r = r;
			this.c = c;
			this.diff = diff;
		}

		public int compareTo(Step s){
			return (this.diff - s.diff); // Order pq by shortest height diff
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