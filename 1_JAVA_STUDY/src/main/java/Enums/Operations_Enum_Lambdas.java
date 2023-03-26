/****************************************************************************
* Copyright 2020 (C) Andrey Tokmakov
* Example how to implement Enum operations types using lambdas
*
* @name    : Operations_Enum_Lambdas.java
* @author  : Tokmakov Andrey
* @version : 1.0
* @since   : October 23, 2020
****************************************************************************/ 

package Enums;

import java.util.function.DoubleBinaryOperator;

enum MathOperation {
	PLUS  ("+", (x, y) -> x + y),
	MINUS ("-", (x, y) -> x - y),
	TIMES ("*", (x, y) -> x * y),
	DIVIDE("/", (x, y) -> x / y);
	
	/** Enum symbol string: **/
	private final String symbol;
	
	/** Op: **/
	private final DoubleBinaryOperator op;
	
	MathOperation(String symbol, DoubleBinaryOperator op) {
		this.symbol = symbol;
		this.op = op;
	}
	
	/*
	@Override 
	public String toString() { 
		return symbol; 
	}
	*/
	
	public String getOperationShortName() { 
		return symbol; 
	}
	
	public double apply(double x, double y) {
		return op.applyAsDouble(x, y);
	}
}

public class Operations_Enum_Lambdas {
	
	public static void main(String[] args)
	{
		run_test(MathOperation.class, 10, 20);
	}
	
	private static <T extends Enum<T> & Operation> 
	void run_test(Class<MathOperation> opEnumType, double x, double y) {
		for (MathOperation operation : opEnumType.getEnumConstants()) {
			System.out.print(operation + " : ");
			System.out.printf("%f %s %f = %f%n", x, operation.getOperationShortName(), y, operation.apply(x, y));
		}
	}
}
