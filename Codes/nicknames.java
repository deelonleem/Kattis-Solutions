import java.io.*;
import java.util.*;

public class nicknames {
	public static void main(String[] args) {
		FastRead sc = new FastRead();
        PrintWriter pw = new PrintWriter(new BufferedWriter(new OutputStreamWriter(System.out)));
        int a = sc.nextInt(); // Number of names
        BST tree = new BST();
        for (int i = 0; i < a; i++){
        	tree.insert(sc.next()); // Insert names into tree
        }
        int b = sc.nextInt(); // Number of nicknames
        HashMap<String, Integer> hm = new HashMap<String, Integer>(); // hm to store nicknames since they aren't unique
        for (int i = 0; i < b; i++){
        	String nick = sc.next();
        	if (hm.containsKey(nick)){pw.println(hm.get(nick));} // If hashmap contains nickname already
        	else { // If it is a new unique nickname
        		hm.put(nick, tree.count(nick));
        		pw.println(hm.get(nick));
        	}
        }
		pw.close();
	}

	// Helper function to determine if string nick is indeed a nickname of string name
	public static boolean isNickname(String name, String nick){
		if (name.indexOf(nick) != 0) {return false;}
		return true; // If passes both conditions, it is a nickname!
	}

	// Adapted from lecture notes for strings instead of int
	static class BSTVertex {
		BSTVertex(String v) { key = v; parent = left = right = null; height = 0; }
	  	// all these attributes remain public to slightly simplify the code
	  	public BSTVertex parent, left, right;
	  	public String key;
	  	public int height; 
	}

	// Adapted from lecture notes, with some new methods
	static class BST {
		public BSTVertex root;

		public BST() {root = null;}

		// public method called to insert a new key with value v into BST
  		public void insert(String v) { root = insert(root, v); }

  		// helper recursive method to perform insertion of new vertex into BST
  		// have to utilise compareTo since we are comparing strings
  		// Also check for balancing as this is now an AVL!
  		public BSTVertex insert(BSTVertex T, String v) {
    		if (T == null) return new BSTVertex(v); // insertion point is found

		    if (T.key.compareTo(v) < 0) { // search to the right
		      T.right = insert(T.right, v);
		      T.right.parent = T;
		    }
		    else { // search to the left
		      T.left = insert(T.left, v);
		      T.left.parent = T;
		    }
            T.height = Math.max(getHeight(T.left), getHeight(T.right)) + 1;
            if (Math.abs(getHeight(T.left) - getHeight(T.right)) <= 1) {return T;} // Already balanced, no need to balance
            else { return balanceTree(T);}
		}  

		// Returns height of tree
		int getHeight(BSTVertex T) { 
			if (T == null){ return -1;}
			else {return T.height;}
		}

		// public method called to count number of nicknames 
		int count(String nick) { return count(root, nick); }

		// helper recursive method to count number of nicknames
        int count(BSTVertex T, String nick){
            int n = 0;
            try { // To circumvent nullpointexception
                if (isNickname(T.key, nick)){
                    n += 1;
                    n += count(T.left, nick);
                    n += count(T.right, nick);
                }
                else if (T.key.compareTo(nick) < 0){
                    n += count(T.right, nick);
                }
                else {
                    n += count(T.left, nick);
                }
            }
            catch (NullPointerException e){return n;}
            return n;
        }

		// method that takes in an unbalanced tree and returns it balanced.
        public BSTVertex balanceTree(BSTVertex T) {
            if (getHeight(T.right) - getHeight(T.left) > 1) { // Balance factor = +2
                if (getHeight(T.right.right) > getHeight(T.right.left)) T = leftRotate(T); 
                else {
                    T.right = rightRotate(T.right);
                    T = leftRotate(T);
                }
            } else { // Balance factor = -2
                if (getHeight(T.left.left) > getHeight(T.left.right)) T = rightRotate(T);
                else {
                    T.left = leftRotate(T.left);
                    T = rightRotate(T);
                }
            }
            return T; 
        }

		//leftRotate and rightRotate implemented using lecture's pseudocode
		public BSTVertex leftRotate(BSTVertex T){
			BSTVertex w = T.right;
			w.parent = T.parent;
			T.parent = w;
			T.right = w.left;
			if (w.left != null) {w.left.parent = T;}
			w.left = T;
			T.height = Math.max(getHeight(T.left), getHeight(T.right)) + 1;
			w.height = Math.max(getHeight(w.left), getHeight(w.right)) + 1;
			return w;
		}

		public BSTVertex rightRotate(BSTVertex T) {
            BSTVertex w = T.left;
            w.parent = T.parent;
            T.parent = w;
            T.left = w.right;
            if (w.right != null) {w.right.parent = T;}
            w.right = T;
            T.height = Math.max(getHeight(T.left), getHeight(T.right)) + 1;
            w.height = Math.max(getHeight(w.left), getHeight(w.right)) + 1;
            return w;
        }
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
	}
}