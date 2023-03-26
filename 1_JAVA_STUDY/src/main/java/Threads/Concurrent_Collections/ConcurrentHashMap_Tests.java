package Threads.Concurrent_Collections;

import java.util.Random;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.TimeUnit;

class TestObject {
	/** **/
	private String value = "";
	
	/**  @param value*/
	public TestObject(String value) {
		this.value = value;
	}
	
	/** @return the value : **/
	public String getValue() {
		return value;
	}
	
	/** @param value the value to set : **/
	public void setValue(String value) {
		this.value = value;
	}
	
	// Overriding the toString of BaseObject class 
	@Override
	public String toString() {
		return this.value;
	}

    // Overriding the equals of BaseObject class 
	@Override
	public boolean equals(final Object obj) {
		if (this == obj) 
			return true;
		else if (null == obj || obj.getClass() != this.getClass())
			return false;
		return this.value.equals(((TestObject)obj).value);
	} 
	
	@Override
	public int hashCode() {
	   int result = value.hashCode();  
	   return result;
	}
}

class Table {
	/** **/
	// private Map<Integer,String> map = Collections.synchronizedMap(new HashMap<Integer,String>());
	/** **/
	private ConcurrentHashMap<Integer,TestObject> map = new ConcurrentHashMap <Integer,TestObject>(16, 0.9f, 1);
	
	/** **/
	public Table() {
		for (int i = 1; i <= 10; i++) {
			map.put(i, new TestObject("String" + i));
		}
	}
	
	public void Read() {
		TestObject obj = map.get(new Random().nextInt(10) + 1);
		System.out.println(obj);
		try {
	    	TimeUnit.MILLISECONDS.sleep(1);
	    } catch (InterruptedException e) {
	        e.printStackTrace();
	    }
	}
	
	public void Write() {
		map.clear();
		try {
	    	TimeUnit.SECONDS.sleep(10);
	    } catch (InterruptedException e) {
	        e.printStackTrace();
	    }
		for (int i = 1; i <= 10; i++) {
			map.put(i, new TestObject("String" + i));
		}
	}
	
	public void Test()
	{
		for (int i = 1; i <= 10; i++) {
			map.put(i, new TestObject("String" + i));
		}
		System.out.println(map.size());
	}
}




public class ConcurrentHashMap_Tests {
	public static void main(String[] args) {
		Table table = new Table();
		table.Test();
	}
}
