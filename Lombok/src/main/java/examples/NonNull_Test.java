/****************************************************************************
* Copyright 2020 (C) Andrey Tokmakov
* NonNull.java class
*
* @name    : NonNull.java
* @author  : Tokmakov Andrey
* @version : 1.0
* @since   : Dec 25, 2020
****************************************************************************/

package examples;

import java.io.IOException;

import lombok.NonNull;
import utils.Person;
import utils.Something;

class NonNullExample extends Something {
	private String name;
  
	public NonNullExample(@NonNull Person person) {
		super("Hello");
		this.name = person.getName();
	}
}

public class NonNull_Test {

	public static void main(String ... args) throws IOException {
		NonNullExample example = new NonNullExample(null);
	}
}
