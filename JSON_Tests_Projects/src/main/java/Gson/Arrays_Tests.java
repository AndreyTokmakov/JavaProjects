package Gson;

import com.google.gson.Gson;
import lombok.Data;
import lombok.NonNull;

import java.lang.reflect.Type;
import java.util.ArrayList;
import com.google.gson.reflect.TypeToken;

@Data
class User {
	@NonNull private long id;
	@NonNull private String name;
}

@Data
class Department {
	@NonNull private long id;
	@NonNull private String name;
	@NonNull private User[] users;
}

class ArrayTester {
	/** **/
	private final static Gson gson = new Gson();
	
	public void Json_To_ObjectArray()
	{
		String userJson = "[{'name': 'Alex','id': 1}, "
                + "{'name': 'Brian','id':2}, "
                + "{'name': 'Charles','id': 3}]";
		User[] userArray = gson.fromJson(userJson, User[].class);  
		 
		for(User user : userArray) {
		    System.out.println(user);
		}
	}

	public void Json_To_ObjectList()
	{
		String userJson = "[{'name': 'Alex','id': 1}, "
                + "{'name': 'Brian','id':2}, "
                + "{'name': 'Charles','id': 3}]";
		
		Type userListType = new TypeToken<ArrayList<User>>(){}.getType();
		ArrayList<User> userArray = gson.fromJson(userJson, userListType); 
		
		for(User user : userArray) {
		    System.out.println(user);
		}
	}
	
	public void Json_To_Object_Complex() {
		String departmentJson = "{'id' : 1, "
		        + "'name': 'HR',"
		        + "'users' : ["
		            + "{'name': 'Alex','id': 1}, "
		            + "{'name': 'Brian','id':2}, "
		            + "{'name': 'Charles','id': 3}]}";
		 
		Department department = gson.fromJson(departmentJson, Department.class);   
		System.out.println(department);
	}
}
    
public class Arrays_Tests {

	public static void main(String[] args)
	{
		ArrayTester tests = new ArrayTester();

		// tests.Json_To_ObjectArray();
		// tests.Json_To_ObjectList();
		tests.Json_To_Object_Complex();
	}
}
