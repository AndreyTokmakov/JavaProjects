package Collections.synchronized_list;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Iterator;
import java.util.List;

class ListAppender implements Runnable {
	/** **/
	private List<String> myList;

	public ListAppender(List<String> myList){
	    this.myList = myList;
	}
	
	@SuppressWarnings("static-access")
	@Override
	public void run() {
		for (int i  = 0; i< 1000000; i++) {
			myList.add("Str" + String.valueOf(i));
			System.out.println("List appended");
			/*
			try {
				Thread.currentThread().sleep(1);
			} catch (InterruptedException e) {
				e.printStackTrace();
			}*/
		}
		System.out.println("ListAppender done");
	}
}


class ListRemover implements Runnable {
	/** **/
	private List<String> myList;

	public ListRemover(List<String> myList){
	    this.myList = myList;
	}
	
	@Override
	public void run() {
	    for (int i = 0; i< 1000000; i++) {
		    synchronized (myList) { 
		    	Iterator<String> iterator = myList.iterator();
			    while (iterator.hasNext()) {
			    	String value = iterator.next();
			    	if (value.contains("2")) {
				        System.out.println(value + " removed");
				        iterator.remove();
				    }
			    }
		    }
		    /*
		    try {
		    	Thread.currentThread();
		    	Thread.sleep(1);
		    } catch (InterruptedException e) {
		    	e.printStackTrace();
		    }*/
	    }
	    System.out.println("List deleter done");
	}
}


public class SynchronizedListTest2 {
	
	  public static void main(String[] args) throws InterruptedException {

		  //List<String> originalList = new ArrayList<String>();
		  List<String> syncList = Collections.synchronizedList(new ArrayList<String>());

		  Thread t1 = new Thread(new ListAppender(syncList));
		  t1.start();
		  Thread t2 = new Thread(new ListRemover(syncList));
		  t2.start();
		  
		  /*
		  for (String v: syncList)
			  System.out.println(v);*/

	}
}
