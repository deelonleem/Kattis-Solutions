import java.io.*;
import java.util.*;

public class t9_spelling {
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        PrintWriter pw = new PrintWriter(new BufferedWriter(new OutputStreamWriter(System.out)));

        HashMap<String, String> hashmap = new HashMap<String, String>(); // HashMap to store key-value pairs
        hashmap.put("a", "2");
        hashmap.put("b", "22");
        hashmap.put("c", "222");
        hashmap.put("d", "3");
        hashmap.put("e", "33");
        hashmap.put("f", "333");
        hashmap.put("g", "4");
        hashmap.put("h", "44");
        hashmap.put("i", "444");
        hashmap.put("j", "5");
        hashmap.put("k", "55");
        hashmap.put("l", "555");
        hashmap.put("m", "6");
        hashmap.put("n", "66");
        hashmap.put("o", "666");
        hashmap.put("p", "7");
        hashmap.put("q", "77");
        hashmap.put("r", "777");
        hashmap.put("s", "7777");
        hashmap.put("t", "8");
        hashmap.put("u", "88");
        hashmap.put("v", "888");
        hashmap.put("w", "9");
        hashmap.put("x", "99");
        hashmap.put("y", "999");
        hashmap.put("z", "9999");
        hashmap.put(" ", "0");

        int n = Integer.parseInt(br.readLine()); // Number of sentences

        for (int i = 0; i < n; i++) { // Looping over number of sentences
        	String sentence = br.readLine();
        	int length = sentence.length(); // Length of ith sentence
      		String prev = " "; // Variable to hold prev number 
        	String result = "Case #" + (i+1) + ": "; // Final output
        	for (int j = 0; j < length; j++) { // Looping over length of ith sentence
        		String letter = sentence.substring(j, j+1); 
        		String value = hashmap.get(letter);
        		if (value.substring(0,1).equals(prev)) { // Space is required
        			result += " ";
        			result += value;
        		}
        		else { // No need space
        			result += value;
        		}
        		prev = value.substring(0,1); // Only the first number is required
        	}
			pw.println(result);
        }
		pw.close();
	}
}