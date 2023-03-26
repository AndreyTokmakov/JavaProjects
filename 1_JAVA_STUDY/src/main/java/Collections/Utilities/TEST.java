package Collections.Utilities;

public class TEST {
	
	protected static void Person_Test() {
		Person bob  = new Person(1, "Bob");
		Person jonh = new Person(2, "Jonh");
		Person jonh1 = new Person(2, "Jonh");
		
		System.out.println("Equals: " + jonh.equals(bob));
		System.out.println("Equals: " + jonh.equals(jonh1));
		System.out.println("Equals: " + (jonh == jonh1));
	}

	public static void main(String[] args) {
		Person_Test();
	}
}
