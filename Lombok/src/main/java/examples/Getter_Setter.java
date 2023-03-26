/****************************************************************************
* Copyright 2020 (C) Andrey Tokmakov
* Getter_Setter.java class
*
* @name    : Getter_Setter.java
* @author  : Tokmakov Andrey
* @version : 1.0
* @since   : Dec 25, 2020
****************************************************************************/

package examples;


import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@RequiredArgsConstructor(staticName = "of")
@AllArgsConstructor(access = AccessLevel.PUBLIC)
class User {
    private String firstName;
    private String secondName;
    
    @Setter(AccessLevel.PROTECTED) 
    private String login;
    
    @Setter(AccessLevel.PRIVATE) 
    private String password;
}

public class Getter_Setter {
	public static void main(String[] args) 
	{
		User user = new User("FIRST_NAME", "SECOND_NAME", "LOGIN", "PASSWORD");

		System.out.println(user.getFirstName());
		System.out.println(user.getSecondName());
		System.out.println(user.getLogin());
		System.out.println(user.getPassword());
		
		System.out.println();
		
		user.setFirstName("NAME_1_UPDATED");
		user.setSecondName("NAME_2_UPDATED");
		user.setLogin("LOGIN_UPDATED");
		// user.setPassword("SET_PASS"); // ERROR  <---> Private
		
		System.out.println(user.getFirstName());
		System.out.println(user.getSecondName());
		System.out.println(user.getLogin());
		System.out.println(user.getPassword());
	}
}
