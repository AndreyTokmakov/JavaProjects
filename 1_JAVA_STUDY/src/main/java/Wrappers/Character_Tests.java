/****************************************************************************
* Copyright 2020 (C) Andrey Tokmakov
* Character wrapper tests
*
* @name    : Character_Tests.java
* @author  : Tokmakov Andrey
* @version : 1.0
* @since   : October 18, 2020
****************************************************************************/ 

package Wrappers;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

class CharacterWorkerTest {
	
	private List<Character> chars = Arrays.asList('U','9','w', '*');
	
	protected void IsLetterOrDigit() {
		for (Object entry: chars) {
			char c = (char) entry;
			System.out.println("'" + c + "'" + " isLetterOrDigit = " + Character.isLetterOrDigit(c));
		}
	}
	
	public void isDigit() {
		for (Object entry: chars) {
			char c = (char) entry;
			System.out.println("'" + c + "'" + " isDigit = " + Character.isDigit(c));
		}
	}

	public void isLetter() {
		for (Object entry: chars) {
			char c = (char) entry;
			System.out.println("'" + c + "'" + " isLetter = " + Character.isLetter(c));
		}
	}
	
	
	
}

public class Character_Tests {
	private static CharacterWorkerTest tests = new CharacterWorkerTest();
	
	public static void main(String[] args) 
	{
		// tests.IsLetterOrDigit();
		// tests.isDigit();
		tests.isLetter();
	}
}
