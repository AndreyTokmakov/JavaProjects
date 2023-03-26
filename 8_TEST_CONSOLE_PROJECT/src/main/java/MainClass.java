//============================================================================
// Name        : TestAppliation.java
// Created on  : June 20, 2019
// Author      : Tokmakov Andrey
// Version     : 1.0
// Copyright   : Your copyright notice
// Description : Cache file class
//============================================================================

import java.math.BigInteger;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Calendar;
import java.util.Date;
import java.util.HashSet;
import java.util.Iterator;
import java.util.List;
import java.util.Scanner;
import java.util.Stack;


class ItemSeparator {
	
	protected String name;
	protected Double price;
	protected Integer quantity;
    
	public ItemSeparator(String rawInput) {
		final String[] parts = rawInput.replace("$$##", "___").split("___");
		if (3 == parts.length) {
			this.name = parts[0];
			this.price = Double.valueOf(parts[1]);
			this.quantity = Integer.valueOf(parts[2]);
		}
 	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public Double getPrice() {
		return price;
	}

	public void setPrice(Double price) {
		this.price = price;
	}

	public Integer getQuantity() {
		return quantity;
	}

	public void setQuantity(Integer quantity) {
		this.quantity = quantity;
	}	
}

///////////////////////////////////////////////////////////////////////////////////////////////

interface Building {
	public void floorCompleted(int floorNumber);
	
	public void printStatus(int floorNumber);
	
	public void printNumberOfFloors();
}


class School implements Building {
	
	private int[] floors = null;
	
	public School(int n) {
		this.floors = new int[n];
		System.out.println("A school is being constructed");
	}

	@Override
	public void floorCompleted(int floorNumber) {
		if (floors.length >= floorNumber) {
			this.floors[floorNumber - 1] = 1;
			System.out.println("Construction for floor number " + floorNumber + " completed in school");
		} else {
			System.out.println("Floor number " + floorNumber + " does not exist in school");
		}
	}

	@Override
	public void printStatus(int floorNumber) {
		if (floorNumber > floors.length) {
			System.out.println("Floor number " + floorNumber + " does not exist in school");
			return;
		}
		
		if (1 == this.floors[floorNumber - 1]) {
			System.out.println("Construction for floor number " + floorNumber + " completed in school");
		}
		else {
			System.out.println("Construction for floor number " + floorNumber + " not completed in school");
		}
	}

	@Override
	public void printNumberOfFloors() {

		System.out.println("The school has " + floors.length + " floors");
	}
}

class Hospital implements Building {
	
	private int[] floors = null;
	
	public Hospital(int n) {
		this.floors = new int[n];
		System.out.println("A hospital is being constructed");
	}

	@Override
	public void floorCompleted(int floorNumber) {
		if (floors.length >= floorNumber) {
			this.floors[floorNumber - 1] = 1;
			System.out.println("Construction for floor number " + floorNumber + " completed in hospital");
		} else {
			System.out.println("Floor number " + floorNumber + " does not exist in hospital");
		}
		
	}

	@Override
	public void printStatus(int floorNumber) {
		if (floorNumber > floors.length) {
			System.out.println("Floor number " + floorNumber + " does not exist in hospital");
			return;
		}
		
		if (1 == this.floors[floorNumber - 1]) {
			System.out.println("Construction for floor number " + floorNumber + " completed in hospital");
		}
		else {
			System.out.println("Construction for floor number " + floorNumber + " not completed in hospital");
		}
	}

	@Override
	public void printNumberOfFloors() {
		System.out.println("The hospital has " + floors.length + " floors");
		
	}
}


///////////////////////////////////////////////////////////////////////////////////////////////

class IteratorTest {
	protected Iterator<String> func(ArrayList<String> mylist){
		Iterator<String> it = mylist.iterator();
		while (it.hasNext()) {

			
			 /*
	         Object element = ~~~Complete this line~~~
	         if (~~~Complete this line~~~) //Hints: use instanceof operator

				break;
				*/
			}
		return it;
	}
	
