package Hashtable;

import java.util.Hashtable;
import java.util.Iterator;
import java.util.Map;
import java.util.Map.Entry;

public class Hashtable_Tests {
	
	protected void CreateTests() {
		Map<String,String> map = new Hashtable<String,String>();
		for (int i = 0;i < 10; i++) {
			map.put("Key" + i, "Value" + i);
		}
		
		for (Entry<String, String>  entry: map.entrySet()) { 
			String key = entry.getKey(); 
			String value = entry.getValue(); 
			System.out.println("(" + key + ", " + value + ")");
		} 
	} 
	
	protected void Test1() {
        Hashtable<Integer, String> hashtable = new Hashtable<>();
         
        hashtable.put(1,  "A");
        hashtable.put(2,  "B" );
        hashtable.put(3,  "C");
         
        System.out.println(hashtable);

        String value = hashtable.get(1);
        System.out.println(value);
         
        hashtable.remove(3); 
         
        //5. Iterate over mappings
        Iterator<Integer> itr = hashtable.keySet().iterator();
         
        while (itr.hasNext()) {
            Integer key = itr.next();
            String mappedValue = hashtable.get(key);
            System.out.println("Key: " + key + ", Value: " + mappedValue);
        }
	}
	
	protected void Test2() {
        Hashtable<String, String> table = new Hashtable<String, String>();
        table.put("Key1", "Value1");
        table.put("Key2", "Value2");
        table.put("Key3", "Value3");
         
        System.out.println("Size: " + table.size());
        for (Entry<String, String> entry: table.entrySet()) {
        	System.out.println(entry.getKey() + "  " + entry.getValue());
        }
         
        System.out.println("\nContains key 'Key2': " + table.containsKey("Key2"));
        System.out.println("Contains value 'Value3': " + table.containsValue("Value3"));
        
	}
	
	
	public static void main(String[] args) {
		// TODO Auto-generated method stub

		Hashtable_Tests tests = new Hashtable_Tests();
		tests.CreateTests();
		// tests.Test1();
		// tests.Test2();
	}
}
