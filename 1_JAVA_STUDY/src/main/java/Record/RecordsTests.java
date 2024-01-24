/****************************************************************************
* Copyright 2021 (C) Andrey Tokmakov
* RecordsTests.java class
*
* @name    : RecordsTests.java
* @author  : Tokmakov Andrey
* @version : 1.0
* @since   : Jan 31, 2021
****************************************************************************/

package Record;

import java.lang.Record;
import java.io.Serializable;


class Tester {
	
	public record EmployeeRecord(Long id, 
								 String firstName, 
								 String lastName, 
								 String email, 
								 int age) {
	}
	
	
	public record EmployeeRecordEx(
	        Long id, 
	        String firstName, 
	        String lastName, 
	        String email, 
	        int age) implements Serializable 
	{
	    //additional field
	    static boolean minor;
	     
	    //additional method
	    public String fullName() {
	        return firstName + " " + lastName;
	    }
	}
	
	public record EmployeeRecordCtor(
	        Long id, 
	        String firstName, 
	        String lastName, 
	        String email, 
	        int age) implements Serializable 
	{
	    public EmployeeRecordCtor{
	        if(age < 18) {
	            // throw new IllegalArgumentException("You cannot hire a minor person as employee");
	        	System.err.println("You cannot hire a minor person as employee");
	        }
	    }
	}
	
	//------------------------------------------------------------------------------------------------

	public void SimpleTest() {
		EmployeeRecord emp = new EmployeeRecord (1l, "Lokesh", "Gupta", "howtodoinjava@gmail.com", 38);
         
        System.out.println(emp.id());
        System.out.println(emp.email());
        System.out.println(emp);
	}
	
	
	public void AdditionslFields() {
		EmployeeRecordEx emp = new EmployeeRecordEx (1L, "Lokesh", "Gupta", "howtodoinjava@gmail.com", 38);
         
        System.out.println(emp.id());
        System.out.println(emp.email());
        System.out.println(emp.fullName());
        System.out.println(emp);
	}
	
	public void CreateWithConstructor() {
		EmployeeRecordCtor emp = new EmployeeRecordCtor (1L, "Lokesh", "Gupta", "howtodoinjava@gmail.com", 11);
         
        System.out.println(emp.id());
        System.out.println(emp.email());
        System.out.println(emp);
	}
}

public class RecordsTests {
	/** **/
	private final static Tester tester = new Tester();
	
	public static void main(String... args) {
		// tester.SimpleTest();
		// tester.AdditionslFields();
		tester.CreateWithConstructor();
	}
}