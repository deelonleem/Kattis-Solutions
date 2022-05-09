import java.util.*;

public class peasouppancakes {
	public static void main(String[] args){
		Scanner sc = new Scanner(System.in);
		int n = Integer.parseInt(sc.nextLine()); // Number of restaurants

		for(int i = 0; i < n; i++){ // Iterate through n restaurants
			Boolean pancake = false;
			Boolean pea = false;
			int k = Integer.parseInt(sc.nextLine()); // Number of menu items
			String name = sc.nextLine();

			for (int j = 0; j < k; j++){ // Iterate through k items of ith restaurant
				String item = sc.nextLine();
				if (item.equals("pancakes")){
					pancake = true;
				}
				else if (item.equals("pea soup")){
					pea = true;
				}
			}
			// At this point, should have iterated through menu of ith restaurant
			if (pancake && pea){
				System.out.println(name);
				return;
			}
		}
		// If done with everything 
		System.out.println("Anywhere is fine I guess");
	}
}