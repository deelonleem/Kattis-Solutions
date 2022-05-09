import java.io.*;
import java.util.*;

public class coconutsplat {
	public static void main(String[] args){
		// Initilisation 
		Scanner sc = new Scanner(System.in);
        PrintWriter pw = new PrintWriter(new BufferedWriter(new OutputStreamWriter(System.out)));

        // Setup of the game
        int syllables = sc.nextInt();
        int players = sc.nextInt();
        int[] array = new int[players*2];
        for (int i =0; i<array.length; i++){
        	if (i%2==0) {array[i] = 3;} // set all even indices to 3
        	else {array[i] = 0;} // Set all odd indices to 0
        }

        // Main loop
        // States: 0 = Out, 1 = Palm, 2 = Fist, 3 = Folded hands
        int index = 0;
        while(nonzero(array)>1){
        	int counts = 0;
        	while (counts != syllables){ // Restarts the moment counts = syllables

        		// Wraparound effect for index if it is beyond array length
        		if (index >= array.length){index = index%(array.length);}

        		// Case 1: If element is out of the game (0)
        		if (array[index] == 0){index += 1;}

        		// Case 2: If element is folded hands (3)
        		else if (array[index] == 3){
        			if (counts == syllables-1){ // If the final count lands on this hand, split into 2 fists
        				array[index] = 2;
        				array[index+1] = 2;
        			} 
        			else {index += 2;} // Else, skip the next hand!
        			counts += 1;
        		}

        		// Case 3: If element is a fist (2)
        		else if (array[index] == 2){
        			if (counts == syllables-1){ // If the final count lands on this hand, turn it into a palm
        				array[index] = 1;
        			}
        			index += 1;
        			counts += 1;
        		}

        		// Case 4: If element is a palm (1)
        		else if (array[index] == 1){
        			if (counts == syllables-1){ // If the final count lands on this hand, remove it from game.
        				array[index] = 0;
        			}
        			index += 1;
        			counts += 1;
        		}
        	}
        }

        // Find the final nonzero index, floor divide it by 2, then print it. 
        int result = 0;
		for (int i = 0; i<array.length; i++){
			if (array[i] != 0){result = i;}
		}
		pw.println(Math.floorDiv(result, 2) + 1);
		pw.close();
	}

	public static int nonzero(int[] arr){ // Helper function to count number of nonzero elements in a list
		int count = 0;
		for (int i = 0; i < arr.length; i++){
			if (arr[i] != 0){count += 1;}
		}
		return count;
	}
}