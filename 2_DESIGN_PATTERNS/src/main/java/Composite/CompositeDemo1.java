//============================================================================
// Name        : CompositeDemo1.java
// Created on  : August 25, 2020
// Author      : Tokmakov Andrey
// Version     : 1.0
// Copyright   : Your copyright notice
// Description : Composite design pattern demo
//============================================================================

package Composite;

import java.util.ArrayList;
import java.util.List;

interface IEmployee { 
    public void showEmployeeDetails(); 
} 

class BaseEmployee implements IEmployee {
    private long empId; 
    private String name; 
    private String position; 
    
    public BaseEmployee(long empId, String name, String position)  { 
        this.empId = empId; 
        this.name = name; 
        this.position = position; 
    } 
      
    @Override
    public void showEmployeeDetails()  { 
        System.out.println(String.format("[id: %s, Name: %s, Positions: %s", empId, name, position)); 
    } 
}

class Developer extends BaseEmployee {
	public Developer(long empId, String name, String position) {
		super(empId, name, position);
	}  
} 

class Manager extends BaseEmployee {
	public Manager(long empId, String name, String position) {
		super(empId, name, position);
	}  
} 

// The Composite!
class CompanyDirectory implements IEmployee { 
    private final List<IEmployee> employeeList = new ArrayList<IEmployee>();
       
    @Override
    public void showEmployeeDetails()  { 
        for(IEmployee emp:employeeList) { 
            emp.showEmployeeDetails(); 
        } 
    } 
       
    public void addEmployee(IEmployee emp) { 
        employeeList.add(emp); 
    } 
       
    public void removeEmployee(IEmployee emp) { 
        employeeList.remove(emp); 
    } 
} 


public class CompositeDemo1 {
	public static void main(String[] args) {
		
		Developer dev1 = new Developer(100, "Lokesh Sharma", "Pro Developer"); 
        Developer dev2 = new Developer(101, "Vinay Sharma", "Developer"); 
        
        Manager mgr1 = new Manager(200, "Kushagra Garg", "SEO Manager"); 
        Manager mgr2 = new Manager(201, "Vikram Sharma ", "Kushagra's Manager"); 
        
        
        
        CompanyDirectory engDirectory = new CompanyDirectory(); 
        engDirectory.addEmployee(dev1); 
        engDirectory.addEmployee(dev2); 
          
        CompanyDirectory accDirectory = new CompanyDirectory(); 
        accDirectory.addEmployee(mgr1); 
        accDirectory.addEmployee(mgr2); 
        
        
      
        CompanyDirectory directory = new CompanyDirectory(); 
        directory.addEmployee(engDirectory); 
        directory.addEmployee(accDirectory); 
        directory.showEmployeeDetails(); 
	}
}
