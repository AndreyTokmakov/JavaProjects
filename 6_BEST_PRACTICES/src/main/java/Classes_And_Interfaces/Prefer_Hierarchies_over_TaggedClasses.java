/****************************************************************************
* Copyright 2020 (C) Andrey Tokmakov
* Minimize mutability example:
*
* @name      : MinimizeMutability.java
* @author    : Tokmakov Andrey
* @version   : 1.0
* @since     : October 19, 2020
****************************************************************************/ 

package Classes_And_Interfaces;

// ----------------- BAD -----------------------------//
// Tagged class - vastly inferior to a class hierarchy!
class Figure_Tagged {
	enum Shape { RECTANGLE, CIRCLE }; // TAG!!! BAD
	
	// Tag field - the shape of this figure
	final Shape shape;
	
	// These fields are used only if shape is RECTANGLE
	double length;
	double width;
	
	// This field is used only if shape is CIRCLE
	double radius;
	
	// Constructor for circle
	public Figure_Tagged(double radius) {
		shape = Shape.CIRCLE;
		this.radius = radius;
	}
	
	// Constructor for rectangle
	public Figure_Tagged(double length, double width) {
		shape = Shape.RECTANGLE;
		this.length = length;
		this.width = width;
	}
	
	double area() {
		switch(shape) {
			case RECTANGLE:
				return length * width;
			case CIRCLE:
				return Math.PI * (radius * radius);
			default:
				throw new AssertionError();
		}
	}
}

//=============================================== GOOD ===============================================//

// Class hierarchy replacement for a tagged class
abstract class Figure {
	abstract double area();
}

class Circle extends Figure {
	final double radius;
	
	Circle(double radius) { 
		this.radius = radius; 
	}
	
	double area() { 
		return Math.PI * (radius * radius); 
	}
}

class Rectangle extends Figure {
	final double length;
	final double width;
	
	Rectangle(double length, double width) {
		this.length = length;
		this.width = width;
	}
	
	double area() { 
		return length * width;
	}
}

public class Prefer_Hierarchies_over_TaggedClasses {
	public static void main(String[] args) {
		
		// Figure_Tagged circle = new Figure_Tagged(1.1);

		Figure circle = new Circle(1.1);
		Figure rectangle = new Rectangle(1.1, 1.2);
	}
}
