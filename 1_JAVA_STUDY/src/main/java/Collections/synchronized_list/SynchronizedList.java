package Collections.synchronized_list;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Iterator;
import java.util.List;

class SynchroProblem implements Runnable{
	
	private List<Integer> myList;

	
	public SynchroProblem(List<Integer> myList){
	    this.myList = myList;
	}

	@Override
	public void run() {
	    // Do stuff with the list .add(), .remove(), ...
		myList.add(5);

	    // Even if mylist is synchronized the iterator is not, so for using the iterator we need the synchronized block
	    synchronized (myList) { 
	    	//do stuff with iterator e.g.
	    	Iterator<Integer> iterator = myList.iterator();
	    	while (iterator.hasNext()){
	        int number = iterator.next();
		        if (number == 123){
		        	iterator.remove();
		        }
	    	}
	    }
	}
}

public class SynchronizedList {
	  public static void main(String[] args) {

	    List<Integer> originalList = new ArrayList<Integer>();
	    List<Integer> syncList = Collections.synchronizedList(originalList);

	    // Create threads and pass the synchronized list
	    Thread t1 = new Thread(new SynchroProblem(syncList));
	    Thread t2 = new Thread(new SynchroProblem(syncList));

	    t1.start();
	    t2.start();
	  }
}
