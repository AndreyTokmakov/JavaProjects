package Utils_Functions.Supplier;

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
			System.out.print("Enter name : ");
			String name = in.nextLine();
			return new User(name);
		};
		
		User user1 = userFactory.get();
		User user2 = userFactory.get();
		
		System.out.println("Name user1: " + user1.getName());
		System.out.println("Name user2: " + user2.getName());  
	}
	
	public static void main(String[] args) {
		SupplierTestClass tests = new SupplierTestClass();
		tests.TEST();
	}
}
