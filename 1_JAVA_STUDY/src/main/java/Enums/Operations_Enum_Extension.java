/****************************************************************************
* Copyright 2020 (C) Andrey Tokmakov
* Person builder pattern demo
*
* @name    : ObeserverDemo.java
* @author  : Tokmakov Andrey
* @version : 1.0
* @since   : October 23, 2020
****************************************************************************/ 

package Enums;

interface Operation {
	double apply(double x, double y);
}

enum BasicOperation implements Operation {
	PLUS("+") {
		public double apply(double x, double y) { return x + y; }
	},
	  
	MINUS("-") {
		public double apply(double x, double y) { return x - y; }
	},
	  
	TIMES("*") {
		public double apply(double x, double y) { return x * y; }
	},

	DIVIDE("/") {
		public double apply(double x, double y) { return x / y; }
	};

	private final String symbol;

	BasicOperation(String symbol) {
	    this.symbol = symbol;
	}

	@Override
	public String toString() {
		return symbol;
	}
}

enum ExtendedOperation implements Operation {
	EXPONENT("^") {
		public double apply(double x, double y) {
			return Math.pow(x, y);
		}
	},
	REMAINDER("%") {
		public double apply(double x, double y) {
			return x % y;
		}
	};

	private final String symbol;

	ExtendedOperation(String symbol) {
		this.symbol = symbol;
	}

	@Override
	public String toString() {
		return symbol;
	}
}

public class Operations_Enum_Extension {
	private static <T extends Enum<T> & Operation> void test(Class<T> opEnumType, double x, double y) {
		for (Operation operation : opEnumType.getEnumConstants()) {
			System.out.printf("%f %s %f = %f%n", x, operation, y, operation.apply(x, y));
		}
	}
	
	public static void main(String[] args) 
	{
		// Text text = new Text();
		// text.applyStyles(EnumSet.of(Text.Style.BOLD, Text.Style.ITALIC));
		
		double x = 11;
		double y = 22;
		test(ExtendedOperation.class, x, y);
		test(BasicOperation.class, x, y);
	}
}
