package Strings;

public class StringFormating
{

	public static void main(String[] args)
	{
		System.out.println(String.format("|%s|", "Hello World"));
		System.out.println(String.format("|%30s|", "Hello World"));
		System.out.println(String.format("|%-30s|", "Hello World"));
		System.out.println(String.format("|%.5s|", "Hello World"));
		System.out.println(String.format("|%30.5s|", "Hello World"));
		
		System.out.println("\n=======================================================\n");
		
		String title = "Effective Java";
		float price = 33.953f;
		System.out.format("%s is a great book. It is sold at %.2f USD today.%n", title, price);

		System.out.println("\n=======================================================\n");
		
		System.out.format("Min value of a byte: %d (%1$#x)%n", Byte.MIN_VALUE);
		System.out.format("Max value of a byte: %d (%1$#x)%n", Byte.MAX_VALUE);
		System.out.format("Min value of an int: %d (%1$x)%n", Integer.MIN_VALUE);
		System.out.format("Max value of an int: %d (%1$x)%n", Integer.MAX_VALUE);

		System.out.println("\n=======================================================\n");
		
		String specifiers = "%-30s %-20s %-5.2f%n";
		System.out.format(specifiers, "Head First Java", "Kathy Sierra", 23.99f);
		System.out.format(specifiers, "Thinking in Java", "Bruce Eckel", 25.69f);
		System.out.format(specifiers, "Effective Java", "Joshua Bloch", 27.88f);
		System.out.format(specifiers, "The Passionate Programmer", "Chad Fowler", 41.99f);
		System.out.format(specifiers, "Code Complete", "Steve McConnell", 38.42f);

		System.out.println("\n=======================================================\n");
		
		
		System.out.format("%05d %n", 12);
		System.out.format("%05d %n", 2016);
		System.out.format("%05d %n", 365);
		System.out.format("%05d %n", 19001800);
		System.out.format("%(d %n", -1234);
		System.out.format("%+d %n", 567);
		System.out.format("%+d %n", -891);
		
	
	}
}
