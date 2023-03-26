package Collections.Queue;

import java.util.Collections;
import java.util.PriorityQueue;

class Tests {
	public void Create_ReverseOrder() {
		 PriorityQueue<Integer> ints = new PriorityQueue<Integer>(Collections.reverseOrder());
		 ints.add(10);
		 ints.add(20);
		 ints.add(15);
		 ints.add(24);
		 
		 System.out.println("-------------------------- Contains:");
		 ints.forEach((Integer entry) -> System.out.println(entry));

		 System.out.println("-------------------------- Poll:");
		 while (false == ints.isEmpty())
			 System.out.println(ints.poll());
	}
	
	public void Create_Order() {
		 PriorityQueue<Integer> ints = new PriorityQueue<Integer>();
		 
		 ints.add(5);
		 ints.add(4);
		 ints.add(3);
		 ints.add(2);
		 ints.add(1);
		 
		 ints.forEach(i -> System.out.print(i + " "));
		 System.out.println();
	}
	
	public void Poll() {
		 PriorityQueue<Integer> ints = new PriorityQueue<Integer>();
		 ints.add(123);
		 ints.add(33);
		 ints.add(13);
		 ints.add(134);

		 while (false == ints.isEmpty()) {
			 System.out.println(ints.poll());
		 }
		 
	}
}

public class PriorityQueueTests {
	public static void main(String[] args) {
		Tests tests = new Tests();
		
		// tests.Create_Order();
		 tests.Create_ReverseOrder();
		// tests.Poll();
	}
}
