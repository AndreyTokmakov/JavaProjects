/****************************************************************************
* Copyright 2020 (C) Andrey Tokmakov
* 
* Java Comparable interface is used to compare objects and sort them 
* according to the natural order.
*
* @name    : Comparable_Tests.java
* @author  : Tokmakov Andrey
* @version : 1.0
* @since   : October 22, 2020
****************************************************************************/ 

package FunctionalInterface.Comparator;

import java.util.Arrays;

class Employee implements Comparable<Employee> {
	private String name;
	private double salary;

	public Employee(String name, double salary) {
		this.name = name;
		this.salary = salary;
	}

	public String getName() {
		return name;
	}

	public double getSalary() {
		return salary;
	}

	public void raiseSalary(double byPercent) {
		double raise = salary * byPercent / 100;
		salary += raise;
	}

	/**
	* Compares employees by salary
	* @param other another Employee object
	* @return a negative value if this employee has a lower salary than
	* otherObject, 0 if the salaries are the same, a positive value otherwise
	*/
	public int compareTo(Employee other) {
		return Double.compare(salary, other.salary);
	}
}

public class Comparable_Tests {
	public static void main(String[] args) {
		Employee[] staff = new Employee[3];
		
		staff[0] = new Employee("Harry Hacker", 35000);
		staff[1] = new Employee("Carl Cracker", 75000);
		staff[2] = new Employee("Tony Tester", 38000);
		
		Arrays.sort(staff);
		for (Employee e : staff)
			System.out.println("name=" + e.getName() + ",salary=" + e.getSalary());
	}
}
