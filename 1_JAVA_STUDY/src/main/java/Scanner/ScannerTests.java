
package Scanner;
import java.util.Scanner;

public class ScannerTests {
	
	private final static Scanner input = new Scanner(System.in);
	
	public static void main(String[] args) {
		ScannerTests tester = new ScannerTests();
		
		// tester.Read_Int_and_String();
		// tester.Read_Int();
		// tester.ReadLine();
		// tester.Read_with_delimeter();
		// tester.Read_3_Line();
		
		tester.TEST();
	}
	
	
	public void ReadLine() {
		System.out.println("Enter your username: ");
		String username = input.nextLine();
		System.out.println("Your username is " + username);
	}
	
	public void Read_Int() {
		int num1 = input.nextInt();
		int num2 = input.nextInt();
		
		System.out.println(num1 + " " + num2);
	}

	
	public void Read_Int_and_String() {
		int num = input.nextInt();
		System.out.println("Num = " + num);
		
		input.skip("\\R");
		
		String line = input.nextLine();
		System.out.println("String: " + line);
		
		/*
		while(0 != num--)
		{
			String line = input.nextLine();
			System.out.println("Result: " + line);
		}*/
	}
	
	public void Read_with_delimeter() {
		Scanner sc = new Scanner(System.in).useDelimiter("\\s");
		String name = sc.next();  // It will not leave until the user enters data.
		int number = sc.nextInt(); // We can read specific data.
		System.out.println("Name " + name + "\t number " + number);
	}
	
	public void Read_3_Line() {
		String line1 = input.nextLine();
		String line2 = input.nextLine();
		String line3 = input.nextLine();
		
		System.out.println("String: " + line3);
		System.out.println("Double: " + Double.parseDouble(line2));
		System.out.println("Int: " + Integer.parseInt(line1));

	}
	
	public void TEST() {
        try {
            int a = input.nextInt();
            int b = input.nextInt();
            int c = a / b;
        }
        catch (final Exception exc) {
            System.out.println(exc.getClass());
        }
 
	}
}
