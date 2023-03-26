package ClassTests;

/** TestObject class. **/
class TestObject { 
    protected String name = "";
      
    // A normal parametrized constructor  
    public TestObject(final String name) { 
    	System.out.println("Normal constructor called"); 
        this.name = name; 
    } 
      
    // Copy constructor 
    public TestObject(final TestObject obj) { 
        System.out.println("Copy constructor called"); 
        this.name = obj.name; 
    } 
       
    // Overriding the toString of Object class 
    @Override
    public String toString() { 
        return String.format("TestObject(%s)", this.name);
    } 
} 

public class CopyConstructorTest {
	public static void main(String[] args) {
		TestObject obj1 = new TestObject("TestValue"); 
        
        // Following involves a copy constructor call 
		TestObject obj2 = new TestObject(obj1);    
  
        // Note that following doesn't involve a copy constructor call as  
        // non-primitive variables are just references. 
		TestObject obj3 = obj2;    
  
		// toString() of c2 is called here 
        System.out.println(obj2);

	}
}
