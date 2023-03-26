package Collections.Queue;

import java.util.LinkedList;
import java.util.NoSuchElementException;
import java.util.Queue;
import java.util.function.Consumer;

public class QueueTest {
	
	public static void test_pop() {
		final Consumer<String> printer = value-> System.out.println( value);
		LinkedList<String> strings = new LinkedList<String>();
		 
		strings.add("Value1");
		strings.add("Value2");
		strings.add("Value3");
		strings.add("Value4");
		
		System.out.println("Before POP: ");
		strings.forEach(printer);
		System.out.println("");
		
		while (false == strings.isEmpty()) {
			String value = strings.pop();
			System.out.println(value);
		}
	}
	
	public static void test_poll() {
		LinkedList<String> strings = new LinkedList<String>();
		 
		strings.add("Value1");
		strings.add("Value2");
		strings.add("Value3");
		strings.add("Value4");
		
		while (false == strings.isEmpty()) {
			String value = strings.poll();
			System.out.println(value);
		}
	}
	
	public static void test_complex() {
        Queue<String> myQueue = new LinkedList<String>(); 
        
        // add elements in the queue using offer() - return true/false
        myQueue.offer("Monday");
        myQueue.offer("Thursday");
        boolean flag = myQueue.offer("Wednesday");
         
        System.out.println("Wednesday inserted successfully? "+flag);
         
        // add more elements using add() - throws IllegalStateException
        try {
            myQueue.add("Thursday");
            myQueue.add("Friday");
            myQueue.add("Weekend");
        } catch (IllegalStateException e) {
            e.printStackTrace();
        }
         
        System.out.println("Pick the head of the queue: " + myQueue.peek());
         
        String head = null;
        try {
            // remove head - remove()
            head = (String) myQueue.remove();
            System.out.print("1) Push out " + head + " from the queue "); 
            System.out.println("and the new head is now: "+myQueue.element());
        } catch (NoSuchElementException e) {
            e.printStackTrace();
        }
         
        // remove the head - poll()
        head = (String) myQueue.poll();
        System.out.print("2) Push out " + head + " from the queue");
        System.out.println("and the new head is now: "+myQueue.peek());
         
        // find out if the queue contains an object
        System.out.println("Does the queue contain 'Weekend'? " + myQueue.contains("Weekend"));
        System.out.println("Does the queue contain 'Monday'? " + myQueue.contains("Monday"));
	}

    public static void main(String[] args) {
    	//test_complex();
    	//test_pop();
    	test_poll();
    }
}
