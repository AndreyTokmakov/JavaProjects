/****************************************************************************
* Copyright 2021 (C) Andrey Tokmakov
* Environment_Test.java class
*
* @name    : Environment_Test.java
* @author  : Tokmakov Andrey
* @version : 1.0
* @since   : Jan 16, 2021
****************************************************************************/

package Enums;

import java.util.HashMap;
import java.util.Map;

enum Environment 
{
    PROD("https://prod.domain.com:1088/"), 
    SIT("https://sit.domain.com:2019/"), 
    CIT("https://cit.domain.com:8080/"), 
    DEV("https://dev.domain.com:21323/");
 
    private String url;
 
    Environment(String envUrl) {
        this.url = envUrl;
    }
 
    public String getUrl() {
        return url;
    }

	//****** Reverse Lookup Implementation************//
 
    // Lookup table
    private static final Map<String, Environment> lookup = new HashMap<String, Environment> ();
  
    // Populate the lookup table on loading time
    static {
        for(Environment env : Environment.values()) {
            lookup.put(env.getUrl(), env);
        }
    }
  
    //This method can be used for reverse lookup purpose
    public static Environment get(String url) {
        return lookup.get(url);
    }
}

public class Environment_Test
{
	public static void main(String[] args) 
	{
		//Get all Enums
		for(Environment env : Environment.values()) {
		    System.out.println(env.name() + " = " + env.getUrl());
		}

		// Using enum constant reference
		String prodUrl = Environment.PROD.getUrl();
		System.out.println("\n" + prodUrl);

		
		// Get enum constant by string - Reverse Lookup
		String url = "https://sit.domain.com:2019/";
		Environment env = Environment.get(url);
		System.out.println("\n" + env);
	}
}
