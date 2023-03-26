/****************************************************************************
* Copyright 2020 (C) Andrey Tokmakov
* RequireNonNull.java class
*
* @name    : RequireNonNull.java
* @author  : Tokmakov Andrey
* @version : 1.0
* @since   : Dec 17, 2020
****************************************************************************/

package Object_Tests;

import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import java.util.Objects;

class Lexicon 
{
	public boolean validate(String word)  {
		return true; 
	}
}

class SpellChecker {
	private final Lexicon dictionary;
	
	public SpellChecker(Lexicon dictionary) {
		 this.dictionary = Objects.requireNonNull(dictionary);
		// this.dictionary = dictionary;
	}
	
	public SpellChecker(Lexicon dictionary, String text) {
		this.dictionary = Objects.requireNonNull(dictionary, "OPSSSSSSSSSSSSSSSSSSS");
		var s = Objects.requireNonNull(text, "Text expected to be NOT NULL");
		// this.dictionary = dictionary;
	}
	
	public boolean isValid(String word)  {
		return dictionary.validate(word);
	}
	
	public List<String> suggestions(String typo) {
		return Collections.<String>emptyList();
	}
}

public class RequireNonNull {
	public static void main(String[] args) 
	{
		try {
			SpellChecker checker = new SpellChecker(null);
		} catch (final Exception exc) {
			exc.printStackTrace();
		}
		
		try {
			SpellChecker checker = new SpellChecker(null, "");
		} catch (final Exception exc) {
			exc.printStackTrace();
		}
	}
}
