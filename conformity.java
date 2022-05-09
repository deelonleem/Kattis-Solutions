import java.io.*;
import java.util.*;

public class conformity {
	public static void main(String[] args){
		// Initilisation 
		Scanner sc = new Scanner(System.in);
        PrintWriter pw = new PrintWriter(new BufferedWriter(new OutputStreamWriter(System.out)));
        int n = sc.nextInt(); // n number of lines of data 
        HashMap<String, Integer> results = new HashMap<String, Integer>();

        // Data collection - change combinations into strings, which will be put into a hashmap
        for (int i = 0; i < n; i++){
        	ArrayList<Integer> combinations = new ArrayList<Integer>();
        	for (int j = 0; j < 5; j++){ // 5 Courses for each line
        		combinations.add(sc.nextInt());
        	}
        	Collections.sort(combinations); // Sort the array
        	String string = combinations.toString();
        	if (results.containsKey(string)){ // If combination exists, incremenent counter
        		int count = results.get(string);
        		results.put(string, count+1);
        	}
        	else { // If combination does not exist, create it in the HashMap
        		results.put(string, 1);
        	}
        }

        int max = 0; // To hold max value
        int sum = 0; // To hold sum 
        for (int count : results.values()){ // Iterate through values of HashMap
        	if (count > max){
        		max = count;
        		sum = count;
        	}
        	else if (count == max){
        		sum += count;
        	}
        }
		pw.println(sum);
		pw.close();
	}
}