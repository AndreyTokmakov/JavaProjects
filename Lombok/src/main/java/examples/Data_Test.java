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

import lombok.Data;
import lombok.NonNull;

@Data
class PersonB {
	@NonNull 
    private String firstName;
	
	@NonNull 
    private String secondName;
}

public class Data_Test {
	
	public static void main(String[] args) 
	{
		PersonB person = new PersonB("Jonh", "McClane");
		System.out.println(person);
		System.out.println(person.getFirstName());
		System.out.println(person.getSecondName());
	}
}
