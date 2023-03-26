package Gson;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

import lombok.Data;
import lombok.NonNull;

@Data
class Employee {
	@NonNull
	private int id;
	@NonNull
	private String firstName;
	@NonNull
	private String lastName;
	@NonNull
	private String email;
}

class Tester {
	/** **/
	private final static Gson gson = new Gson();
	
	public void Json_To_Object()
	{
		final String jsonString = "{'id':1001, 'firstName':'Lokesh', 'lastName':'Gupta', 'email':'howtodoinjava@gmail.com'}";
		Employee empObject = gson.fromJson(jsonString, Employee.class);
		System.out.println(empObject);
	}
	
	public void Object_To_Json() {
		final Employee emp = new Employee(1001, "Lokesh", "Gupta", "howtodoinjava@gmail.com");
		String jsonString = gson.toJson(emp);
		 
		System.out.println(jsonString);
	}
	
	public void Object_To_Json_Pretty() 
    {
		final Employee employeeObj = new Employee(1, "Lokesh", "Gupta", "howtogoinjava@gmail.com");
                 
        Gson gson = new GsonBuilder().setPrettyPrinting().create(); 
        System.out.println(gson.toJson(employeeObj));
    }
}

public class GsonTests {
	/** Tester class instance: **/
	private final static Tester tester = new Tester();

	public static void main(String[] args) 
	{
		 tester.Json_To_Object();
		// tester.Object_To_Json();
		// tester.Object_To_Json_Pretty();
	}
}
