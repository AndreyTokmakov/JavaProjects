package Preferences_and_Properties;

import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;
import java.util.Properties;
import java.util.Set;

public class PropertiesTests {
	
	public static void Create_and_Iterate()
	{
		Properties capitals = new Properties();     
		capitals.put("Illinois", "Springfield");
		capitals.put("Missouri", "Jefferson City");
		capitals.put("Washington", "Olympia");
		capitals.put("California", "Sacramento");
		capitals.put("Indiana", "Indianapolis");

		// Show all states and capitals in hashtable.
		Set states = capitals.keySet();   // get set-view of keys
		Iterator itr = states.iterator();
		      
		while (itr.hasNext()) {
			String str = (String) itr.next();
			System.out.println("The capital of " + str + " is " + 
			capitals.getProperty(str) + ".");
		}     
		
		System.out.println();

		// look for state not in list -- specify default
		String str = capitals.getProperty("Florida", "Not Found");
		System.out.println("The capital of Florida is " + str + ".");
	}
	
	public static void Props_From_Map() {
		Map<String, String> map = new HashMap<String, String>();
		for (int i = 0; i< 10; ++i) {
			map.put("key_" + i, "value_" + i);
		}

		Properties properties = new Properties();
		properties.putAll(map);
		
		System.out.println(properties);
	}
	
	public static void main(String args[]) 
	{
		// Create_and_Iterate();
		Props_From_Map();
	}
}
