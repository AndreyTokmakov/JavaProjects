package Deque;

import java.util.concurrent.ConcurrentLinkedDeque;

class AddTask implements Runnable { 
    private final ConcurrentLinkedDeque<String> list;
 
    public AddTask(ConcurrentLinkedDeque<String> list) {
        this.list = list;
    }
 
    @Override
    public void run() {
        String name = Thread.currentThread().getName();
        for (int i = 0; i < 10000; i++) {
            list.add(name + ": Element " + i);
        }
    }
}

class RemoveTask implements Runnable {
    private final ConcurrentLinkedDeque<String> list;
 
    public RemoveTask(ConcurrentLinkedDeque<String> list) {
        this.list = list;
    }
 
    @Override
    public void run() {
        for (int i = 0; i < 5000; i++) {
            list.pollFirst();
            list.pollLast();
        }
    }
}

public class ConcurrentLinkedDeque_Tests {
	/** **/
	protected ConcurrentLinkedDeque<String> linked_deque = new ConcurrentLinkedDeque<String>();
	
	private void Add_Peek_Test() {
		linked_deque.clear();
		
		linked_deque.add("Test1");
		linked_deque.add("Test2");
		linked_deque.add("Test3"); 
		
		for (int i = 0; i < 10; i++)
		{
			String element = linked_deque.peekLast();
			System.out.println(element);
		}
	}
	
    public static void Add_Remove_Tasks() 
    {
        ConcurrentLinkedDeque<String> list = new ConcurrentLinkedDeque<>();
        Thread threads[] = new Thread[100];
 
        for (int i = 0; i < threads.length; i++) {
            AddTask task = new AddTask(list);
            threads[i] = new Thread(task);
            threads[i].start();
        }
        System.out.printf("Main: %d AddTask threads have been launched\n", threads.length);
 
        for (int i = 0; i < threads.length; i++) {
            try {
                threads[i].join();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
        System.out.printf("Main: Size of the List: %d\n", list.size());
 
        for (int i = 0; i < threads.length; i++) {
            RemoveTask task = new RemoveTask(list);
            threads[i] = new Thread(task);
            threads[i].start();
        }
        System.out.printf("Main: %d RemoveTask threads have been launched\n", threads.length);
 
        for (int i = 0; i < threads.length; i++) {
            try {
                threads[i].join();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
        System.out.printf("Main: Size of the List: %d\n", list.size());
    }
	
	/*********************** MAIN ***********************/
    public static void main(String[] args)  {
    	ConcurrentLinkedDeque_Tests tests = new ConcurrentLinkedDeque_Tests();
    	
    	// tests.Add_Peek_Test();
    	tests.Add_Remove_Tasks();
    }
}
