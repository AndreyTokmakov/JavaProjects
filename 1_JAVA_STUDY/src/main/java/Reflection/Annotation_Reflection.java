/****************************************************************************
* Copyright 2020 (C) Andrey Tokmakov
* Annotation reflection
*
* @name    : Annotation_Reflection.java
* @author  : Tokmakov Andrey
* @version : 1.0
* @since   : October 22, 2020
****************************************************************************/ 

package Reflection;

import java.lang.annotation.Annotation;
import java.lang.annotation.Retention;

interface FinanceInformation {
    // Some fields
}

interface FinanceInfoBuilder {
    public FinanceInformation buildFinacneInformation();
}

// We're going to use the annotation at runtime
@Retention(java.lang.annotation.RetentionPolicy.RUNTIME)
@interface FinanceAnnotation {
	// Declare a parameter for the class name with a default value
	String financeBuilder() default "edu.javacourse.reflection.DbFinanceInfoBuilder";
}

// Declare annotation for the class
@FinanceAnnotation(financeBuilder = "edu.javacourse.reflection.WebFinanceInfoBuilder")
class FinanceInfoBuilderFactory {
	public static FinanceInfoBuilder getFinanceInfoBuilder() {
     try {
    	 // Get the annotation for the class. Because this is our class, then you can cast it
         Annotation ann = FinanceInfoBuilderFactory.class.getAnnotation(FinanceAnnotation.class);
         FinanceAnnotation fa = (FinanceAnnotation)ann;
         
         // Load the class by name
         Class<?> cl = Class.forName(fa.financeBuilder());
         
         // Because our class must implement the FinanceInfoBuilder interface, then we can cast to the interface
         @SuppressWarnings("deprecation")
		 FinanceInfoBuilder builder = (FinanceInfoBuilder)cl.newInstance();
         return builder;
     } catch (ClassNotFoundException | InstantiationException | IllegalAccessException ex) {
         ex.printStackTrace(System.out);
     }
     return null;
 }
}

public class Annotation_Reflection {
	public static void main(String[] args) {
		FinanceInfoBuilder builder = FinanceInfoBuilderFactory.getFinanceInfoBuilder();
		builder.buildFinacneInformation();
	}
}
