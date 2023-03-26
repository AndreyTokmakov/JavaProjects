package Object_Tests;


/** NonClonableObject class. **/
class NonClonableObject {
	// Name
	protected String name = "<None>";
	
	// Default constructor:
	public NonClonableObject() {
		System.out.println(this.getClass().getName() + " default constructor");
	}
	
	// Constructor with params:
	public NonClonableObject(final String name) {
		System.out.println(this.getClass().getName() + " parameterized constructor");
		this.name = name;
	}
	
	public void setName(final String name) {
		this.name = name;
	}
	
	@Override
	public String toString() {
		return String.format("NoClonableObject (%s)", this.name);
	}
	
	@Override
	protected Object clone() throws CloneNotSupportedException {
		return super.clone();
	}
}


/** ClonableObject class. **/
class ClonableObject implements Cloneable {
	// Name
	protected String name = "<None>";
	
	// Default constructor:
	public ClonableObject() {
		System.out.println(this.getClass().getName() + " default constructor");
	}
	
	// Constructor with params:
	public ClonableObject(final String name) { 
		System.out.println(this.getClass().getName() + " parameterized constructor");
		this.name = name;
	}
	
	// Copy constructor :
	public ClonableObject(final ClonableObject obj) {
		System.out.println(this.getClass().getName() + " copy constructor");
		this.name = obj.name;
	}
	
	public void setName(final String name) {
		this.name = name;
	}
	
	@Override
	public String toString() {
		return String.format("ClonableObject (%s)", this.name);
	}
	
	@Override
	protected Object clone() throws CloneNotSupportedException {
		return super.clone();
	}
}

/********************* TESTS ***********************/
public class Clone_Tests {
	
	protected void NonClonableTest() {
		try {
			NonClonableObject obj = new NonClonableObject("ThisClassShallNoBeCloned");
			NonClonableObject obj1 = (NonClonableObject)obj.clone();
			System.out.println(obj);
			System.out.println(obj1);
		} catch (CloneNotSupportedException e) {
			e.printStackTrace();
		}
	}
	
	protected void NonClonableTest_1() {
		NonClonableObject obj  = new NonClonableObject("ThisClassShallNoBeCloned");
		NonClonableObject obj1 = new NonClonableObject("ThisClassShallNoBeCloned_1");
		
		obj = obj1;
		
		System.out.println("obj: " + obj + ", obj1:  " + obj1);
		obj.setName("Updated_Name");
		System.out.println("obj: " + obj + ", obj1:  " + obj1);
	}
	
	protected void Clone_Test1() throws CloneNotSupportedException {
		ClonableObject obj = new ClonableObject("NOT_UPDATED_NAME");
		ClonableObject obj1 = (ClonableObject)obj.clone();
		
		System.out.println("obj: " + obj + ", obj1:  " + obj1);
		obj.setName("<--OK-->");
		System.out.println("obj: " + obj + ", obj1:  " + obj1);
	}
	
	protected void Clone_Test2() throws CloneNotSupportedException {
		ClonableObject obj  = new ClonableObject("NOT_UPDATED_NAME");
		ClonableObject obj1 = new ClonableObject(obj);
		
		System.out.println("obj: " + obj + ", obj1:  " + obj1);
		obj.setName("<--OK-->");
		System.out.println("obj: " + obj + ", obj1:  " + obj1);
	}
	
	/** MAIN 
	 * @throws CloneNotSupportedException **/
	public static void main(String[] args) throws CloneNotSupportedException {
		Clone_Tests tests = new Clone_Tests();
		
		// tests.NonClonableTest();
		// tests.NonClonableTest_1();
		
		 tests.Clone_Test1();
		// tests.Clone_Test2();
	}
}
