import java.io.*;
import java.util.*;

public class teque {
	public static void main(String[] args) {
		FastRead fr = new FastRead();
		PrintWriter pw = new PrintWriter(new BufferedWriter(new OutputStreamWriter(System.out)));
		int n = fr.nextInt(); // n number of commands
		StringBuilder string = new StringBuilder();

		// Setup of both hashmaps - one for front half, one for back half
		HashMap<Integer, Integer> front = new HashMap<Integer, Integer>();
		int front_head = -1; // Trackers for head/tail for front half
		int front_tail = 0; 
		HashMap<Integer, Integer> back = new HashMap<Integer, Integer>();
		int back_head = -1; // Trackers for head/tail for back half
		int back_tail = 0; 

		// Main loop over n commands 
		for (int i = 0; i<n; i++){
			String command = fr.next();
			int item = fr.nextInt();

			// First method: Push back 
			if (command.equals("push_back")){
				back.put(back_tail, item);
				back_tail += 1;
			}

			// Second method: Push front
			else if (command.equals("push_front")){
				front.put(front_head, item);
				front_head -= 1;
			}

			// Third method: Push middle
			else if (command.equals("push_middle")){
				front.put(front_tail, item);
				front_tail += 1;
			}

			// Fourth method: Get
			else if (command.equals("get")){
				if (item < front.size()){ // Can be found in front half
					string.append(front.get(item+front_head+1));
				}
				else { // Can be found in back half
					string.append(back.get(item-front.size()+back_head+1));
				}
				string.append("\n"); // Creates a newline for next append
			} 

			// Check the sizes here. We want front to always be smaller by 1 or both equal. 
			if (back.size() > front.size()){ // If back is larger than front
				front.put(front_tail, back.get(back_head+1));
				front_tail += 1;
				back_head += 1;
				back.remove(back_head);
			}
			else if (front.size() > back.size()+1){ // If front is larger by 2 than back
				back.put(back_head, front.get(front_tail-1));
				back_head -= 1;
				front_tail -= 1;
				front.remove(front_tail);
			}
		}
		pw.println(string.toString());
		pw.close();
	}

	public static class FastRead{ // Class to optimise next(), nextLine() and nextInt().
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

		String nextLine(){ // Optimised nextLine();
            String result = "";
            try {
                result = br.readLine();
            }
            catch (IOException e) {
                e.printStackTrace();
            }
            return result;
        }
	}
}