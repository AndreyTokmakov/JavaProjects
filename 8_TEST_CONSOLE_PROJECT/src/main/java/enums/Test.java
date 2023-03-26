package enums;

import java.util.EnumSet;
import java.util.Set;

class Text {
	public enum Style { 
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


enum Ensemble {
	SOLO,
	DUET,
	TRIO,
	QUARTET,
	QUINTET,
	SEXTET,
	SEPTET,
	OCTET,
	NONET, 
	DECTET;
	
	public int numberOfMusicians() {
		return ordinal() + 1; 
	}
}

public class Test
{
	private static <T extends Enum<T> & Operation> void test(Class<T> opEnumType, double x, double y) {
		for (Operation operation : opEnumType.getEnumConstants()) {
			System.out.printf("%f %s %f = %f%n", x, operation, y, operation.apply(x, y));
		}
	}

	public static void foo(String enumStr) {
		System.out.printf(enumStr);
	}
	
	public static void main(String[] args) 
	{
		// Text text = new Text();
		// text.applyStyles(EnumSet.of(Text.Style.BOLD, Text.Style.ITALIC));
		
		/*
		double x = 11;
		double y = 22;
		test(ExtendedOperation.class, x, y);
		test(BasicOperation.class, x, y);
		*/
		
		Ensemble x = Ensemble.DECTET;
		System.out.println(Ensemble.DECTET);
		System.out.println(x.ordinal());

		String str = String.valueOf(Ensemble.DECTET);

	}
}
