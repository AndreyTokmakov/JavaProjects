/****************************************************************************
* Copyright 2020 (C) Andrey Tokmakov
* Enum usage example:
*
* @name    : Gender_Tests.java
* @author  : Tokmakov Andrey
* @version : 1.0
* @since   : December 01, 2020
****************************************************************************/ 

package Enums;

enum Gender {
    MALE("M"), 
    FEMALE("F");
	
    private final String code;
 
    private Gender(String code) {
        this.code = code;
    }
 
    public String code() {
        return code;
    }
 
    public static Gender fromCode(String code) {
    	if (code != null) {
    		for (Gender g : Gender.values()) {
                if (code.equalsIgnoreCase(g.code)) {
                    return g;
                }
            }
        }
        return null;
    }
}

public class Gender_Tests
{
	public static void main(String[] args) 
	{
		Gender gender = Gender.fromCode("M");
		System.out.println(gender + ", ordinal: " + gender.ordinal());
		
		gender = Gender.fromCode("F");
		System.out.println(gender + ", ordinal: " + gender.ordinal());
	}
}
