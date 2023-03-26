/****************************************************************************
* Copyright 2020 (C) Andrey Tokmakov
* EnumMap_Tests.java class
*
* @name    : EnumMap_Tests.java
* @author  : Tokmakov Andrey
* @version : 1.0
* @since   : January 06, 2021
****************************************************************************/

package Enums;

import java.util.EnumMap;
import java.util.Map.Entry;

class EnumMapTester {
	
	/** DayOfWeek enum: **/
	private static enum DayOfWeek {
	    MONDAY, 
	    TUESDAY,
	    WEDNESDAY, 
	    THURSDAY, 
	    FRIDAY, 
	    SATURDAY, 
	    SUNDAY
	}
	
	private EnumMap<DayOfWeek, String> buildMap() {
		EnumMap<DayOfWeek, String> map = new EnumMap<DayOfWeek, String>(DayOfWeek.class);
		
		map.put(DayOfWeek.SUNDAY, "Its Sunday!!");
		map.put(DayOfWeek.MONDAY, "Its Monday!!");
		map.put(DayOfWeek.TUESDAY, "Its Tuesday!!");
		map.put(DayOfWeek.WEDNESDAY, "Its Wednesday!!");
		map.put(DayOfWeek.THURSDAY, "Its Thursday!!");
		map.put(DayOfWeek.FRIDAY, "Its Friday!!");
		map.put(DayOfWeek.SATURDAY, "Its Saturday!!");
		
        return map;
	}

	public void CreateEnumMap() {
		EnumMap<DayOfWeek, String> activityMap = new EnumMap<>(DayOfWeek.class);
		activityMap.put(DayOfWeek.MONDAY, "MONDAY");
		activityMap.put(DayOfWeek.TUESDAY, "TUESDAY");

		System.out.println(activityMap);
	}
	
	public void CreateEnumMap2() {
		EnumMap<DayOfWeek, String> activityMap = buildMap();
        for(final Entry<DayOfWeek, String> entry : activityMap.entrySet()){
            System.out.println( entry.getKey() + " ==> " + entry.getValue());
        }
    }
	
	public void CopyConstructor() {
		EnumMap<DayOfWeek, String> activityMap = new EnumMap<>(DayOfWeek.class);
		activityMap.put(DayOfWeek.MONDAY, "Soccer");
		activityMap.put(DayOfWeek.TUESDAY, "Basketball");

		EnumMap<DayOfWeek, String> activityMapCopy = new EnumMap<>(activityMap);
		
		System.out.println("Size = " + activityMapCopy.size());
		System.out.println("MONDAY = " + activityMapCopy.get(DayOfWeek.MONDAY));
		System.out.println("TUESDAY = " + activityMapCopy.get(DayOfWeek.TUESDAY));
	}
	
	public void Remove() {
		EnumMap<DayOfWeek, String> daysMap = this.buildMap();
		
        String value = daysMap.remove(DayOfWeek.WEDNESDAY);
        System.out.println("Removed Value: " + value);
 
   
        boolean result = daysMap.remove(DayOfWeek.SATURDAY, "Its Saturday!!");
        System.out.println("Is the entry {SATURDAY = 'Its Saturday!!'} removed ? = " + result);
        
        result = daysMap.remove(DayOfWeek.FRIDAY, "Its FRIDAY!!");
        System.out.println("Is the entry {FRIDAY = 'Its FRIDAY!!'} removed ? = " + result);
         
        System.out.println("\nUpdated EnumMap:\n" + daysMap);
	}
}

public class EnumMap_Tests {
	private final static EnumMapTester tester = new EnumMapTester();
	
	public static void main(String[] args) 
	{
		tester.CreateEnumMap();
		// tester.CreateEnumMap2();
		
		// tester.CopyConstructor();
		
		// tester.Remove();
	}
}
