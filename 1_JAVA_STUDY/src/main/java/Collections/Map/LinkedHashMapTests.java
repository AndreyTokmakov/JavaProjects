package Collections.Map;

import java.util.LinkedHashMap;
import java.util.Map;
import java.util.Map.Entry;

class Tests {

	protected void CreateTests() {
		Map<String,String> map = new LinkedHashMap<String,String>();
		for (int i = 0;i < 10; i++) {
			map.put("Key" + i, "Value" + i);
		}
		
		for (Entry<String, String>  entry: map.entrySet()) { 
			String key = entry.getKey(); 
			String value = entry.getValue(); 
			System.out.println("(" + key + ", " + value + ")");
		} 
	} 
}

public class LinkedHashMapTests {
	private static final Tests tests = new Tests();
	
	public static void main(String[] args) {
		tests.CreateTests();
	}
}
