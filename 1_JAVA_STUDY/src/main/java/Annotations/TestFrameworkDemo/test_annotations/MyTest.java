/****************************************************************************
* Copyright 2020 (C) Andrey Tokmakov
* MyTest annotation
*
* @name    : MyTest.java
* @author  : Tokmakov Andrey
* @version : 1.0
* @since   : December 02, 2020
****************************************************************************/ 

package Annotations.TestFrameworkDemo.test_annotations;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

@Retention(RetentionPolicy.RUNTIME)
@Target(ElementType.METHOD)
public
@interface MyTest {
	
}