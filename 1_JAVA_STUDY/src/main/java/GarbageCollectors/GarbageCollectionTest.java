package GarbageCollectors;


// Java program to demonstrate requesting  
// JVM to run Garbage Collector 
class Test { 
	
	public void RunTest() throws InterruptedException { 
		Test t1 = new Test(); 
		Test t2 = new Test(); 
       
		t1 = null;                  // Nullifying the reference variable 
		System.gc();                // requesting JVM for running Garbage Collector 
		t2 = null;                  // Nullifying the reference variable 
		Runtime.getRuntime().gc();  // requesting JVM for running Garbage Collector 
	} 
   
	// finalize method is called on object once before garbage collecting it 
	@Override
	protected void finalize() throws Throwable { 
		System.out.println("Garbage collector called"); 
		System.out.println("Object garbage collected : " + this); 
	} 
} 


class Employee { 
    private int ID; 
    private String name; 
    private int age; 
    private static int nextId = 1; 
   
    // it is made static because it  is keep common among all and shared by all objects 
    public Employee(String name,int age) { 
        this.name = name; 
        this.age = age; 
        this.ID = nextId++; 
    } 
    
    public void show() { 
        System.out.println("Id="+ID+"\nName="+name+"\nAge="+age); 
    } 
    
    public void showNextId() { 
        System.out.println ("Next employee id will be="+nextId); 
    } 
    
    // Overriding the toString of Employee class 
    @Override
    public String toString() { 
        return String.format("Employee(ID: %d, name: %s, age: %d)", 
        					 this.ID, this.name, this.age);
    }

    // Overriding the equals of BaseObject class 
	@Override
	public boolean equals(final Object obj) {
		if (this == obj) 
			return true;
		else if (null == obj || obj.getClass() != this.getClass())
			return false;
		
		Employee emp = (Employee)obj;
		if (this.ID != emp.ID || this.age != emp.age)
			return false;
		else 
			return this.name.equals(emp.name);
	} 
	
	@Override
	public int hashCode() {
	   int result = this.name.hashCode();  
	   result = 31 * result + this.ID;    
	   result = 31 * result + this.age; 
	   return result;
	}
} 


public class GarbageCollectionTest {
	public static void main(String[] args) throws InterruptedException {
		
		/*
		Test test = new Test();
		test.RunTest();
		*/
		
		
        Employee E=new Employee("GFG1",56); 
        Employee F=new Employee("GFG2",45);  
        Employee G=new Employee("GFG3",25); 
        E.show(); 
        F.show(); 
        G.show(); 
        E.showNextId(); 
        F.showNextId(); 
        G.showNextId(); 
              
        {
            Employee X=new Employee("GFG4",23);      
            Employee Y=new Employee("GFG5",21); 
            X.show(); 
            Y.show(); 
            X.showNextId(); 
            Y.showNextId(); 
            
            X = null;
            Y = null;
            
            System.gc();  
            System.runFinalization(); 
    		Runtime.getRuntime().runFinalization();
        } 
        
        // After countering this brace, X and Y will be removed.Therefore, now it should show nextId as 4. 
        
        E.showNextId();//Output of this line  
        
        // should be 4 but it will give 6 as output. 
	}
}
