/****************************************************************************
* Copyright 2020 (C) Andrey Tokmakov
* AbstractCollectionTests tests
*
* @name    : AbstractCollectionTests.java
* @author  : Tokmakov Andrey
* @version : 1.0
* @since   : October 25, 2020
****************************************************************************/ 

package Collections.AbstractCollections;

import java.util.AbstractCollection;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import java.util.TreeSet;

public class AbstractCollectionTests {
	
	public static void main(String[] args) {
		// Add();
		
		Sort();
		
		// AddAll_Clear();
	}
	
    public static void Sort() { 
		List<Integer> list = new ArrayList<Integer>(Arrays.asList(5,1,2,6,9,4,3,7,8));
		
		System.out.println(list);
		
		Collections.sort(list);
		System.out.println(list);
		
		Collections.sort(list, (var1, var2) -> {return var2.compareTo(var1);});
		System.out.println(list);
    } 	
	
    public static void Add() { 
        AbstractCollection<Object> collection = new ArrayList<Object>(); 
  
        collection.add("Welcome"); 
        collection.add("To"); 
        collection.add("Geeks"); 
        collection.add("4"); 
        collection.add("Geeks"); 
  
        System.out.println("AbstractCollection: " + collection); 
    } 
    
    public static void AddAll_Clear() { 
        AbstractCollection<String>  collection1 = new TreeSet<String>(); 
  
        collection1.add("Welcome"); 
        collection1.add("To"); 
        collection1.add("Geeks"); 
        collection1.add("4"); 
        collection1.add("Geeks"); 
        collection1.add("TreeSet"); 
  
        System.out.println("AbstractCollection 1: " + collection1); 
  
        // Creating anothe Collection 
        AbstractCollection<String> collection2 = new TreeSet<String>(); 

        System.out.println("AbstractCollection 2: " + collection2); 
        collection2.addAll(collection1); 
 
        System.out.println("AbstractCollection 2: " + collection2); 
  
        collection2.clear(); 
  
        // Check for the empty collection 
        System.out.println("Is the collection empty? " + collection2.isEmpty()); 
    } 
}
