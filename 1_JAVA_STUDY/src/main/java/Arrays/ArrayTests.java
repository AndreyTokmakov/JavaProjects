package Arrays;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List; 

class Tester {
	
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
        int intArr[] = { 10, 20, 15, 22, 35 }; 
        int intArr1[] = { 10, 15, 22 }; 
        int intArr2[] = { 10, 15, 22 }; 
        System.out.println("Integer Arrays on comparison: " + Arrays.compare(intArr, intArr1)); 
        System.out.println("Integer Arrays on comparison: " + Arrays.compare(intArr1, intArr)); 
        System.out.println("Integer Arrays on comparison: " + Arrays.compare(intArr1, intArr2)); 
	}
	
	public void CompareUnsigned() 
	{ 
		int intArr[] = { 10, 20, 15, 22, 35 }; 
		int intArr1[] = { 10, 15, 22 }; 
		System.out.println("Integer Arrays on comparison: "+ Arrays.compareUnsigned(intArr, intArr1)); 
		System.out.println("Integer Arrays on comparison: "+ Arrays.compareUnsigned(intArr1, intArr)); 
	}
	
	public void CopyOf() 
	{ 
        int intArr[] = { 10, 20, 15, 22, 35 }; 
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
        int intArr [][] = {{ 10, 20, 15, 22, 35 }}; 
        int intArr1[][] = {{ 10, 15, 22 }}; 
        
        int intArr2[][] = {{ 10, 15, 22 }}; 
        int intArr3[][] = {{ 22, 15, 10 }}; 
        
        System.out.println("Integer Arrays on comparison 1: " + Arrays.deepEquals(intArr, intArr1));
        System.out.println("Integer Arrays on comparison 2: " + Arrays.deepEquals(intArr2, intArr3));
        System.out.println("Integer Arrays on comparison 3: " + Arrays.deepEquals(intArr1, intArr2));
	}
	
    public void DeepHashCode() { 
        int intArr[][] = { { 10, 20, 15, 22, 35 } }; 
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
	
	public void Fill() { 
        int intArr[] = { 10, 20, 15, 22, 35 }; 
        int intKey = 22; 
        
        System.out.println("Integer Array on filling: " + Arrays.toString(intArr)); 
        Arrays.fill(intArr, intKey); 
        System.out.println("Integer Array on filling: " + Arrays.toString(intArr)); 
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
		
	public void Sort() { 
		int intArr[] = { 10, 20, 15, 22, 35, 123, 23, 423, 7}; 
		Arrays.sort(intArr); 
		System.out.println("Integer Array: " + Arrays.toString(intArr)); 
	} 
		
	public void Sort1() { 
		int intArr[] = { 10, 20, 15, 22, 35, 123, 23, 423, 7}; 
		Arrays.sort(intArr, 1, 5); 
		System.out.println("Integer Array: " + Arrays.toString(intArr)); 
	}
	
	public void Spliterator() { 
        int intArr[] = { 10, 20, 15, 22, 35 }; 
        System.out.println("Integer Array: " + Arrays.spliterator(intArr)); 
    }
}


public class ArrayTests {
	/** **/
	public static void main(String[] args) {
		Tester tester = new Tester();
		
		// tester.Create();
		// tester.AsList();
		// tester.Create2();
		// tester.BinarySearch();
		// tester.Compare();
		// tester.CompareUnsigned();
		// tester.CopyOf();
		tester.CopyOfRange();
		// tester.DeepEquals();
		// tester.DeepHashCode();
		// tester.DeepToString();
		// tester.Equals();
		// tester.Fill();
		// tester.Mismatch();
		// tester.ParallelSort();
		// tester.Sort();
		// tester.Sort1();
		// tester.Spliterator();
	}
}
