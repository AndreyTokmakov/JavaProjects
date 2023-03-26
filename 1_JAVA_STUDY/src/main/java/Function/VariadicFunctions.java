package Function;

public class VariadicFunctions {
	
	private static void printArgs(String... strings) {
		for (String string : strings) {
			System.out.print(string + " ");
		}
		System.out.println();
	}

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		
		printArgs("hello");                 // short for printArgs( ["hello"] )
        printArgs("hello", "world");        // short for printArgs( ["hello", "world"] )
	}
}
