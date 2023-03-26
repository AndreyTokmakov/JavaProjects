/****************************************************************************
* Copyright 2020 (C) Andrey Tokmakov
* Interfaces best practice example:
*
* @name    : FilesInMemoryStorage.java
* @author  : Tokmakov Andrey
* @version : 1.0
* @since   : October 19, 2020
****************************************************************************/ 

package Annotations;

import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;


import Annotations.test_annotations.MyTest;
import Annotations.test_annotations.ExceptionTest;
import Annotations.test_annotations.ExceptionTestContainer;
import Annotations.test_annotations.MultipleExceptionTest;

import Annotations.test_classes.Sample;
import Annotations.test_classes.ExceptionTestContainer_Tests;
import Annotations.test_classes.ExceptionTests;
import Annotations.test_classes.MultipleException_Tests;


/** Test launcher class: **/
class TestLauncher {
	private int tests = 0;
	private int passed = 0;
	
	public void run_tests(String classname) throws Exception 
	{
		Class<?> testClass = Class.forName(classname);
		for (final Method method : testClass.getDeclaredMethods()) 
		{
			if (method.isAnnotationPresent(MyTest.class)) {
				System.out.println(String.format("Running test '%s()'", method.getName()));
				tests++;
				try {
					method.invoke(null);
					passed++;
				} catch (InvocationTargetException wrappedException) {
					Throwable exception = wrappedException.getCause();
					System.out.println(method + " failed: " + exception);
				} catch (Exception exec) {
					System.out.println("Invalid @MyTest: " + method);
				}
			}
		}
	}
	
	public void run_exception_tests(String classname) throws Exception 
	{
		Class<?> testClass = Class.forName(classname);
		for (final Method method : testClass.getDeclaredMethods()) 
		{
			System.out.println(String.format("Running test '%s()'", method.getName()));
			if (method.isAnnotationPresent(ExceptionTest.class)) {
				try {
					method.invoke(null);
					System.out.printf("Test %s failed: no exception%n", method);
				} 
				catch (InvocationTargetException wrappedException) {
					Throwable exception = wrappedException.getCause();
					Class<? extends Throwable> exceptionType = method.getAnnotation(ExceptionTest.class).value();
					if (exceptionType.isInstance(exception)) {
						passed++;
					} else {
						System.out.printf("Test %s failed: expected %s, got %s%n", 
								          method, exceptionType.getClass().getName(), exception);
					}
				} 
				catch (Exception exec) {
					System.out.println("Invalid @Test: " + method);
				}
			}
		}
	}
	
	public void run_multiple_exception_tests(String classname) throws Exception 
	{
		Class<?> testClass = Class.forName(classname);
		for (final Method method : testClass.getDeclaredMethods()) 
		{
			System.out.println(String.format("Running test '%s()'", method.getName()));
			if (true == method.isAnnotationPresent(MultipleExceptionTest.class)) {
				try {
					method.invoke(null);
					System.out.printf("Test %s failed: no exception%n", method);
				} 
				catch (InvocationTargetException wrappedException) {
					Throwable exception = wrappedException.getCause();
					int oldPassed = passed;
					Class<? extends Throwable>[] exceptionTypes = method.getAnnotation(MultipleExceptionTest.class).value();
					for (Class<? extends Throwable> exceptionType : exceptionTypes) {
					  if (exceptionType.isInstance(exception)) {
					    passed++;
					    break;
					  }
					}
					if (passed == oldPassed) {
					  System.out.println(String.format("Test %s failed: %s %n", method, exception));
					}
				} 
				catch (Exception exec) {
					System.out.println("Invalid @Test: " + method);
				}
			}
		}
	}
	
	public void run_exception_container_tests(String classname) throws Exception 
	{
		Class<?> testClass = Class.forName(classname);
		for (final Method method : testClass.getDeclaredMethods()) 
		{
			System.out.println(String.format("Running test '%s()'", method.getName()));
			if (true == method.isAnnotationPresent(ExceptionTest.class) || 
				true == method.isAnnotationPresent(ExceptionTestContainer.class)) {
				tests++;
				try {
					method.invoke(null);
					System.out.println(String.format("ERROR: Test %s failed: no exception%n", method));
				} catch (Throwable wrappedException) {
					Throwable exception = wrappedException.getCause();
					int oldPassed = passed;
					ExceptionTest[] exceptionTests = method.getAnnotationsByType(ExceptionTest.class);
					for (ExceptionTest exceptionTest : exceptionTests) {
						if (exceptionTest.value().isInstance(exception)) {
							passed++;
							break;
						}
					}
					if (passed == oldPassed) {
						System.out.println(String.format("Test %s failed: %s %n", method, exception));
					}
				}
			}
		}
	}
}

public class PreferAnnotations_to_NamingPatterns {
	/** **/
	protected static final TestLauncher testLaucnher = new TestLauncher();
	
	public static void main(String[] args) throws Exception {
		// testLaucnher.run_tests(Sample.class.getName());
		// testLaucnher.run_exception_tests(ExceptionTests.class.getName());
		// testLaucnher.run_multiple_exception_tests(MultipleException_Tests.class.getName());
		testLaucnher.run_exception_container_tests(ExceptionTestContainer_Tests.class.getName());
	}
}
