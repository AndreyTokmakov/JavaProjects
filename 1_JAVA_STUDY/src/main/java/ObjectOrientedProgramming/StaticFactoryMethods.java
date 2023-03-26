//============================================================================
// Name        : StaticFactoryMethods.java
// Created on  : September 13, 2020
// Author      : Tokmakov Andrey
// Version     : 1.0
// Copyright   : Your copyright notice
// Description : Assertions tests
//============================================================================


package ObjectOrientedProgramming;

import java.util.HashMap;

class Color {
	// Color name:
	private String name = "None";
	
	// Available colors palette cache:
	private static HashMap<String, Color> paletteCache = 
			new HashMap<String, Color> ();
	
	private Color(String colorName) {
		this.name = colorName;
		System.out.println("Color(" + name + ") created.");
	}

    @Override
    public String toString() { 
        return String.format("Color(%s)", this.name);
    }

	@Override
	public boolean equals(final Object obj) {
		if (this == obj) 
			return true;
		else if (null == obj || obj.getClass() != this.getClass())
			return false;
		else {
			return this.name.equals(((Color)obj).name);
		}
	} 
	
	@Override
	public int hashCode() {
	   int result = name.hashCode();  
	   return result;
	}
	
	/************ Factory constructor methods: *************/
	
	public static synchronized Color createColor(String colorName) {
		if (false == Color.paletteCache.containsKey(colorName)) {
			paletteCache.put(colorName, new Color(colorName));
		}
		return Color.paletteCache.get(colorName);
	}
}


public class StaticFactoryMethods {
	public static void main(String[] args) {

		Color red1 = Color.createColor("Red");
		System.out.println(red1);
		
		Color red2 = Color.createColor("Red");
		System.out.println(red2);
		
		Color red3 = Color.createColor("Red");
		System.out.println(red3);
		
		Color blue = Color.createColor("Blue");
		System.out.println(blue);
	}
}