	public void Test () {
		ArrayList<String> mylist = new ArrayList<String>();
		
		mylist.add("42");
		mylist.add("10");
		mylist.add("###");
		mylist.add("Hello");
		mylist.add("Java");

		
		Iterator<String> it = func(mylist);
		while (it.hasNext()) {
			Object element = it.next();
			System.out.println((String)element);
	    }
	}
}


class TestLoops {
	
	public void Test1() {
		int a = 5;
		int b = 3;
		int n = 5;
	
		int m = 1;
		for (int i = 0; i < n; i++) {
			a += b * m;
			b*= 2;
			System.out.print(a + " ");
		}
		System.out.println();
	}
}




class StringTests {
	private final Scanner input = new Scanner(System.in);
	
	public void Test1() {
		String line1 = input.nextLine();
		String line2 = input.nextLine();
		
		System.out.println(line1.length() + line2.length());
		
		if (line1.compareTo(line2) > 0)
			System.out.println("Yes");
		else 
			System.out.println("No");
		
		System.out.print(line1.toUpperCase().charAt(0) + line1.substring(1, line1.length()) + " " + line2.toUpperCase().charAt(0) + line2.substring(1, line2.length())); 
	}
	
	public void IsStringPalindrome() {
		String line = input.nextLine();
		final String reversed = new StringBuffer(line).reverse().toString();		
		if (line.compareTo(reversed) > 0)
			System.out.println("Yes");
		else 
			System.out.println("No");
		
	}
	
    public void End_of_file() {
        final Scanner input = new Scanner(System.in);
        int counter = 0;
        while (input.hasNext()) {
            final String line = input.nextLine();
            System.out.println(++counter + " " + line);
        }
    }
}

class DataTimeTests {
	
