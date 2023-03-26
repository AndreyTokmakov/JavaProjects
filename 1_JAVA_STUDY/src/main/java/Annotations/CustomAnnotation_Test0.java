/****************************************************************************
* Copyright 2020 (C) Andrey Tokmakov
* Java custom annotations demo.
*
* @name      : CustomAnnotation_Test0.java
* @author    : Tokmakov Andrey
* @version   : 1.0
* @since     : November 01, 2020
* 
****************************************************************************/ 

package Annotations;
import java.lang.annotation.*;
import java.lang.reflect.*;

@Retention(RetentionPolicy.RUNTIME)
@interface MyAnnotation {
	String str();
	int val();
}

@Retention(RetentionPolicy.RUNTIME)
@interface VersionAnnotation {
	String author() default "unknown";
	String version() default "1.0";
}

class Worker {
	@MyAnnotation(str = "Some example text", val = 54321)
	public void annotated_method() 
			throws NoSuchMethodException, SecurityException {

		Method method = this.getClass().getMethod("annotated_method");
		MyAnnotation anno = method.getAnnotation(MyAnnotation.class);
		System.out.println("str: " + anno.str() + "\nval: " + anno.val());
	}
	
	@VersionAnnotation
	public void annotated_method2() 
			throws NoSuchMethodException, SecurityException {
		
		Method method = this.getClass().getMethod("annotated_method2");
		VersionAnnotation anno = method.getAnnotation(VersionAnnotation.class);
		System.out.println("author: " + anno.author() + "\nversion: " + anno.version());
	}
}

public class CustomAnnotation_Test0 {
	public static void main(String[] args) throws NoSuchMethodException, SecurityException {
		Worker w = new Worker();
		
		// w.annotated_method();
		w.annotated_method2();
	}
}
