/****************************************************************************
* Copyright 2020 (C) Andrey Tokmakov
* MultipleExceptionTest annotation
*
* @name    : MultipleExceptionTest.java
* @author  : Tokmakov Andrey
* @version : 1.0
* @since   : December 02, 2020
****************************************************************************/ 

package Annotations.test_annotations;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

@Retention(RetentionPolicy.RUNTIME)
@Target(ElementType.METHOD)
public @interface MultipleExceptionTest {
	public Class<? extends Throwable>[] value();
}