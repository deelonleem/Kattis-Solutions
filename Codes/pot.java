import java.util.*;
import java.lang.Math;

public class pot {
	public static void main(String[] args){
		Scanner sc = new Scanner(System.in);

		int i, power, num;
		int input;
		int result = 0; 
		int n = sc.nextInt();

		for (i=0; i<n; i++){
			input = sc.nextInt();
			power = input%10;
			num = input/10;
			result += Math.pow(num,power);
			}
		System.out.println(result);
	}
}