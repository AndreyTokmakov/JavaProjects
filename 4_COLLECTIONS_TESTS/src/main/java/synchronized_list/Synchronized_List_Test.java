package synchronized_list;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collection;
import java.util.Collections;
import java.util.List;
import java.util.ListIterator;



public class Synchronized_List_Test {
	
	public List<String> getSyncListSample() {
		List<String> originalList = 
				new ArrayList<String>(Arrays.asList("Value1", "Value2","Value3",
													"Value4", "Value5","Value6", "Value7"));
		return Collections.synchronizedList(originalList);	
	}
		
	public void Create() {
		List<String> syncList = getSyncListSample();
		for (final String str: syncList) {
			System.out.println(str);
		}
	}
	
	public void Print_Reverse() {
		List<String> syncList = getSyncListSample();
		ListIterator<String> listIter = syncList.listIterator(syncList.size());
		while (listIter.hasPrevious()) {
			System.out.println(listIter.previous());
		}
	}

	/*************** TEST  **************/
	public static void main(String[] args) throws InterruptedException {
		Synchronized_List_Test tests = new Synchronized_List_Test();
		
		// tests.Create();
		// tests.Print_Reverse();
		// tests.StrListTest();
    }
}
