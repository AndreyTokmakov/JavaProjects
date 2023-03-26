/****************************************************************************
* Copyright 2020 (C) Andrey Tokmakov
* Java collection algoritms class
*
* @name      : Algoritms.java
* @author    : Tokmakov Andrey
* @version   : 1.0
* @since     : October 31, 2020
* 
****************************************************************************/ 

package Algoritms;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.Enumeration;
import java.util.List;
import java.util.Map;
import java.util.NoSuchElementException;

class Algoritms {
	
	public List<String> getTestList() {
		return Arrays.asList("One", "Two", "Three", "Four", "Five", "Six");
	}
	
	public void Min() {
		final List<Integer> numbers = Arrays.asList(7,4,9,4,9,13,7,1);
		Integer min = Collections.min(numbers);
			
		System.out.println(numbers + ". Min element = "+ min);	
	}
	
	public void Max() {
		final List<Integer> numbers = Arrays.asList(7,4,9,4,9,13,7,1);
		Integer max = Collections.max(numbers);
		
		System.out.println(numbers + ". Max element = " + max);
	}
	
	public void Reverse() {
		final List<Integer> numbers = Arrays.asList(1,2,3,4,5);
		System.out.print(numbers);
		Collections.reverse(numbers);
		System.out.println("  --->  " + numbers);
	}
	
	public void Fill() {
		int ar[] = {2, 2, 1, 8, 3, 2, 2, 4, 2};
        Arrays.fill(ar, 10);
        System.out.println("Array completely filled" + " with 10\n" + Arrays.toString(ar));
	}
	
