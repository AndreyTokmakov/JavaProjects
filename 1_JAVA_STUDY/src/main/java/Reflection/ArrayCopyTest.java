package Reflection;

import java.lang.reflect.Array;
import java.util.Arrays;

public class ArrayCopyTest {

	public static Object goodCopyOf(Object a, int newLength) {
		Class<? extends Object> cl = a.getClass();
		if (!cl.isArray()) 
			return null;
		Class<?> componentType = cl.getComponentType();
		int length = Array.getLength(a);
		Object newArray = Array.newInstance(componentType, newLength);
		System.arraycopy(a, 0, newArray, 0, Math.min(length, newLength));
		return newArray;
	}
	
	public static void main(String[] args) 
	{
		{
			int[] src = { 1, 2, 3 };
			int[] dst = (int[]) goodCopyOf(src, 5);
			
			System.out.print(Arrays.toString(src) + " --> ");
			System.out.println(Arrays.toString(dst));
		}
		{
			String[] src = { "Tom", "Dick", "Harry" };
			String[] dst = (String[]) goodCopyOf(src, 5);
			
			System.out.print(Arrays.toString(src) + " --> ");
			System.out.println(Arrays.toString(dst));
		}
	}
}
