import java.io.*;
import java.util.*;

public class islands {
	public static void main(String[] args) {
		// Initilisation 
		Scanner sc = new Scanner(System.in);
        PrintWriter pw = new PrintWriter(new BufferedWriter(new OutputStreamWriter(System.out)));

        int r = sc.nextInt();
        int c = sc.nextInt();
        int islands = 0;
        char[][] surface = new char[r][c];
        int[][] visited = new int[r][c]; // Default set to 0

        // Setting up the surface array
        for (int i = 0; i < r; i++) {
        	char[] row = sc.next().toCharArray();
        	for (int j = 0; j < c; j++) {
        		surface[i][j] = row[j];
        	}
        }

        // Main loop
        for (int i = 0; i < r; i++) {
        	for (int j = 0; j < c; j++) {
        		if ((surface[i][j] == 'L') && (visited[i][j] == 0)) {
        			dfs(i, j, r, c, surface, visited);
        			islands += 1;
        		}
        	}
        }
		pw.println(islands);
		pw.close();
	}

	// DFS algo to fully visit the current island
	public static void dfs(int curr_row, int curr_col, int r, int c, char[][] surface, int[][] visited) {
		visited[curr_row][curr_col] = 1; // So we don't revisit this

		// Defining coordinates
		int up = curr_row - 1;
		int down = curr_row + 1;
		int left = curr_col - 1;
		int right = curr_col + 1;

		// Case 1: Go up if these 3 conditions are met
		if ((up >= 0) && (visited[up][curr_col] == 0) && (surface[up][curr_col] == 'L' || surface[up][curr_col] == 'C')) {
			dfs(up, curr_col, r, c, surface, visited);
		}
		// Case 2: Go down if these 3 conditions are met
		if ((down < r) && (visited[down][curr_col] == 0) && (surface[down][curr_col] == 'L' || surface[down][curr_col] == 'C')) {
			dfs(down, curr_col, r, c, surface, visited);
		}
		// Case 3: Go left if these 3 conditions are met
		if ((left >= 0) && (visited[curr_row][left] == 0) && (surface[curr_row][left] == 'L' || surface[curr_row][left] == 'C')) {
			dfs(curr_row, left, r, c, surface, visited);
		}
		// Case 4: Go right if these 3 conditions are met
		if ((right < c) && (visited[curr_row][right] == 0) && (surface[curr_row][right] == 'L' || surface[curr_row][right] == 'C')) {
			dfs(curr_row, right, r, c, surface, visited);
		}
	}

}
