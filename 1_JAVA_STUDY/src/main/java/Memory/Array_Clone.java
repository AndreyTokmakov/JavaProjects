/****************************************************************************
* Copyright 2020 (C) Andrey Tokmakov
* Array_Clone.java class
*
* @name    : Array_Clone.java
* @author  : Tokmakov Andrey
* @version : 1.0
* @since   : Dec 16, 2020
****************************************************************************/

package Memory;

public class Array_Clone {
	
	public static void CopyArray() { 
		int[] sourceArr = {0, 1, 2, 3, 4, 5, 6, 7, 8, 9};
		int[] destArr = new int[sourceArr.length];
		
		System.arraycopy(sourceArr, 0, destArr, 0, sourceArr.length);
		for (var i = 0; i < destArr.length; i++) {
			System.out.print(destArr[i] + " ");
		}
    }
	
	public static void CopyArray_Integer() { 
		Integer[] sourceArr = {0, 1, 2, 3, 4, 5, 6, 7, 8, 9};
		Integer[] destArr = new Integer[sourceArr.length];
		
		System.arraycopy(sourceArr, 0, destArr, 0, sourceArr.length);
		for (var i = 0; i < destArr.length; i++) {
			System.out.print(destArr[i] + " ");
		}
    }
	
	
	public static void main(String[] arguments) {
		// CopyArray();
		CopyArray_Integer();
	}
}
