package Reflection;

import java.lang.reflect.Field;
import java.util.ArrayList;

public class ReflectionTests {
	
	static int getCapacity(final ArrayList<?> list) throws Exception {
        Field dataField = ArrayList.class.getDeclaredField("elementData");
        dataField.setAccessible(true);
        return ((Object[]) dataField.get(list)).length;
    }
    
	private static int getArrayListCapacity(final ArrayList<?> list) throws Exception{
        // get the elementData field from ArrayList class
        Field arrayField = ArrayList.class.getDeclaredField("elementData");
        
        // Since the elementData field is private, we need to make it accessible first 
        arrayField.setAccessible(true);
        
        // now get the elementData Object array from our list
        Object[] internalArray = (Object[])arrayField.get(list);
        
        // Internal array length is the ArrayList capacity!
        return internalArray.length;
    }
	
	public static void main(String[] args) throws Exception {
	       ArrayList<Integer> list = new ArrayList<Integer>(3);
	        for (int i = 0; i < 17; i++) {
	            list.add(i);
	            System.out.format("Size: %2d, Capacity: %2d%n",
	                              list.size(), getCapacity(list));
	        }

	}
}
