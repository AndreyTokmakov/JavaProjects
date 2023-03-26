package Gson;

import java.util.HashSet;
import java.util.Set;

import java.lang.reflect.Type;
import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

class SetJsonTester {
	/** **/
	private final static Gson gson = new Gson();
	
	public void Serialize_To_JSON()
	{
		Set<String> userSet = new HashSet<>();
		userSet.add("Alex");
		userSet.add("Brian");
		userSet.add("Charles");
		 
		String jsonString = gson.toJson(userSet);  
		System.out.println(jsonString);
	}
	
	public void Json_To_Set() {
		String jsonString = "['Alex','Brian','Charles','Alex']";
		 
		Type setType = new TypeToken<HashSet<String>>(){}.getType();
		Set<String> userSet = gson.fromJson(jsonString, setType);  
		System.out.println(userSet);
	}
}

public class Set_JSON_Tests {
	public static void main(String... strings) {
		SetJsonTester tests = new SetJsonTester();
		// tests.Serialize_To_JSON();
		tests.Json_To_Set();
	}
}
