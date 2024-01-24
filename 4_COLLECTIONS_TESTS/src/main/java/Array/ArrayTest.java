/****************************************************************************
* Copyright 2020 (C) Andrey Tokmakov
* ArrayTest.java class
*
* @name    : ArrayTest.java
* @author  : Tokmakov Andrey
* @version : 1.0
* @since   : Dec 6, 2020
****************************************************************************/

package Array;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Random;

class Tests {
	
	private static final Random random = new Random();
	
	protected void FillArrayRandom(int[] data, int size) {
		for (int i = 0;i < size; i++) {
			data[i] = random.nextInt(size * 2);
		}
	}
	
	protected <T> void print(T[] data) {
		System.out.println(Arrays.toString(data));
	}
	
	protected void print(int[] data) {
		System.out.println(Arrays.toString(data));
	}

		
	protected void Fill() {
		int[] numbers = new int[4];
		Arrays.fill(numbers, 0);
		System.out.println(Arrays.toString(numbers)); 
	}
	
	public void Fill2() { 
        int intArr[] = { 10, 20, 15, 22, 35 }; 
        int intKey = 22; 
        
        System.out.println("Integer Array on filling: " + Arrays.toString(intArr)); 
        Arrays.fill(intArr, intKey); 
        System.out.println("Integer Array on filling: " + Arrays.toString(intArr)); 
    } 
	
	protected void Fill_Range() {
		int[] numbers = new int[10];
        Arrays.fill(numbers, 0, 5, 10); 
        System.out.println(Arrays.toString(numbers)); 
	}
	
	public void Create() {
		int[] numbers = {1,2,3,4,5};
		for (int i: numbers)
			System.out.print(i + " ");
		
		System.out.println();
		
		Arrays.stream(numbers).forEach(val -> System.out.print(val + " "));
	}
	
	public void AsList() {
		Integer[] values = { 1, 3, 7 };
		List<Integer> list = Arrays.asList(values);
		list.stream().forEach(val -> System.out.print(val + " "));

		System.out.println("\nTest2");

		List<Integer> list2 = new ArrayList<Integer>(Arrays.asList(values));
		list.stream().forEach(val -> System.out.print(val + " "));
		
		System.out.println("\nTest3");
  
        // To convert the elements as List 
        System.out.println( Arrays.asList(values)); 
	}
	
	public void BinarySearch() {
        int intArr[] = { 10, 20, 15, 22, 35 }; 
        Arrays.sort(intArr); 
        int intKey = 22; 
        System.out.println(intKey + " found at index = " + Arrays.binarySearch(intArr, intKey)); 
        System.out.println(intKey + " found at index = " + Arrays.binarySearch(intArr, 1, 3, intKey)); 
	}
	
	public void Create2() {
		Integer[] spam = new Integer[] { 1, 2, 3 };
		List<Integer> list = Arrays.asList(spam);
		list.stream().forEach(v -> System.out.println(v));
		
		String[] strings = new String[] { "Val1", "Val2", "Val3", "Val4", "Val5"};
		List<String> str_list = Arrays.asList(strings);
		str_list.stream().forEach(v -> System.out.println(v));
	}
	
	public void Compare() {
        int intArr[]  = { 10, 20, 15, 22, 35 }; 
        int intArr1[] = { 10, 15, 22 }; 
        int intArr2[] = { 10, 15, 22 }; 
        System.out.println("Compare: " + Arrays.compare(intArr,  intArr1)); 
        System.out.println("Compare: " + Arrays.compare(intArr1, intArr)); 
        System.out.println("Compare: " + Arrays.compare(intArr1, intArr2)); 
	}
	
	public void CompareUnsigned() 
	{ 
		int[] intArr = { 10, 20, 15, 22, 35 };
		int[] intArr1 = { 10, 15, 22 };
		System.out.println("Compare: " + Arrays.compareUnsigned(intArr, intArr1)); 
		System.out.println("Compare: " + Arrays.compareUnsigned(intArr1, intArr)); 
	}
	
	public void CopyOf() 
	{ 
        int[] intArr = { 10, 20, 15, 22, 35 };
        System.out.println("Integer Array: " + Arrays.toString(intArr)); 
        System.out.println("New Arrays by copyOf:"); 
        System.out.println("Integer Array: " + Arrays.toString(Arrays.copyOf(intArr, 10))); 
	} 
	
	public void CopyOfRange() 
	{ 
        int intArr[] = { 10, 20, 15, 22, 35 }; 
        System.out.println("Integer Array: " + Arrays.toString(intArr)); 
        System.out.println("\nNew Arrays by copyOfRange:\n"); 
        System.out.println("Integer Array: " + Arrays.toString(Arrays.copyOfRange(intArr, 1, 3))); 
	} 
	
