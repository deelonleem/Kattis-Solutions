import java.io.*;
import java.util.*;

public class joinstrings {
	public static void main(String[] args) {
		FastRead fr = new FastRead();
		PrintWriter pw = new PrintWriter(new BufferedWriter(new OutputStreamWriter(System.out)));
		int N = fr.nextInt(); // N number of strings
		ArrayList<TailedLinkedList> array = new ArrayList<TailedLinkedList>(); 
		// Main concept is to keep an array of tailedlinkedlists that represent each string.

		if (N == 1){ // If N = 1, return the next input string and that's it.
			pw.println(fr.nextLine());
		} 

		else { // If N != 1 (all other normal cases)
			for (int i = 0; i < N; i++){ // Data collection, put all strings into the main tailedlinkedlist. 
				TailedLinkedList string = new TailedLinkedList();
				string.addItem(fr.nextLine()); // Convert each string to a linked list.
				string.addItem("");
				array.add(string); // Normal array add method
			}
			for (int i = 0; i < N-2; i++){ // Doing the N-2 operations, leaving out the last one!
				TailedLinkedList Sa = array.get(fr.nextInt()-1);
				TailedLinkedList Sb = array.get(fr.nextInt()-1);
				Sa.concat(Sb); // Concatenate both strings
			}
			StringBuilder last = new StringBuilder(array.get(fr.nextInt()-1).toString()); // Handles last input
        	pw.println(last.append(array.get(fr.nextInt()-1).toString())); // Print final result.
		}
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

	static class ListNode { // Adapted from Lecture 5's ListNode.java
	// Getters
	public String item;
	public ListNode next;

	// Setters 
	public ListNode(String val) { this(val, null); }
	public ListNode(String val, ListNode n) { 
		item = val; 
		next = n; 
	}

	// Methods
	public ListNode getNext() { return next; }
	public String getItem() { return item; }
  	public void setItem(String val) { item = val; }
	public void setNext(ListNode n) { next = n; }
	}

	static class TailedLinkedList { // Adapted from Lecture 5's TailedLinkedList.java
		// Attributes
        public ListNode head;
        public ListNode tail;
        public int num_nodes;

        // Methods
        public int size() { return num_nodes; }
        public ListNode getHead() { return head; }
        public ListNode getTail() { return tail; }

        // Insert item into index n
        public void insert (ListNode cur, ListNode n) {
            n.setNext(head);
            head = n;
            if (tail == null) tail = head;
            num_nodes++;
        }

        // Add to new item to the back
        public void addItem(String item) { 
            ListNode newNode = new ListNode(item);
            insert(null, newNode);
        }

        // Concat two tailed linked lists together
        public void concat(TailedLinkedList l) {
            tail.setNext(l.getHead());
            tail = l.tail;
            num_nodes += l.num_nodes;
        }

        @Override // Overriding Java's inbuilt toString() method.
        public String toString() {
            ListNode cur = head;
            StringBuffer result = new StringBuffer("");
            for (int i = 1; i < num_nodes; i++) {
                cur = cur.getNext();
                result.append(cur.getItem());
            }
            return result.toString();
        }
    }


}