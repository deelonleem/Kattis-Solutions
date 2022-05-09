import java.io.*;
import java.util.*;

public class workstations {
	public static void main(String[] args){
		// Initilisation 
		Scanner sc = new Scanner(System.in);
        PrintWriter pw = new PrintWriter(new BufferedWriter(new OutputStreamWriter(System.out)));

        // Initilisation of variables
        int n = sc.nextInt();
        int m = sc.nextInt();
        int saved = 0; // Track the number of times an unlock is saved

        // Custom comparators to sort researcher by shortest times 
        Comparator<Researcher> r_compare = new Comparator<Researcher>(){
        	public int compare(Researcher first, Researcher second){
        		if (first.a < second.a){return -1;}
        		else if (first.a > second.a){return +1;}
        		else {return first.s-second.s;}
        	}
        };

        // Setting up PQs
        PriorityQueue<Researcher> researchers = new PriorityQueue<Researcher>(r_compare);
        PriorityQueue<Integer> endtimes = new PriorityQueue<Integer>(); // Default is min heap alr
        // Loop to fill up Researcher PQ
        for (int i = 0; i<n; i++){
        	int a = sc.nextInt();
        	int s = sc.nextInt();
        	Researcher r = new Researcher(a, s);
        	researchers.add(r);
        }

        // Main body
        endtimes.add(researchers.poll().totaltime()); // Start the first workstation
        while (researchers.isEmpty() == false){
        	Researcher next_r = researchers.poll();
        	endtimes.add(next_r.totaltime());

        	if (next_r.arrivaltime() < endtimes.peek()){continue;}

        	while(endtimes.isEmpty() == false){
        		int earliest = endtimes.poll(); // Earliest ending workstation
        		int diff = next_r.arrivaltime() - earliest;
        		if (diff > m){continue;} // Logged out already, continue searching
        		else if (diff < 0){ // Still in use, got to open new workstation
        			endtimes.add(earliest);
        			break;
        		}
        		else{ // Not expired and not in use, saved 1 unlock!
        			saved+=1;
        			break;
        		}
        	}

        }
		pw.println(saved);
		pw.close();
	}

	public static class Researcher {
		protected int a;
		protected int s;

		Researcher(int a, int s) {
			this.a = a;
			this.s = s;
		}

		int totaltime() { return a + s; }

		int arrivaltime() { return a; }
	}

}

