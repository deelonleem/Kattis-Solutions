import java.util.*;

public class detaileddiff {
	public static void main(String[] args){
		Scanner sc = new Scanner(System.in);

		int n = sc.nextInt();
		int i, j, length;

		for (i=0; i<n; i++){
			String line1 = sc.next();
			String line2 = sc.next();
			String result = "";
			length = line1.length();
			for (j=0; j<length; j++){
				if (line1.charAt(j) == line2.charAt(j))
					result += ".";
				else 
					result += "*";
			}
		System.out.println(line1);
		System.out.println(line2);
		System.out.println(result);
		System.out.println();
		}
	}
}