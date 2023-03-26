package FunctionalInterface.Supplier;

import java.util.Scanner;
import java.util.function.Supplier;

class User {
	private String name;
	
	public User(String n){
		this.name =n ;
	}	
	public String getName() {
		return name;
	}
}

public class SupplierTestClass {
	
	public void TEST() {
		final Supplier<User> userFactory = ()->{
			Scanner in = new Scanner(System.in);
			System.out.println("Enter name : ");
			String name = in.nextLine();
			return new User(name);
		};
		
		User user1 = userFactory.get();
		User user2 = userFactory.get();
		
		System.out.println("Name user1: " + user1.getName());
		System.out.println("Name user2: " + user2.getName());  
	}
	
	public void TEST2() {
		String t = "One";
		Supplier<String> supplierStr = () -> t.toUpperCase();
		System.out.println(supplierStr.get());
	}
	
	public static void main(String[] args) {
		SupplierTestClass tests = new SupplierTestClass();
		
		// tests.TEST();
		tests.TEST2();	
	}
}
