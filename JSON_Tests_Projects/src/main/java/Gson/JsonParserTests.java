package Gson;

import com.google.gson.Gson;
import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import com.google.gson.JsonParser;
import com.google.gson.reflect.TypeToken;

class ParserTest {
	/** **/
	private final static Gson gson = new Gson();
	
	public void Parse_Test_1()
	{
		String json = "{'id': 1001, "
                + "'firstName': 'Lokesh',"
                + "'lastName': 'Gupta',"
                + "'email': 'howtodoinjava@gmail.com'}";
 
        JsonElement jsonElement = new JsonParser().parse(json);
         
        JsonObject jsonObject = jsonElement.getAsJsonObject();
 
        System.out.println("id = " +  jsonObject.get("id") );
        System.out.println("firstName = " +  jsonObject.get("firstName") );
        System.out.println("lastName = " +  jsonObject.get("lastName") );
        System.out.println("email = " +  jsonObject.get("email") );
	}
	
	public void Parse_Test_2()
	{
		String json = "{'id': 1001, "
                + "'firstName': 'Lokesh',"
                + "'lastName': 'Gupta',"
                + "'email': 'howtodoinjava@gmail.com'}";
 
		JsonObject jsonObject = new Gson().fromJson(json, JsonObject.class);
 
        System.out.println("id = " +  jsonObject.get("id") );
        System.out.println("firstName = " +  jsonObject.get("firstName") );
        System.out.println("lastName = " +  jsonObject.get("lastName") );
        System.out.println("email = " +  jsonObject.get("email") );
	}
}

public class JsonParserTests {
	public static void main(String[] args) 
	{
		ParserTest tests = new ParserTest();
		// tests.Parse_Test_1();
		tests.Parse_Test_2();
	}
}