	public void BinarySearch() {
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
	
	public void BinarySearch_ReverseOrder() {
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
	
	public void IndexOfSubList() {
		 List<Integer> numbers = new ArrayList<Integer>(Arrays.asList(1,2,3,4,5,6,7,8,9));
		 List<Integer> toFind1 = new ArrayList<Integer>(Arrays.asList(3,4,5));
		 List<Integer> toFind2 = new ArrayList<Integer>(Arrays.asList(3,4,5,44));
		 
		 int index1 = Collections.indexOfSubList(numbers, toFind1);
		 System.out.println("Index = " + index1); 
		 
		 int index2 = Collections.indexOfSubList(numbers, toFind2);
		 System.out.println("Index = " + index2); 
	}
	
	public void LastIndexOfSubList() {
		 List<Integer> numbers = new ArrayList<Integer>(Arrays.asList(1,2,3,4,5,1,2,3,4,5));
		 List<Integer> toFind1 = new ArrayList<Integer>(Arrays.asList(1,2));
		 List<Integer> toFind2 = new ArrayList<Integer>(Arrays.asList(3,4,5,44));
		 
		 int index1 = Collections.lastIndexOfSubList(numbers, toFind1);
		 System.out.println("Index = " + index1); 
		 
		 int index2 = Collections.lastIndexOfSubList(numbers, toFind2);
		 System.out.println("Index = " + index2); 
	}
	
	public void Frequency() {
		List<Integer> numbers = new ArrayList<Integer>(Arrays.asList(1,2,8,3,4,5,6,7,8,9));
		
		int to_find = 8;
		int count = Collections.frequency(numbers, to_find);
		
		System.out.println(numbers); 
		System.out.println("Frequency of number " + to_find + " in collection is: " + count); 
	}
	
	 public void Enumeration() throws Exception 
	 { 
		 try { 
			 List<String> strings = new ArrayList<String>(Arrays.asList("Ram", "Gopal", "Verma"));
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
	 
	 public void Copy() {
         List<String> src = new ArrayList<String>(Arrays.asList("Ram", "Gopal", "Verma"));
         List<String> dst = new ArrayList<String>(Arrays.asList("1", "2", "3")); 

         System.out.println("Src: " + src); 
         System.out.println("Dst: " + dst); 

         System.out.println("\nAfter copying:\n"); 

         // copy element into destlst 
         Collections.copy(dst, src); 

         System.out.println("Src: " + src); 
         System.out.println("Dst: " + dst); 
	 }
	 
	 public void nCopies() {
		 List<String> list = Collections.nCopies(4, "One"); 
		 System.out.println(list); 
	 }
	 
	 public void ReplaceAll() {
         List<String> list = new ArrayList<String>(Arrays.asList("One", "Two", "Three", "One", "Two", "One"));
         System.out.println(list); 
         Collections.replaceAll(list, "One", "One_New");
         System.out.println(list); 
	 }
	 
	 public void ReverseOrder() {
		 ArrayList<Integer> numbers = new ArrayList<Integer>(Arrays.asList(1,2,8,3,4,5,6,7,8,9));
		 Collections.sort(numbers, Collections.reverseOrder()); 
		 System.out.println("List after the use of Collection.reverseOrder()"+ " and Collections.sort() :\n" + numbers);      
	 }
	 
	 public void Rotate() {
		 ArrayList<Integer> numbers = new ArrayList<Integer>(Arrays.asList(1,2,8,3,4,5,6,7,8,9));
		 
		 System.out.println(numbers);
		 Collections.rotate(numbers, numbers.size() / 2);
		 System.out.println(numbers);  
	 }
	 
	 public void Sort() {
		 ArrayList<Integer> numbers = new ArrayList<Integer>(Arrays.asList(5,4,3,2,1));
		 
		 System.out.println(numbers);
		 Collections.sort(numbers);
		 System.out.println(numbers);  
	 }
	 
	 public void Swap() {
		 ArrayList<Integer> list = new ArrayList<Integer>(Arrays.asList(1,2,3,4,5));
	
		 System.out.println(list);
		 Collections.swap(list, 0, 4);
		 System.out.println(list);
	 }
	 
	 public void SingletonList() {
		try { 
			 // create singleton list using method singletonList() method 
			 List<Integer> list = Collections.singletonList(20); 
			 System.out.println("singletonList : " + list); 
		}  catch (IllegalArgumentException e) { 
			System.out.println("Exception thrown : " + e); 
		} 
	}
	 
	 public void SingletonMap() {
		 Map<Integer, String> dict = Map.of(1, "One", 2, "Two",3, "Three",4, "Four");
		 Map<Integer, String> synmap = Collections.synchronizedMap(dict);
		 
		 System.out.println(synmap); 
		 
		 try {
			 synmap.put(1, "New__One");
		 }   catch (UnsupportedOperationException exc) {
			 // exc.printStackTrace();
			 System.out.println("Exception thrown : " + exc); 
			 System.out.println("We can't modify this map. OK"); 
		 }
	} 
};

//  synchronizedCollection(Collection c)
//  synchronizedList(List list)
//  synchronizedMap(Map m)
//  synchronizedSet(Set s)
//  synchronizedSortedMap(SortedMap sm)
//  synchronizedSortedSet(SortedSet ss)

//  unmodifiableCollection(Collection c)
//  unmodifiableList(List list)
//  unmodifiableMap(Map m)
//  unmodifiableSet(Set s)
//  unmodifiableSortedMap(SortedMap sm)
//  unmodifiableSortedSet(SortedSet ss)

public class AlgoritmsTests {
	public static void main(String[] args) throws Exception {
		Algoritms algoritms = new Algoritms();
		
		// algoritms.Min();
		// algoritms.Max();
		// algoritms.Fill();
		
		// algoritms.Reverse();
		// algoritms.ReverseOrder();
		// algoritms.Rotate();
		// algoritms.Sort();
		algoritms.Swap();

		// algoritms.BinarySearch();
		// algoritms.BinarySearch_ReverseOrder();
		// algoritms.IndexOfSubList();
		
		// algoritms.SingletonList();
		// algoritms.SingletonMap();
		
		// algoritms.Frequency();
		// algoritms.Enumeration();
		
		// algoritms.Copy();
		// algoritms.nCopies();
		// algoritms.ReplaceAll();
	}
}
