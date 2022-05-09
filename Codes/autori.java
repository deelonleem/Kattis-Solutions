import java.util.*;

public class autori {
	public static void main(String[] args){
		Scanner sc = new Scanner(System.in);

		String result = ""; // Variable to hold final result
		String input;
		
		input = sc.nextLine();
		String arr[] = input.split("-"); // Splits input by "-", puts names in an array

		for (String str : arr)
			result += str.charAt(0); // Concatenate first element of every element in arr

		System.out.println(result);

	}
}