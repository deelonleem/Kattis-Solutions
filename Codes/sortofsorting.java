import java.io.*;
import java.util.*;

public class sortofsorting {
	public static void main(String[] args){
		// Initilisation 
		Scanner sc = new Scanner(System.in);
        PrintWriter pw = new PrintWriter(new BufferedWriter(new OutputStreamWriter(System.out)));

        // Main body
        while(true){
        	int n = Integer.parseInt(sc.nextLine()); // Number of names for this case
        	if (n == 0) break; // Exit clause 
        	List<Name> names = new ArrayList<>(200);
        	for (int i = 0; i < n; i++){
        		String studentname = sc.nextLine();
        		Name student = new Name(studentname);
        		names.add(student);
        	}
        	Collections.sort(names);

        	for (Name student: names){
        		pw.println(student.name);
        	}
        	pw.println();
        }
		pw.close();
	}

	public static class Name implements Comparable<Name>{
		String name, subname;

		public Name(String s){
			name = s;
			subname = s.substring(0,2);
		}

		public int compareTo(Name x){
			return this.subname.compareTo(x.subname);
		}
	}
}