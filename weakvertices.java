import java.io.*;
import java.util.*;

public class weakvertices {
	public static void main(String[] args){
		Scanner sc = new Scanner(System.in);
        PrintWriter pw = new PrintWriter(new BufferedWriter(new OutputStreamWriter(System.out)));

        while (true){
        	int n = sc.nextInt();
        	if (n == -1){break;} // EOF marked by -1
        	int[][] adjMatrix = new int[n][n];

        	// Filling up the adjMatrix
        	for (int r = 0; r < n; r++) { // Iterate through rows
        		for (int c = 0; c < n; c++) { // Iterate through cols
        			adjMatrix[r][c] = sc.nextInt();
        		}
        	}

        	// Check for triangles
        	for (int r = 0; r < n; r++){
        		if (isTriangle(r, adjMatrix)) {continue;}
        		else pw.println(r + " ");
        	}
        }
		pw.close();
	}

	public static boolean isTriangle(int vert, int[][] adjMatrix){
		int n = adjMatrix.length;

		for (int a = 0; a < n; a++){
			if (adjMatrix[vert][a] == 1) {
				for (int b = a + 1; b < n; b ++){
					if (adjMatrix[vert][b] == 1 && adjMatrix[a][b] == 1){return true;}
				}
			}
		}
		return false;
	}
}