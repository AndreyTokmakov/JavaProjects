//============================================================================
// Name        : ExceptionTesting.java
// Created on  : Jul 19, 2017
// Author      : Tokmakov Andrey
// Version     :
// Copyright   : Your copyright notice
// Description : ExceptionTesting class
//============================================================================

package Exceptions;

/** @class ExceptionTesting:  **/
public class ExceptionTesting {
	/*** */
	public ExceptionTesting() {
		// TODO Auto-generated constructor stub
	}
	public void Test() throws MyException
	{
		throw new MyException("Test");
	}
	
	
	public static void main(String[] args) {
		
		ExceptionTesting T = new ExceptionTesting();
		
		try {
			T.Test();
		} catch (MyException e) {
			System.out.println("MyException!!!");
			System.out.println(e.getClass());
			e.printStackTrace();
		} catch (Exception e) {
			System.out.println("Exception!!!");
			System.out.println(e.getClass());
			e.printStackTrace();
		}
	}
}
