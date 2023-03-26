package ConcurrentHashMap;

import java.util.HashMap;
import java.util.Map;
import java.util.Map.Entry;
import java.util.concurrent.ConcurrentHashMap;

public class ConcurrentHashMapTest {
	
	public static void FillTable(Map<Integer, String> hashTable) {
		
		
		
		hashTable.put(1, "Value1");
		hashTable.put(2, "Value2");
		hashTable.put(3, "Value3");
		hashTable.put(4, "Value4");
	}
	
	public static void FillTable2(Map<Integer, String> table) {
		table.put(1, "Value1");
		table.put(2, "Value2");
		table.put(3, "Value3");
		table.put(4, "Value4");
	}
	

	public static void ShowTable(Map<Integer, String> hashTable) {
		for (Entry<Integer, String> entry : hashTable.entrySet()) {
			System.out.println(entry.getKey() + " = " + entry.getValue());
		}
	}
	
	public static void main(String[] args) {
		ConcurrentHashMap<Integer, String> hashTable = new ConcurrentHashMap<Integer, String>(16, 0.9f, 1);
		Map<Integer, String> newTable = new HashMap<Integer, String>();
		
		ConcurrentHashMapTest.FillTable(hashTable);
		ConcurrentHashMapTest.FillTable2(newTable);

		
		ConcurrentHashMapTest.ShowTable(hashTable);
		
		for (Entry<Integer, String> entry : hashTable.entrySet()) {
			if (false == newTable.containsKey(entry.getKey())) {
				/** Remove element from hash table if there is no such element in database memory table : **/
				hashTable.remove(entry.getKey());
			} else {
				/** Remove element from database memory table is hash table have same equal element : **/
				String value = newTable.get(entry.getKey());
				if (true == value.equals(entry.getValue())) {
					newTable.remove(entry.getKey());
				}
			}
		}
		hashTable.putAll(newTable);
		
		System.out.println("");
		ConcurrentHashMapTest.ShowTable(newTable);

		System.out.println("");
		ConcurrentHashMapTest.ShowTable(hashTable);
	}
}
