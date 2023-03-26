/****************************************************************************
* Copyright 2020 (C) Andrey Tokmakov
* Observer pattern demo class
*
* @name    : Clone_Tests.java
* @author  : Tokmakov Andrey
* @version : 1.0
* @since   : November 05, 2020
****************************************************************************/ 

package ObjectOrientedProgramming.Clone_Clonable;

class Department  implements Cloneable {
	private int id;
    private String name;
 
    public Department(int id, String name) {
        this.id = id;
        this.name = name;
    }
    
    public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}
	
	@Override
	protected Object clone() throws CloneNotSupportedException {
	    return super.clone();
	}
}

class Employee implements Cloneable {
    private int empoyeeId;
    private String employeeName;
    private Department department;
 
    public Employee(int id, String name, Department dept) {
        this.empoyeeId = id;
        this.employeeName = name;
        this.department = dept;
    }
    
    public int getEmpoyeeId() {
		return empoyeeId;
	}

	public void setEmpoyeeId(int empoyeeId) {
		this.empoyeeId = empoyeeId;
	}

	public String getEmployeeName() {
		return employeeName;
	}

	public void setEmployeeName(String employeeName) {
		this.employeeName = employeeName;
	}

	public Department getDepartment() {
		return department;
	}

	public void setDepartment(Department department) {
		this.department = department;
	}

	
	@Override
	protected Object clone() throws CloneNotSupportedException {
	    Employee cloned = (Employee)super.clone();
	    cloned.setDepartment((Department)cloned.getDepartment().clone());   
	    return cloned;
	}
}

public class Clone_Tests {
	
	public static void cloneTest1() throws CloneNotSupportedException
	{
        Department dept = new Department(1, "Human Resource");
        Employee original = new Employee(1, "Admin", dept);
 
        //Lets create a clone of original object
        Employee cloned = (Employee) original.clone();
 
        
        System.out.println(cloned.getEmpoyeeId()); // Let verify using employee id, if cloning actually workded
        System.out.println(original != cloned); // Must be true and objects must have different memory addresses
        System.out.println(original.getClass() == cloned.getClass()); // As we are returning same class; so it should be true
 
        //Default equals method checks for references so it should be false. If we want to make it true,
        //then we need to override equals method in Employee class.
        System.out.println(original.equals(cloned));
	}
	
	public static void cloneTest2() throws CloneNotSupportedException
	{
		Department hr = new Department(1, "Human Resource");
        Employee original = new Employee(1, "Admin", hr);
        Employee cloned = (Employee) original.clone();
        
        System.out.println(original.getDepartment().getName());
        System.out.println(cloned.getDepartment().getName());
 
        // Let change the department name in cloned object and we will verify in original object
        cloned.getDepartment().setName("Finance");
 
        System.out.println(original.getDepartment().getName());
        System.out.println(cloned.getDepartment().getName());
	}
	
	public static void main(String[] args) throws CloneNotSupportedException
	{
		// cloneTest1();
		
		cloneTest2(); 
	}
}
