/**
 * 
 */
package Collections;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import java.util.NoSuchElementException;
import java.util.Enumeration;

public class CollectionsTests {
	
	public static void Min() {
		final List<Integer> numbers = Arrays.asList(7,4,9,4,9,13,7,1);
		Integer min = Collections.min(numbers);
		System.out.println("Min = "+ min);	
	}
	
	public static void Max() {
		final List<Integer> numbers = Arrays.asList(7,4,9,4,9,13,7,1);
		Integer max = Collections.max(numbers);
		System.out.println("Min = "+ max);
	}
	
	public static void Reverse() {
		final List<Integer> numbers = Arrays.asList(1,2,3,4,5);
		System.out.println(numbers);
		Collections.reverse(numbers);
		System.out.println(numbers);
	}
	
	public static void Fill() {
		int ar[] = {2, 2, 1, 8, 3, 2, 2, 4, 2};
        Arrays.fill(ar, 10);
        System.out.println("Array completely filled" + " with 10\n" + Arrays.toString(ar));
	}
	
	public static void BinarySearch() {
		 List<Integer> numbers = new ArrayList<Integer>(Arrays.asList(1,2,3,4,5,6,7,8,9));
		 
		 for (int X: Arrays.asList(1, 5, 9, 15)) {
			 int index = Collections.binarySearch(numbers, X);
			 if (index >= 0) {
				 System.out.println("Element " +  numbers.get(index) + " is found in position " + index); 
			 } else {
				 System.out.println("Failed to find element " + X); 
			 }
		 }
	}
	
	public static void BinarySearch_ReverseOrder() {
		 List<Integer> numbers = new ArrayList<Integer>(Arrays.asList(9,8,7,6,5,4,3,2,1));
		 
		 for (int X: Arrays.asList(1, 5, 9, 15)) {
			 int index = Collections.binarySearch(numbers, X, Collections.reverseOrder());
			 if (index >= 0) {
				 System.out.println("Element " +  numbers.get(index) + " is found in position " + index); 
			 } else {
				 System.out.println("Failed to find element " + X); 
			 }
		 }
	}
	
	public static void Frequency() {
		List<Integer> numbers = new ArrayList<Integer>(Arrays.asList(1,2,8,3,4,5,6,7,8,9));
		int count = Collections.frequency(numbers, 8);
		System.out.println("Frequency = " + count); 
	}
	
	 public static void Enumeration() throws Exception 
	 { 
		 try { 
			 List<String> strings = new ArrayList<String>(Arrays.asList("Ram","Gopal","Verma"));
			 System.out.println("List: " + strings); 

	         Enumeration<String> e = Collections.enumeration(strings); 
	         System.out.println("\nEnumeration over list: "); 
	         while (e.hasMoreElements()) 
	        	 System.out.println("Value is: " + e.nextElement()); 
	     } 
		 catch (IllegalArgumentException e) { 
			 System.out.println("Exception thrown : " + e); 
	     } 
		 catch (NoSuchElementException e) { 
			 System.out.println("Exception thrown : " + e); 
		 } 
	 } 

	 
	public static void EmptyList() {
		List<Integer> numbers = Collections.<Integer>emptyList(); // Collections.emptyList(); <-- also fine
		System.out.println(numbers);
		System.out.println("numbers.isEmpty() = " + numbers.isEmpty());
	}
	
	public static void TEST() {
		List<Integer> numbers = new ArrayList<Integer>(Arrays.asList(9,8,7,6,5,4,3,2,1));
		
	}
	
	public static void main(String[] args) throws Exception {
		// Min();
		// Max();
		// Reverse();
		
		// BinarySearch();
		// BinarySearch_ReverseOrder();
		
		// Frequency();
		
		// Enumeration();
		
		EmptyList();
		
// 		TEST();
	}
}
