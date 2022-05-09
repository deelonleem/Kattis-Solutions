import java.io.*;
import java.util.*;

public class humancannonball {
	public static double[] d;
	public static void main(String[] args){
		// Initilisation 
		Scanner sc = new Scanner(System.in);
        PrintWriter pw = new PrintWriter(new BufferedWriter(new OutputStreamWriter(System.out)));
        ArrayList<Position> poslist = new ArrayList<Position>();
        ArrayList<Edge> edgelist = new ArrayList<Edge>();
        double inf = 100000000;
        Position start = new Position(sc.nextDouble(), sc.nextDouble(), 0, false); // Start point
        Position end = new Position(sc.nextDouble(), sc.nextDouble(), 1, false); // End point
        poslist.add(start);
        poslist.add(end);
        edgelist.add(new Edge(start, end));
        edgelist.add(new Edge(end, start));
        int n = sc.nextInt(); // Number of cannons
        d = new double[n + 2];
        d[0] = 0;
        d[1] = inf;
        for (int i = 0; i < n; i++) { // Collecting info for cannons
        	d[i+2] = inf;
        	Position cannon = new Position(sc.nextDouble(), sc.nextDouble(), i+2, true);
        	for (Position p: poslist) {
        		edgelist.add(new Edge(p, cannon));
        		edgelist.add(new Edge(cannon, p));
        	}
        	poslist.add(cannon);
        }

        // Relax all edges
        for (int i = 0; i < n+1; i++) {
        	for (Edge e: edgelist) {
        		relax(e);
        	}
        }
        pw.println(d[1]);
		pw.close();
	}

	public static class Position {
		double x, y;
		int id; 
		boolean cannon; // True if cannon, else False
		public Position(double i, double j, int iden, boolean c) {
			this.x = i;
			this.y = j;
			this.id = iden; 
			this.cannon = c;
		}
	}

	public static class Edge {
		Position start, end;
		double time;
		public Edge(Position s, Position e) {
			this.start = s;
			this.end = e;
			this.time = getTime();
		}
		public double getTime() {
			double distance = Math.sqrt((start.x-end.x)*(start.x-end.x) + (start.y-end.y)*(start.y-end.y));
			if (start.cannon == false) {
				return distance/5; // Means must walk there
			}
			else {
				return Math.min(Math.abs(distance-50)/5 + 2, distance/5); // Check which is faster, walk or by cannon
			}
		}
	}

	public static void relax(Edge e) {
		if (d[e.end.id] > d[e.start.id] + e.time) {
			d[e.end.id] = d[e.start.id] + e.time;
		}
	}
}