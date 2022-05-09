import java.io.*;
import java.util.*;

public class cardtrading {
	public static void main(String[] args){
		// Initilisation
		Scanner sc = new Scanner(System.in);
        PrintWriter pw = new PrintWriter(new BufferedWriter(new OutputStreamWriter(System.out)));

        // Obtaining Information, setting up initial conditions - Tested to be working!
        int N = sc.nextInt();
        int T = sc.nextInt();
        int K = sc.nextInt();

        // For all these arrays: index = type, value = number of that type of card/respective prices
        List<Integer> deck = new ArrayList<Integer>(T); // Anthony's deck
 		List<Integer> buy = new ArrayList<Integer>(T); // Buy prices
 		List<Integer> sell = new ArrayList<Integer>(T); // Sell prices
 		List<Integer> ratio = new ArrayList<Integer>(T); // Ratio of sell - buy
        for (int i = 0; i < T; i ++){ // Add T 0s into all 3 lists.
         	deck.add(0);
        	buy.add(0);
        	sell.add(0);
        	ratio.add(0);
        }
        for (int i = 0; i < N; i++){ // Add the relevant types of cards into the deck. 
        	int temp = sc.nextInt();
        	int value = deck.get(temp-1);
        	deck.set(temp-1, value+1);
        }

        long networth = 0;
        for (int i = 0; i < T; i++){ // Iterate T more times for buy/sell prices
        	int buyprice = sc.nextInt();
        	int sellprice = sc.nextInt();
        	buy.set(i, buyprice);
        	sell.set(i, sellprice);

        	// Start calculating ratio 
        	if (deck.get(i) == 0){ // If you have 0 copies
        		int earning = 0;
        		int spending = (-2*buy.get(i));
        		ratio.set(i, spending-earning);
        		networth += earning;
        	}
        	else if (deck.get(i) == 1){ // If you have 1 copy
        		int earning = sell.get(i);
        		int spending = -buy.get(i);
        		ratio.set(i, spending-earning);
        		networth += earning;
        	}
        	else { // If you have 2 copies already
        		int earning = (2*sell.get(i));
        		int spending = 0;
        		ratio.set(i, spending-earning);
        		networth += earning;
        	}
        }
        Collections.sort(ratio);
        long profit = networth;
        for (int j = T-K; j<T; j++){
        	profit += ratio.get(j);
        }

        pw.println(profit);
        pw.close();
    }
}
