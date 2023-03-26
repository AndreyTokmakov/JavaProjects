package Collections.CopyOnWriteArrayList;

import java.util.Arrays;
import java.util.Comparator;
import java.util.List;
import java.util.concurrent.CopyOnWriteArrayList;
import java.util.function.Consumer;

class StringComparator implements Comparator<String> {
    @Override
    public int compare(final String str1, final String str2) {
    	return str2.compareTo(str1);
    }
} 

public class CopyOnWriteArrayList_Test {
	/** String simple printer. **/
	protected final Consumer<String> simplePrinter = (String value) -> System.out.println("  " + value);	
	
	public void test() {
		List<String> list = new CopyOnWriteArrayList<String>(Arrays.asList("Value1", "Value2","Value3",
																		   "Value4", "Value5","Value6", "Value7"));
		list.forEach(simplePrinter);
	}
	
	public void test_sort() {
		List<String> list = new CopyOnWriteArrayList<String>(Arrays.asList("Value1", "Value2","Value3",
				   														   "Value4", "Value5","Value6", "Value7"));
		list.sort(new StringComparator());
		list.forEach(simplePrinter);
	}
	
	/****************** MAIN ***************/
	public static void main(String[] args) {

		CopyOnWriteArrayList_Test tests = new CopyOnWriteArrayList_Test();
		// tests.test();
		tests.test_sort();
	}
}
