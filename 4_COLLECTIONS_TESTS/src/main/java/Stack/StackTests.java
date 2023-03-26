/** **/
package Stack;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.ListIterator;
import java.util.Stack;
import java.util.stream.Collectors;

public class StackTests {
	
	protected void Create_Delete() {
		Stack<Integer> stack = new Stack<>();
		stack.push(0);
		stack.push(1);
		stack.push(2);
		
		while (false == stack.empty()) {
			System.out.println(stack.pop());
		}
	}
	
	protected void Create2() {
		Stack<Integer> stack = new Stack<>();
		List<Integer> intList = Arrays.asList(1, 2, 3, 4, 5, 6, 7);
		boolean result = stack.addAll(intList);
		
		while (false == stack.empty()) {
			System.out.println(stack.pop());
		}
	}
	
	protected void Iterator() {
	    Stack<Integer> intStack = new Stack<>();
	    List<Integer> intList = Arrays.asList(1, 2, 3, 4, 5, 6, 7);
	    intStack.addAll(intList);
	     
	    ListIterator<Integer> iter = intStack.listIterator();
	    while (iter.hasNext()) {
	    	System.out.println(iter.next());
	    }
	}
	 
	public void Filter_Stream() {
		Stack<Integer> stack = new Stack<>();
		List<Integer> inputIntList = Arrays.asList(1, 2, 3, 4, 5, 6, 7, 9, 10);
		stack.addAll(inputIntList);
		
		List<Integer> filtered = stack.stream().filter(element -> element <= 3).collect(Collectors.toList());
		System.out.println(filtered);
	}
	
	public void Capacity() {
		Stack<Integer> stack = new Stack<>();
		for (int i = 0; i < 5; i++)
			stack.push(i);
		
		System.out.println("Size = " + stack.size() + ", Capacity = " + stack.capacity());
		System.out.println(stack);
		
		for (int i = 5; i < 11; i++)
			stack.push(i);
		
		System.out.println("\nSize = " + stack.size() + ", Capacity = " + stack.capacity());
		System.out.println(stack);
		
	}
	
	public void Tests() {
		Stack<Integer> stack = new Stack<>();
		for (int i = 0; i < 5; i++)
			stack.push(i);
		
		System.out.println("Size = " + stack.size() + ", Capacity = " + stack.capacity());
		System.out.println(stack);
		
		for (int i = 5; i < 11; i++)
			stack.push(i);
		
		System.out.println("\nSize = " + stack.size() + ", Capacity = " + stack.capacity());
		System.out.println(stack);

	}
	
	public void Contains_ALL() {
		Stack<Integer> stack = new Stack<Integer>();
		stack.addAll(Arrays.asList(1, 2, 3, 4, 5, 6, 7, 9, 10));
		

		System.out.println(stack.containsAll(Arrays.asList(1,2,3,4)));
		System.out.println(stack.containsAll(Arrays.asList(1,2,3,44)));
	}
	
	////////////////////// MAIN /////////////////////////
	
	public static void main(String[] args) {
		StackTests tests = new StackTests();
		
		// tests.Create_Delete();
		// tests.Create2();
		
		// tests.Iterator();
		// tests.Filter_Stream();
		// tests.Capacity();
		
		// tests.Tests();
		
		// tests.Capacity();
		
		tests.Contains_ALL();
	}
}
