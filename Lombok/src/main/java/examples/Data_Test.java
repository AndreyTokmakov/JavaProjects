/****************************************************************************
* Copyright 2020 (C) Andrey Tokmakov
* Data_Test.java class
*
* @name    : Data_Test.java
* @author  : Tokmakov Andrey
* @version : 1.0
* @since   : Dec 25, 2020
****************************************************************************/

package examples;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NonNull;

@Data
class PersonB {
	@NonNull
    private String firstName;
	
	@NonNull 
    private String secondName;
}


@Data
@AllArgsConstructor
class EmployeeEx
{
	private Integer id;
	private String firstName;
	private String lastName;
	private String email;
}

public class Data_Test
{
	public static void EmployeeEx_Test()
	{
		EmployeeEx empl = new EmployeeEx(1, "Lokesh", "Gupta", "howtodoinjava@gmail.com");
		System.out.println(empl);
	}

	public static void PersonB_Test()
	{
		PersonB person = new PersonB("Jonh", "McClane");
		System.out.println(person);
		System.out.println(person.getFirstName());
		System.out.println(person.getSecondName());
	}
	
	public static void main(String[] args) 
	{
		// PersonB_Test();
		EmployeeEx_Test();
	}
}
