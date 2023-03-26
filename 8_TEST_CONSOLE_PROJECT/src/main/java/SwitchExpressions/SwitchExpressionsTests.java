/****************************************************************************
* Copyright 2020 (C) Andrey Tokmakov
* Observer pattern demo class
*
* @name      : ObeserverDemo.java
* @author    : Tokmakov Andrey
* @version   : 1.0
* @since     : November 17, 2020
****************************************************************************/ 

package SwitchExpressions;

enum Day {
   MON, 
   TUE, 
   WED, 
   THUR, 
   FRI, 
   SAT, 
   SUN
};

class SwitchTester {
    public Boolean isWeekDayV1_1 (Day day) 
    {
        Boolean result = switch(day) {
            case MON, TUE, WED, THUR, FRI -> true;
            case SAT, SUN -> false;
        };
        return result;
    }
    
    public Boolean isWeekDay_Yield(Day day) 
    {
        Boolean result = switch(day) {
            case MON, TUE, WED, THUR, FRI : yield true;
            case SAT, SUN : yield false;
        };
        return result;
    }
    
    public Boolean isWeekDay_MultipleStatements(Day day) 
    {
        Boolean result = switch(day) {
            case MON, TUE, WED, THUR, FRI -> { 
                System.out.println("It is WeekDay");
                yield true; 
            }
            case SAT, SUN -> { 
                System.out.println("It is Weekend");
                yield false; 
            }
        };
        return result;
    }
    
    public void Return_vs_Yield() {
    	final Day day = Day.MON;
    	{
    		boolean result = isWeekDayV1_1(day);
    		System.out.println(result);
    	}
    	{
    		boolean result = isWeekDay_Yield(day);
    		System.out.println(result);
    	}
    } 
}

public class SwitchExpressionsTests {
	public static void main(String[] args) 
	{
		SwitchTester tester = new SwitchTester();
		
		/*
		System.out.println(tester.isWeekDayV1_1(Day.MON));     //true
        System.out.println(tester.isWeekDay_Yield(Day.MON));     //true
        System.out.println(tester.isWeekDay_MultipleStatements(Day.MON));       //true
        */
		
		tester.Return_vs_Yield();
	}
}