	public void GetDay() {
		final SimpleDateFormat dateFormater = new SimpleDateFormat("yyyy-MM-dd");
		Date startTimestamp;
		try {
			startTimestamp = dateFormater.parse("2019-07-07");
			System.out.println(startTimestamp);
			System.out.println(startTimestamp.toString());
		} catch (ParseException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	public void GetWeekDay() {
		LocalDate localDate = LocalDate.of(2020, 5,4);
		System.out.println(localDate.getDayOfWeek().toString());
		
		//Calendar c = Calendar.getInstance();
		//c.setTime(localDate.T);
		//int dayOfWeek = c.get(Calendar.DAY_OF_WEEK);
	}
	
	
	class Interval {
		protected int from;
		protected int until;
		
		public Interval(final String from, final String until) {
			this.from = Integer.valueOf(from);
			this.until = Integer.valueOf(until);
		}
		
		public Interval(int from, int until) {
			this.from = from;
			this.until = until;
		}

		public int getFrom() {
			return this.from;
		}

		public void setFrom(final String from) {
			this.from = Integer.valueOf(from);
		}

		public int getUntil() {
			return this.until;
		}

		public void setUntil(final String until) {
			this.until = Integer.valueOf(until);
		}
	
		@Override
		public String toString() {
			return from + " - " + until;
		}
		
		@Override
		public boolean equals(final Object object) {
			Interval in = (Interval)object;
			return this.from == in.from && in.until == this.until;
			
		}
		
		public boolean isIntervalIncluded(final Interval interval) {
			return this.from <= interval.getFrom() && this.until >= interval.getUntil();
		} 
		
		public boolean isOverlaps(final Interval interval) {
			if (true == isIntervalIncluded(interval))
				return true;
			if (this.from > interval.until || this.until <  interval.from)
				return false;
			return true;
		}
		
		public boolean extend(final Interval interval) {
			if (false == isOverlaps(interval))
				return false;
			if (this.from > interval.from)
				this.from  = interval.from;
			if (this.until < interval.until)
				this.until  = interval.until;
			return true;
		}
	}
	
	public List<Interval> getAvailableIntervals(final List<Interval> calendar) {
		List<Interval> result = new ArrayList<Interval>();
		Interval previous = calendar.get(0);
		for (final Interval interval: calendar) {
			if (previous.getUntil() < interval.getFrom())
				result.add(new Interval(previous.getUntil(), interval.getFrom()));
			previous = interval;
		}
		return result;
	}

	public void Calendars_GetFreeIntervals() {
		
		List<Interval> user1Calendar = Arrays.asList(new Interval("14" ,"15"), new Interval("16" ,"17"), new Interval("17" ,"18"));
		List<Interval> user2Calendar = Arrays.asList(new Interval("16" ,"17"), new Interval("17" ,"18"));
		
		/*
		for (final Interval user2TimeSlot: user2Calendar) {
			for (final Interval user1TimeSlot: user1Calendar) {
				if (true == user2TimeSlot.isIntervalIncluded(user1TimeSlot))
						System.out.println(user2TimeSlot);
				
			}
		}*/
		
		/*
		Interval interval1 = new Interval("13" ,"18");
		Interval interval2 = new Interval("18" ,"22");
		
		boolean ovelaps = interval1.isOverlaps(interval2);
		System.out.println(ovelaps);
		
		interval1.extend(interval2);
		System.out.println(interval1);
		*/
		
		List<Interval> availableSlots =  getAvailableIntervals(user1Calendar);
		for (final Interval slot: availableSlots) 
			System.out.println(slot);
	}
}

class NumbersTests {
	
	public boolean isPrime(int n) {
	    /** check if n is a multiple of 2. **/
	    if (0 == n % 2)
	    	return false;
	    /** if not, then just check the odd. **/
	    for(int i = 3; i * i <= n; i += 2) {
	        if (0 == n % i)
	            return false;
	    }
	    return true;
	}
	
	public void ReadInput_CheckIsPrime() {
		final Scanner scanner = new Scanner(System.in);
		BigInteger n = scanner.nextBigInteger();
		scanner.close();
		System.out.println(n.isProbablePrime(1) ? "prime" :"not prime"); 
    }
	
	void CheckIfNumberIsPrime() {
		System.out.println(isPrime(13));
	}
}

class InterviewQuestions {
	
	public void Walk_Up_The_Stairs_Alghorithm(int[] steps_size, int steps_max) {
		
		int tmp = steps_max;
		for (int size : steps_size) {
			tmp = tmp % size;
			
		}
		
	}
	
	public void Walk_Up_The_Stairs() {
		int[] steps_size = {1, 3 ,5};
		
		Walk_Up_The_Stairs_Alghorithm(steps_size, 8);
		
		
	}
}


class Array_Matrix_Tests {
	
	private static void printArray(final int[][] matrix, int size) {
		for (int i = 0; i < size; i++) {
			for (int n = 0; n < size; n++) 
				System.out.format("%-5d", matrix[i][n]);
			System.out.println();
		}
	}
	
	private static int array2DSum(final int[][] matrix, int size) {
		int result = 0;
		for (int i = 0; i < size; i++) {
			for (int n = 0; n < size; n++) {
				result += matrix[i][n];
			}
		}
		return result;
	}
	
	private static int array2DSumMax(final int[][] matrix, int size) {
		int max_summ = 0;
		for (int i = 0; i <= (size - 3); i++) {
			for (int n = 0; n <= (size - 3); n++) {
				int summ = array2DSum(matrix, 3);
				if (summ > max_summ)
					max_summ = summ;
			}
		}
		return max_summ;
	}
	
	private static int array2DSumMax2(final int[][] matrix, int size) {
		int max_summ = 0;
		for (int i = 0; i <= (size - 3); i++) {
			for (int j = 0; j <= (size - 3); j++) {
				int summ = 0;
				for (int m = i; m < (3 + i); m++) {
					for (int n = j; n < (3 + j); n++) {
						if ((i + 1) == m && (n == j || (2 + j) ==n ))
							continue;
						summ += matrix[m][n];
					}
				}
				if (summ > max_summ)
					max_summ = summ;
			}
		}
		return max_summ;
	}
	
	public static void Java2DArray_Test() {
		int[][] matrix = new int[3][3];
		matrix[0][0] = 2;
		matrix[1][0] = 1;
		matrix[2][0] = 1;
		
		matrix[0][1] = 4;
		matrix[1][1] = 2;
		matrix[2][1] = 2;
		
		matrix[0][2] = 4;
		matrix[1][2] = 1;
		matrix[2][2] = 4;
		
		printArray(matrix, 3);
		System.out.println(array2DSumMax2(matrix, 3));
	}
}

class Algoritms {
	
	public static boolean checkParentheses(final String str) {
		Stack<Character> stack = new Stack<Character>();
		for (int i = 0; i < str.length(); i++) {
			char c = str.charAt(i);
			if ('(' == c || '{' == c || '[' == c) {
				stack.add(c);
			} 
			else {
				if (true == stack.isEmpty()) 
					return false;
				char open = stack.pop();
				if (')' == c && '(' != open) {
					break;
				} else if ('}' == c && '{' != open) {
					return false;
				} else if (']' == c && '[' != open) {
					return false;
				}
			}
		}
		return stack.isEmpty();
	}
	
	public static void CalcParentheses() {
		String line = "((()))))";
		System.out.println(checkParentheses(line));
	}
}

/////////////////////////////////////////////////////////////////////

class HackerRankTests {
	private final static Scanner input = new Scanner(System.in);
	
	private final static String insertQuery = "Insert";
	private final static String deleteQuery = "Delete";
	
	protected void HashSetTest() {
		int count = input.nextInt();
		HashSet<String> set = new HashSet<String>();
		while (count-- > 0) {
			set.add(input.nextLine());
			System.out.println(set.size());
		}
	}
	
	protected void JavaList() {
		String line = input.nextLine();
		ArrayList<Integer> list = new ArrayList<Integer>();
		line = input.nextLine();
		for (String s: line.split(" "))
			list.add(Integer.valueOf(s));
		
		line = input.nextLine();
		int count = Integer.valueOf(line);
		while (count-- > 0) {
			line = input.nextLine();
			if (true == line.equals(insertQuery)) {
				line = input.nextLine();
				String[] parts = line.split(" ", 2);
				list.add(Integer.valueOf(parts[0]), Integer.valueOf(parts[1]));
			} else if (true == line.equals(deleteQuery)) {
				line = input.nextLine();
				System.out.println(list);
				System.out.println("Delete: " + Integer.valueOf(line));
				list.remove(Integer.valueOf(line).intValue());
				System.out.println(list);
			}
		}
		list.forEach(v -> System.out.print(v + " "));
	}
}


/////////////////////////////////////////////////////////////////////

public class MainClass {
	/** Main. **/
	public static void main(String[] args) {
		
		
		
	
		// Algoritms.CalcParentheses();
		
		// HackerRankTests hackerRankTests = new HackerRankTests();
		// hackerRankTests.HashSetTest();
		// hackerRankTests.JavaList();
		
		
		// Array_Matrix_Tests.Java2DArray_Test();

		// InterviewQuestions Q = new InterviewQuestions();
		// Q.Walk_Up_The_Stairs();
		
		// NumbersTests tests = new NumbersTests();
		// tests.CheckIfNumberIsPrime();
	
		// DataTimeTests date_tests = new DataTimeTests();
		// date_tests.GetDay();
		// date_tests.GetWeekDay();
		// date_tests.Calendars_GetFreeIntervals();
		
		// StringTests tests = new StringTests();
		// tests.Test1();
		// tests.IsStringPalindrome();
		
		
		/*
		System.out.println("TestApplication");
		ItemSeparator sep = new ItemSeparator("Bread$$##12.5$$##10");
		System.out.println(sep.getName());
		System.out.println(sep.getPrice());
		System.out.println(sep.getQuantity());*/
		

		/*
		
		School school = new School(2000);
		school.printNumberOfFloors();
		
		school.floorCompleted(99);
		school.floorCompleted(830);
		school.floorCompleted(-182);
		*/
		
	}
}
