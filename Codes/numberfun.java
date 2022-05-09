import java.util.*;

public class numberfun {
	public static void main(String[] args){
		Scanner sc = new Scanner(System.in);
		int i;
		int t = sc.nextInt();

		for (i=0; i<t; i++){
			float a = sc.nextInt();
			float b = sc.nextInt();
			float c = sc.nextInt();

			if (a + b == c)
				System.out.println("Possible");
			else if (a - b == c)
				System.out.println("Possible");
			else if (b - a == c)
				System.out.println("Possible");
			else if (a * b == c)
				System.out.println("Possible");
			else if (a / b == c)
				System.out.println("Possible");
			else if (b / a == c)
				System.out.println("Possible");
			else 
				System.out.println("Impossible");
		}
	}
}