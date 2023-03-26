/****************************************************************************
* Copyright 2020 (C) Andrey Tokmakov
* Java System properties tests
*
* @name    : SystemProperties.java
* @author  : Tokmakov Andrey
* @version : 1.0
* @since   : October 17, 2020
****************************************************************************/ 

package System;

import java.util.Map;
import java.util.Properties;

class SystemPropertiesTests {
	
	protected void List_Props() 
	{
		Properties pros = System.getProperties();
		pros.list(System.out);
	}
	
	public void Get_Some_Props() 
	{
		Properties pros = System.getProperties();
	  
	    System.out.println(pros.getProperty("java.home"));
	    System.out.println(pros.getProperty("java.library.path"));
	    System.out.println(pros.getProperty("java.ext.dirs"));
		System.out.println(pros.getProperty("java.class.path"));
		System.out.println(pros.getProperty("ANT_HOME"));
	}
	
	protected void Get_Custom_Runtime_Properties  () 
	{
		Properties properties = System.getProperties();
		System.out.println("customProp = " + properties.getProperty("customProp"));
		
		// HELP: java -jar <FILE_NAME> -DcustomProp=Some_Test_Value
	}
	
	
	public void TEST() 
	{
		Map<String, String> props = System.getenv();
		
		// props.keySet().forEach(s -> System.out.println(s));
		
	    for (final Map.Entry<String, String> entry: props.entrySet()) { 
	    	System.out.println(entry.getKey() + "  =  " + entry.getValue());
	    }
	}
}

public class SystemProperties {
	private final static SystemPropertiesTests tests = new SystemPropertiesTests();
	
	public static void main(String[] args) {
		// tests.List_Props();
		// tests.Get_Some_Props();
		// tests.TEST();
		
		tests.Get_Custom_Runtime_Properties();
	}
}
