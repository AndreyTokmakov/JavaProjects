/****************************************************************************
* Copyright 2020 (C) Andrey Tokmakov
* Enum usage example:
*
* @name    : Gender_Tests.java
* @author  : Tokmakov Andrey
* @version : 1.0
* @since   : December 01, 2020
****************************************************************************/ 

package Enums;

import java.util.EnumSet;
import java.util.Set;

class Text {
	public static enum Style { 
		BOLD, 
		ITALIC, 
		UNDERLINE, 
		STRIKETHROUGH
	}
	
	// Any Set could be passed in, but EnumSet is clearly best
	public void applyStyles(Set<Style> styles) { 
		System.out.print("Text styles: [ ");
		for (Style s: styles)
			System.out.print(s + " ");
		System.out.print("]");
	}
}

public class Text_Style {
	public static void main(String[] args) 
	{
		Text text = new Text();
		text.applyStyles(EnumSet.of(Text.Style.BOLD, Text.Style.ITALIC));
	}
}
