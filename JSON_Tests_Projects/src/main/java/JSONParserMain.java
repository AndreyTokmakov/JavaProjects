 
import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;

import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.Iterator;
import java.util.Set;

import org.json.*;

/** @author AndTokm **/
public class JSONParserMain {
	/**  @param args **/
	public static void main(String[] args) {

		// Test4();
		
		Parse_File();
	}
	
	public static void Parse_File()
	{
		String filePath = "S:\\Temp\\JSON\\Test.json";
		try {
			 FileReader reader = new FileReader(filePath);
			 JSONParser jsonParser = new JSONParser();
			 
			 JSONObject jsonObject = (JSONObject)jsonParser.parse(reader);
			 JSONArray jsonArray = (JSONArray)jsonObject.get("items");
			 
			 // JSONArray jsonArray = (JSONArray)jsonParser.parse(reader);

			 
			 System.out.println(jsonArray);
		} 
		catch (IOException | ParseException | NullPointerException | ClassCastException ex) {
			ex.printStackTrace();
		}
	}
	
	public static void Test4()
	{
		String filePath = "S:\\Temp\\JSON\\disp.json";
		try {
			 FileReader reader = new FileReader(filePath);
			 JSONParser jsonParser = new JSONParser();
			 JSONArray jsonArray = (JSONArray)jsonParser.parse(reader);

			 for (int i = 0; i < jsonArray.size(); i++) {
				 JSONObject target = null;
				 try {
					 target = (JSONObject)jsonArray.get(i);
				 } catch (ClassCastException exc) { // This only could happen when we trying to parse version object
					 System.out.println("Version = " + jsonArray.get(i));
					 continue;
				 }
				 
				 String ip = (String)target.get("ip");
				 String name = (String)target.get("name");
				 System.out.println("ip = " + ip + ", name = " + name);
		     }
		} 
		catch (FileNotFoundException ex) {
			ex.printStackTrace();
		} 
		catch (IOException ex) {
			ex.printStackTrace();
		} 
		catch (ParseException ex) {
			ex.printStackTrace();
		} 
		catch (NullPointerException ex) {
			ex.printStackTrace();
		} 
	}
	
	
	public static void Test3()
	{
		String filePath = "C:\\Temp\\json.json";
		try {
			 FileReader reader = new FileReader(filePath);
			 JSONParser jsonParser = new JSONParser();
			 JSONObject jsonObject = (JSONObject)jsonParser.parse(reader);
			 
			 String firstName = (String)jsonObject.get("firstname");
			 System.out.println("firstName : " + firstName);
			 
			 long id = (long)jsonObject.get("id");
			 System.out.println("id : " + id);
			 
			 JSONArray lang = (JSONArray)jsonObject.get("languages");
			 for (int i = 0; i < lang.size(); i++) {
				  JSONObject innerObj = (JSONObject)lang.get(i);
				  String language = (String)innerObj.get("lang");
				  String knowledge = (String)innerObj.get("knowledge");
				  System.out.println("languages[" + i + "] : language = "+ language + ", knowledge = " + knowledge);
			 }
	
			 JSONObject structure = (JSONObject)jsonObject.get("job");
			 String site = (String)structure.get("site");
			 String name = (String)structure.get("name");
			 System.out.println("Job : site = " + site + ", name = " + name);
		} 
		catch (FileNotFoundException ex) {
			ex.printStackTrace();
		} catch (IOException ex) {
			ex.printStackTrace();
		} catch (ParseException ex) {
			ex.printStackTrace();
		} catch (NullPointerException ex) {
			ex.printStackTrace();
		} 
	}
	

	public static void Test1()
	{
		String filePath = "C:\\Temp\\json.json";
		try {
			 FileReader reader = new FileReader(filePath);
			 JSONParser jsonParser = new JSONParser();
			 JSONObject jsonObject = (JSONObject)jsonParser.parse(reader);
			 
			 String firstName = (String)jsonObject.get("firstname");
			 System.out.println("firstName : " + firstName);
			 
			 long id = (long)jsonObject.get("id");
			 System.out.println("id : " + id);
			 
			 JSONArray lang = (JSONArray)jsonObject.get("languages");
			 for (int i = 0; i < lang.size(); i++) {
				  System.out.println("languages[" + i + "] : " + lang.get(i));
			 }
			 
			 Iterator i = lang.iterator();
			 
			 // take each value from the json array separately
			 while (i.hasNext()) {
				 JSONObject innerObj = (JSONObject) i.next();
				 System.out.println("language "+ innerObj.get("lang") + " with level " + innerObj.get("knowledge"));
			 }
			 // handle a structure into the json object
			 JSONObject structure = (JSONObject) jsonObject.get("job");
			 System.out.println("Into job structure, name: " + structure.get("name"));
		} 
		catch (FileNotFoundException ex) {
			ex.printStackTrace();
		} catch (IOException ex) {
			ex.printStackTrace();
		} catch (ParseException ex) {
			ex.printStackTrace();
		} catch (NullPointerException ex) {
			ex.printStackTrace();
		} 
	}
	
	public static void Test2() {
		// TODO Auto-generated method stub
		
		/*JSONObject obj = new JSONObject(" .... ");
		String pageName = obj.getJSONObject("pageInfo").getString("pageName");

		JSONArray arr = obj.getJSONArray("posts");
		for (int i = 0; i < arr.length(); i++)
		{
		    String post_id = arr.getJSONObject(i).getString("post_id");
		}*/
	}
	
}
