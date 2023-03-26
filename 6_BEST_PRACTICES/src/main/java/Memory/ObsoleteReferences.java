package Memory;

import java.util.EmptyStackException;

class Stack {
	private Object[] elements;
	private int size = 0;
	private int capacity = 0;
	
	public Stack(int initialCapacity) {
		capacity = initialCapacity;
		this.elements = new Object[this.capacity];
	}

	public void push(Object e) {
		ensureCapacity();
		elements[size++] = e;
	}
	
	public Object pop() {
		if (size == 0)
			throw new EmptyStackException();
		Object result = elements[--size];
		elements[size] = null; // Eliminate obsolete reference
		return result;
	}
	
	public int size() {
		return this.size;
	}
	
	public int capacity() {
		return this.capacity;
	}
	
	/**
	* Ensure space for at least one more element, roughly
	* doubling the capacity each time the array needs to grow.
	*/
	private void ensureCapacity() {
		if (elements.length == size) {
			Object[] oldElements = elements;
			elements = new Object[2 * elements.length + 1];
			System.arraycopy(oldElements, 0, elements, 0, size);
		}
	}
}


public class ObsoleteReferences {
	public static void main(String[] args) {
		Stack stack = new Stack(1000);

		System.out.println("Capacity = " +  stack.capacity());
		System.out.println("Size = " +  stack.size());
		
		for (int i = 0; i < 100; i++)
			stack.push(new String("Value_" + i));
		
		System.out.println("Capacity = " +  stack.capacity());
		System.out.println("Size = " +  stack.size());
		
		for (int i = 0; i < 100; i++)
			stack.pop();
		
		for (int i = 100; i < 200; i++)
			stack.push(new String("Value_" + i));
		
		System.out.println("Capacity = " +  stack.capacity());
		System.out.println("Size = " +  stack.size());
		
		for (int i = 0; i < 100; i++)
			stack.pop();
		
		// 
		// RUN IN DEBUGER IN THIS LINE TO CHECK MEMORY OF POP-ED OBJECTS
		// 
		
		System.out.println("Capacity = " +  stack.capacity());
		System.out.println("Size = " +  stack.size());
	}
}
