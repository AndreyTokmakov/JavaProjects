package Generics;

import java.util.Arrays;
import java.util.List;

public class Wildcards_Tests {

    public static void main(String[] args)  { 
        List<Integer> list1 = Arrays.asList(1,2,3); 
        List<Double> list2 = Arrays.asList(1.1,2.2,3.3); 
  
        printlist(list1); 
        printlist(list2); 
        printlist_Genreric(list1); 
        printlist_Genreric(list2); 
    } 
  
    private static void printlist(List<?> list)  { 
        System.out.println(list); 
    } 
    
    private static<Type> void printlist_Genreric(List<Type> list)  { 
        System.out.println(list); 
    } 
}
