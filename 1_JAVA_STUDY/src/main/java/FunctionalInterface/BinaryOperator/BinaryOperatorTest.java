//============================================================================
// Name        : BinaryOperatorTest.java
// Created on  : April 26, 2019
// Author      : Tokmakov Andrey
// Version     : 1.0
// Copyright   : Your copyright notice
// Description : SortTest
//============================================================================

package FunctionalInterface.BinaryOperator;

import java.util.ArrayList;
import java.util.List;
import java.util.function.BinaryOperator;
import java.util.function.UnaryOperator;

public class BinaryOperatorTest {
	
    public void BinaryOperator_TEST() {
        
       BinaryOperator<Integer> multiply = (x, y) -> x*y;
        
       System.out.println(multiply.apply(3, 5)); // 15
       System.out.println(multiply.apply(10, -2)); // -20
   }
    
	public void MaxBy(){
        BinaryOperator<Integer> op = BinaryOperator.maxBy((a, b) -> (a > b) ? 1 : ((a == b) ? 0 : -1));
        System.out.println(op.apply(98, 11));
        System.out.println(op.apply(-13, -92));
    }
	

	

	public static void main(String[] args) {
		BinaryOperatorTest tests = new BinaryOperatorTest();
		
		// tests.BinaryOperator_TEST();
		
		tests.MaxBy();
	}
}
