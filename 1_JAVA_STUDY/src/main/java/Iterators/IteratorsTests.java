package Iterators;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Iterator;
import java.util.List;
import java.util.ListIterator;

class Tests {
	
	public void IterateList() {
		List<String> strings = Arrays.asList("Value1", "Value2", "Value3", "Value4");
		Iterator<String> iter = strings.iterator();
		while (iter.hasNext()){
			System.out.println(iter.next());
		}
	}
	
	public void ListIterator_Tests() {
		List<String> states = Arrays.asList("Value1", "Value2", "Value3", "Value4");
		ListIterator<String> listIter = states.listIterator();
        while (listIter.hasNext())
            System.out.println(listIter.next());
        
        System.out.println("\nSet & Iterate backwards:\n");
        
        listIter.set("Value5");
        while (listIter.hasPrevious())
            System.out.println(listIter.previous());
	}
	
	public void Remove() {
		ArrayList<String> strings = new ArrayList<String>();
		strings.addAll(Arrays.asList("Value1", "Value2", "Value3", "Value4"));
		
		ListIterator<String> listIter = strings.listIterator();
        while (listIter.hasNext()) {
            String val = listIter.next();
            if (true == val.equals("Value3")) {
	            listIter.remove();
            }
        }
        
        System.out.println("After deleting 'Value3':");
        strings.stream().forEach(str -> System.out.println(str));
        
        listIter = strings.listIterator();
		while (listIter.hasNext()) {
			listIter.next();
			listIter.remove();
		}
		
		System.out.println("\nAfter deleting all:");
        strings.stream().forEach(str -> System.out.println(str));
	}	
	
	public void PreviousIndex() {
		ArrayList<String> strings = new ArrayList<String>();
		strings.addAll(Arrays.asList("Value1", "Value2", "Value3", "Value4"));


		ListIterator<String> listIter = strings.listIterator();
        while (listIter.hasNext()) {
            String val = listIter.next();
            System.out.println(listIter.previousIndex() + " = " + val);
        }
	}
	
	public void ForEachRemaining() {
		// ArrayList<String> strings = new ArrayList<String>(Arrays.asList("1", "2", "3", "4", "5", "6"));
		// System.out.println(strings);
		
		Iterator<Integer> iterator = Arrays.asList(1,25,87,100,1143,144).iterator();
		
		while (iterator.hasNext()) {
			if (iterator.next() == 100) { 
				break;
			}
		}
		
		iterator.forEachRemaining(System.out::println);

	}
}


public class IteratorsTests {
	
	/************* Main: *****************/
	public static void main(String[] args) {
		Tests tests = new Tests();
		
		// tests.IterateList();
		// tests.ListIterator_Tests();
		// tests.Remove();
		// tests.PreviousIndex();
		tests.ForEachRemaining();
	}
}
