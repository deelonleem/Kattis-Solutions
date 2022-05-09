import java.io.*;
import java.util.*;

public class bestrelayteam {

	public static void main(String[] args){ // Main method
		// Initilisation 
		Scanner sc = new Scanner(System.in);
        PrintWriter pw = new PrintWriter(new BufferedWriter(new OutputStreamWriter(System.out)));
		HashMap<String, Double> firstrunners = new HashMap<String, Double>(); // Overall hashmap to store first runner data. key = name, value = time1.
		HashMap<String, Double> nextrunners = new HashMap<String, Double>(); // Overall hashmap to store next runners data. key = name, value = time2.
 
 		// Collecing information - Tested to be working!
 		int n = Integer.parseInt(sc.nextLine()); // Number of athletes
 		for (int i = 0; i < n; i++) { // Iterates through the n athletes
 			String name = sc.next();
 			double time1 = sc.nextDouble();
 			double time2 = sc.nextDouble();
 			firstrunners.put(name, time1);
 			nextrunners.put(name, time2);
 			}

 		// Sort nextrunners by values - firstrunners doesn't have to be sorted since I'm iterating through all
 		nextrunners = sortbyvalues(nextrunners);

 		// Iterate through each first runner, and then add the 3 fastest nextrunners, and check for best time
 		double best_timing = 100000.0;
 		String[] best_team = new String[4];  

 		for (Map.Entry<String, Double> runner: firstrunners.entrySet()) { // Iterates through the firsttiming HashMap
 			Double curr_timing = 0.00; // Variable to store current team's timing
 			String[] curr_team = new String[4]; // variable to store current team's names
 			curr_timing += runner.getValue(); // Add ith first runner's timing to current timing
 			String name = runner.getKey(); 
 			curr_team[0] = name; // Add the ith first runner to current team 

 			int counter = 0;
 			for (Map.Entry<String, Double> next: nextrunners.entrySet()) { // Iterates through nextrunners (already sorted)
 				if (next.getKey().equals(name)){
 					continue;
 				}
 				if (counter > 2){
 					break;
 				}
 				curr_team[(counter + 1)] = next.getKey();
 				curr_timing += next.getValue();
 				counter += 1; }

 			if (curr_timing < best_timing){ // Check - is current team better than best?
 				best_timing = curr_timing;
 				best_team = curr_team;
 			}
 		}
 	pw.println(best_timing);
 	for (int i = 0; i<4; i++){
 		String name = best_team[i];
 		pw.println(name);
 	}
 	pw.close();
 	}

	// Helper method to sort a HashMap by values - Tested to be working!
    public static HashMap<String, Double> sortbyvalues (HashMap<String, Double> map) {
        // Put kyyvalue pairs of hashmap into a list
        List<Map.Entry<String, Double>> list = new LinkedList<Map.Entry<String, Double>>(map.entrySet());
 
        // Sort the list using Collections.sort and comparator
        Collections.sort(list,new Comparator<Map.Entry<String, Double>>() {
                public int compare(Map.Entry<String, Double> runner1, Map.Entry<String, Double> runner2) {
                    return (runner1.getValue()).compareTo(runner2.getValue());
                }
            });
 
        // Put the data back into a new HashMap
        HashMap<String, Double> sorted = new LinkedHashMap<String, Double>();
        for (Map.Entry<String, Double> runner : list) {
            sorted.put(runner.getKey(), runner.getValue());
        }
 
        // Return the sorted HashMap!
        return sorted;
    }
}