	public void DeepEquals() {
        int[][] intArr = {{ 10, 20, 15, 22, 35 }};
        int[][] intArr1 = {{ 10, 15, 22 }};
        
        int[][] intArr2 = {{ 10, 15, 22 }};
        int[][] intArr3 = {{ 22, 15, 10 }};
        
        System.out.println("Integer Arrays on comparison 1: " + Arrays.deepEquals(intArr, intArr1));
        System.out.println("Integer Arrays on comparison 2: " + Arrays.deepEquals(intArr2, intArr3));
        System.out.println("Integer Arrays on comparison 3: " + Arrays.deepEquals(intArr1, intArr2));
	}
	
    public void DeepHashCode() { 
        int[][] intArr = { { 10, 20, 15, 22, 35 } };
        System.out.println("Integer Array: " + Arrays.deepHashCode(intArr)); 
    } 
	
	public void DeepToString() { 
        int intArr[][] = { { 10, 20, 15, 22, 35 } }; 
        System.out.println("Integer Array: " + Arrays.deepToString(intArr)); 
    } 

	public void Equals() { 
        int intArr[] = { 10, 20, 15, 22, 35 }; 
        int intArr1[] = { 10, 15, 22 }; 
        System.out.println("Integer Arrays on comparison: " + Arrays.equals(intArr, intArr1)); 
    } 
	
	public void Mismatch() { 
	    int intArr[] = { 10, 20, 15, 22, 35 }; 
	    int intArr1[] = { 10, 15, 22 }; 
        System.out.println("The element mismatched at index: "  + Arrays.mismatch(intArr, intArr1)); 
    }
	
	public void ParallelSort() { 
		int intArr[] = { 10, 20, 15, 22, 35, 123, 23, 423, 7}; 
		Arrays.parallelSort(intArr); 
		System.out.println("Integer Array: " + Arrays.toString(intArr)); 
	} 
		

	protected void Sort() {
		int[] numbers = new int[10];
		FillArrayRandom(numbers, numbers.length);
		
		System.out.println("Before: " + Arrays.toString(numbers)); 
		Arrays.sort(numbers);
		System.out.println("After: " + Arrays.toString(numbers)); 
	}
	
	protected void Sort_Range() {
		{
			int[] numbers = new int[10];
			FillArrayRandom(numbers, numbers.length);
			
			System.out.println("Before: " + Arrays.toString(numbers)); 
			Arrays.sort(numbers, 0, 5);
			System.out.println("After: " + Arrays.toString(numbers));
		}
		{
			int intArr[] = { 9,8,7,6,5,4,3,2,1}; 
			Arrays.sort(intArr, 1, 5); 
			System.out.println("Integer Array: " + Arrays.toString(intArr)); 
		}
	}
	
	public void Spliterator() { 
        int intArr[] = { 10, 20, 15, 22, 35 }; 
        System.out.println("Integer Array: " + Arrays.spliterator(intArr)); 
    }
	
	public void Clone() { 
        int arr1[] = { 10, 20, 15, 22, 35 }; 
        print(arr1);
        
        int arr2[] = arr1.clone();
        print(arr2);
    }
	
	public void CopyArray() { 
		int[] sourceArr = {0, 1, 2, 3, 4, 5, 6, 7, 8, 9};
		int[] destArr = new int[5];
		
		System.arraycopy(sourceArr, 5, destArr, 0, 5);
		for (int i=0; i < destArr.length; i++) {
			System.out.print(destArr[i] + " ");
		}
    }
}


public class ArrayTest {
	private final static Tests tests = new Tests();
	
	public static void main(String[] args) {
		
		// tests.Fill();
		// tests.Fill2();
		// tests.Fill_Range();
		
		// tests.Sort();
		// tests.Sort_Range();
		
		// tests.Create();
		// tests.AsList();
		// tests.Create2();
		
		// tests.BinarySearch();
		
		// tests.Compare();
		// tests.CompareUnsigned();
		
		 tests.CopyOf();
		// tests.CopyOfRange();
		
		// tests.DeepEquals();
		// tests.DeepHashCode();
		// tests.DeepToString();
		// tests.Equals();
		// tests.Fill();
		// tests.Mismatch();
		// tests.ParallelSort();
		 
		// tests.Clone();
		
		// tests.CopyArray();
		
		// tests.Spliterator();
		
		//int array[] = Arrays.newInstance(array.getClass().componentType(), 10);

	}
}
