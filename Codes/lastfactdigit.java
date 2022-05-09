import java.util.*;

public class lastfactdigit {

	public static int factorial(int n){
		int result=1; 
		int i;

		for (i=1; i<n+1; i++)
			result = result * i;
		return result; 
	}

	public static void main(String[] args){
		Scanner sc = new Scanner(System.in);

		int t = sc.nextInt();
		int i, fact, result;

		for (i=0; i < t; i++){
			int n = sc.nextInt();
			fact = factorial(n);
			result = fact%10;

			System.out.println(result);
		}

	}
}