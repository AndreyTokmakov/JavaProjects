/****************************************************************************
* Copyright 2020 (C) Andrey Tokmakov
* Dequeues.java class
*
* @name    : Dequeues.java
* @author  : Tokmakov Andrey
* @version : 1.0
* @since   : Dec 9, 2020
****************************************************************************/

package Deques;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashSet;
import java.util.Set;

class Deque_Algoritms {
	private final java.util.Scanner input = new java.util.Scanner(System.in); 

	protected void Test() {
		String firstList[] = input.nextLine().split(" ", 2);
		int N = Integer.valueOf(firstList[0]), M = Integer.valueOf(firstList[1]);
		ArrayList<Integer> numbers = new ArrayList<Integer>(N);
		while (N-- > 0) {
			int x = input.nextInt();
			numbers.add(x);
		}
		
		long max = 0;
		for (int i = 0; i<= numbers.size() - M; i++) {
			long len = numbers.subList(i, i + M).stream().distinct().count();
			if (len > max)
				max = len;
			if (max >= M)
				break;
		}
		System.out.println(max);
	}
}


public class Dequeues {
	private final static Deque_Algoritms algoritms = new Deque_Algoritms();
	
	public static void main(String[] args) {
		
		// System.out.println(Integer.MAX_VALUE);
		
		algoritms.Test();
	}
}
