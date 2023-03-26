/****************************************************************************
* Copyright 2020 (C) Andrey Tokmakov
* Lamba_Basics class
*
* @name    : Lamba_Basics.java
* @author  : Tokmakov Andrey
* @version : 1.0
* @since   : Some_Month 01, 2020
****************************************************************************/ 

package Lambdas;

import java.util.Map;
import java.util.TreeMap;
import java.util.function.DoubleSupplier;
import java.util.function.IntSupplier;
import java.util.function.LongSupplier;
import java.util.function.Supplier;

interface ICalculator<T> {
	public T operation(T x, T y);
	
	public default void info() {
		System.out.println("ICalculator::info()");
	}
}

public class Lamba_Basics {
	
	public void Lambdas_Different_Types() {
		IntSupplier num = () -> 1;
		System.out.println(num.getAsInt());
		
		DoubleSupplier rand = Math::random;
		System.out.println(rand.getAsDouble());
		
		LongSupplier now = System::currentTimeMillis;
		System.out.println(now.getAsLong());
		
		Supplier<String> status = ()-> "Users count: " + num.getAsInt();
		System.out.println(status.get());
		
		Supplier<Map<String, String>> mapSupplier = TreeMap::new;
		System.out.println(mapSupplier.get());
	}
	
	public void Simple_Test() {
		Runnable r2 = () -> System.out.println("Hello world two!");
		r2.run();
	}
	
	public void Sum() {
		// lambda expression to implement above functional interface. This interface by default implements calculate() 
		ICalculator<Integer> intCalc = (x, y) -> { return x + y; };
		System.out.println("1 + 3 = " + intCalc.operation(1, 3));
	}
	
	public void Mult() {
		ICalculator<Integer> calculator = (x, y) -> { return x * y; };
		System.out.println("2 * 5 = " + calculator.operation(2, 5));
		calculator.info();
	}
	
	public static void main(String[] args) {
		Lamba_Basics tests  = new Lamba_Basics();
		
		tests.Lambdas_Different_Types();
		
		// tests.Simple_Test();
		// tests.Sum();
		// tests.Mult();
	}
}
