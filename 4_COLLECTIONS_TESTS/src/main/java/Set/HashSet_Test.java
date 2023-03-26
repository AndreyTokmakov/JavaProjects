package Set;

import java.util.Arrays;
import java.util.HashSet;
import java.util.Set;

public class HashSet_Test {

	protected void InsertTest() {
		Set<Integer> set = new HashSet<Integer>();
		System.out.println(set.add(1));
		System.out.println(set.add(1));
	}
	
	private void PrintSet(final Set<?> set, final String text) {
		if (false == text.isEmpty() || true == text.isBlank())
			System.out.print(text);
		for (final Object value : set)
			System.out.print(value + " ");
		System.out.println();
	}
	
	protected void CreateSet() {
		Set<Integer> numbers = new HashSet<>();
	    
		numbers.add(10);
		numbers.add(20);
		numbers.add(30);
		numbers.add(40);
		numbers.add(40);

		PrintSet(numbers, "");
		
		numbers.remove(20);
		
		PrintSet(numbers, "\nAfter deleting 20:\n\n");
	}
	
	protected void CreateSet2() {
		Set<Integer> numbers = new HashSet<Integer>(Arrays.asList(1, 2, 3, 6, 2, 3));
		PrintSet(numbers, "");
	}
	
	protected void CreateSet3() {
		final Set<String> strings = new HashSet<String>() {{ add("Value1"); add("Value2"); add("Value3"); }};
		PrintSet(strings, "");
	}
	
	protected void ToList() {
		final Set<Integer> numbers = new HashSet<Integer>(Arrays.asList(1,2,3,4,5,6,7,8,9));
		final Integer[] ints = (Integer[])numbers.toArray(new Integer[numbers.size()]);
		for (final Integer i: ints)
			System.out.println(i);
	}

	protected void ToString() {
		final Set<Integer> numbers = new HashSet<Integer>(Arrays.asList(1,2,3,4,5,6,7,8,9));
		String numStr = numbers.toString();
		System.out.println(numStr);
	}
	
	protected void Contains_FInd() {
		final Set<String> strings = new HashSet<String>(Arrays.asList("Value1", "Value2", "Value3", "Value4", "Value5"));
		PrintSet(strings, "");
		
		System.out.println("\nIs set contains 'Value1': " + strings.contains("Value1"));
		System.out.println("Is set contains 'Value6': " + strings.contains("Value6"));
		System.out.println("Is set contains '" + Arrays.asList("Value1", "Value2") + "': " + strings.containsAll(Arrays.asList("Value1", "Value2")));
		System.out.println("Is set contains '" + Arrays.asList("Value1", "Value4") + "': " + strings.containsAll(Arrays.asList("Value1", "Value4")));
		System.out.println("Is set contains '" + Arrays.asList("Value1", "Value7") + "': " + strings.containsAll(Arrays.asList("Value1", "Value7")));
	
	}
	
	protected void Create_Tests() {
		final Set<Integer> set1 = Set.of(1,2,3,4,5,6,7,8,9);
		System.out.println(set1);
		
		final Set<Integer> set2 = new HashSet<Integer>(Arrays.asList(1,2,3,4,5,6,7,8,9));
		System.out.println(set1);
		
		final Set<Integer> set3 = new HashSet<Integer>() {{ add(1); add(2); add(3); }};
		System.out.println(set3);
	}

	public <T> Set<T> symmetricDifference(Set<? extends T> set1, Set<? extends T> set2) {
		final Set<T> result = new HashSet<T>(set1);
		for (T i: set2) {
			if (set1.contains(i))
				result.remove(i);
			else
				result.add(i);
		}
		return result;
	}

	public void TESTS() {
		final Set<Integer> set1 = Set.of(1,2,3);
		System.out.println(set1);

		final Set<Integer> set2 = Set.of(0,1,2);
		System.out.println(set2);

		final Set<Integer> set3 = symmetricDifference(set1, set2);
		System.out.println(set3);

	}
	
	public static void main(String[] args) {
		HashSet_Test tests = new HashSet_Test();
		
		// tests.Create_Tests();
		
		// tests.InsertTest();
		// tests.CreateSet();
		// tests.CreateSet2();
		// tests.CreateSet3();
		// tests.ToList();
		// tests.Contains_FInd();
		// tests.ToString();

		tests.TESTS();
	}
}
