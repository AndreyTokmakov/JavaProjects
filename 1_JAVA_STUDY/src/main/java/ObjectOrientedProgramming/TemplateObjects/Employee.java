package ObjectOrientedProgramming.TemplateObjects;

public class Employee { 
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
