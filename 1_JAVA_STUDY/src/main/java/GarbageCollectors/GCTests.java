package GarbageCollectors;

import java.lang.ref.SoftReference;
import java.util.concurrent.TimeUnit;

class ReferenceObject {  
	@Override
	public void finalize() {
		System.out.println("object is garbage collected");
	}
}

class TestObject {  
	@Override
	public void finalize() {
		System.out.println("TestObject::finalize()");
	}
}

class Person {   
    // to store person (object) name
    String name;
     
    public Person(String name) {
        this.name = name;
    }
     
    @Override
    /* Overriding finalize method to check which object is garbage collected */
    protected void finalize() throws Throwable {
        // will print name of person (object)
        System.out.println("Person object - " + this.name + " -> successfully garbage collected");
        TimeUnit.MILLISECONDS.sleep(500);
    }
}

public class GCTests {
	
	protected void SimpleTest() {
		ReferenceObject refObj1 = new ReferenceObject();  
		ReferenceObject refObj2  =new ReferenceObject();  
		refObj1 = null;  
		refObj2 = null;  
		
		System.gc(); 
		Runtime.getRuntime().runFinalization();
	}
	
	protected void Test2() {
		TestObject obj = new TestObject();
		SoftReference<TestObject> ref = new SoftReference<TestObject>(obj);
		obj = null;
		obj = ref.get();
		ref.clear();
	}
	
	protected void Test3() {
		Person p1 = new Person("John Doe");
		p1 = null;
		
		System.gc(); 
		//Runtime.getRuntime().gc();
		
		Runtime.getRuntime().runFinalization();
	}
	
	public static void main(String[] args) {
		GCTests test = new GCTests();
		
		// test.SimpleTest();
		// test.Test2();
		test.Test3();
	}
}
