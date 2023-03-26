/****************************************************************************
* Copyright 2021 (C) Andrey Tokmakov
* FieldAnnotationsScanner_Tests.java class
*
* @name    : FieldAnnotationsScanner_Tests.java
* @author  : Tokmakov Andrey
* @version : 1.0
* @since   : Jan 11, 2021
****************************************************************************/

package Reflection;

import java.util.Set;

import org.reflections.Reflections;
import org.reflections.scanners.FieldAnnotationsScanner;
import org.reflections.scanners.MethodParameterScanner;
import org.reflections.scanners.Scanner;
import org.reflections.scanners.SubTypesScanner;
import org.reflections.scanners.TypeAnnotationsScanner;

import Reflection.test_classes.Base;
import Reflection.test_classes.MyTestClass;

public class SubTypesScanner_Tests {
	
	public static Set<Class<? extends Base>> getReflectionsSubTypes(final String packageName) {
	    Reflections reflections = new Reflections(packageName, new SubTypesScanner());
	    return reflections.getSubTypesOf(Base.class);
	}

	public static void getSubTypesTest() {
		Set<Class<? extends Base>> types = getReflectionsSubTypes("Reflection.test_classes");
		
		System.out.println(types.size());
		for (final Class<?> type: types) {
			System.out.println(type);
		}
	}
	
	public static void getSubTypesTest_Annotated() {
		final Reflections reflections = new Reflections("Reflection.test_classes", 
														new SubTypesScanner(), new TypeAnnotationsScanner());
		final Set<Class<?>> annotated = reflections.getTypesAnnotatedWith(MyTestClass.class);
		
		System.out.println(annotated.size());
		for (final Class<?> type: annotated)
			System.out.println(type);
	}
	
	public static void main(String[] args) {

		// getSubTypesTest();
		getSubTypesTest_Annotated();
	}
}
