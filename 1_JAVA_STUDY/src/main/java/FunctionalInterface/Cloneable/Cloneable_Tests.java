/****************************************************************************
* Copyright 2020 (C) Andrey Tokmakov
* Cloneable interface tests.
*
* @name    : Cloneable_Tests.java
* @author  : Tokmakov Andrey
* @version : 1.0
* @since   : October 22, 2020
****************************************************************************/ 

package FunctionalInterface.Cloneable;

import java.util.Date;
import java.util.GregorianCalendar;

class Point implements Cloneable {
	protected int x;
	protected int y;

	public Point(int x, int y) {
		this.x = x;
		this.y = y;
	}
	
	@Override
	public Point clone() throws CloneNotSupportedException {
		return (Point) super.clone();
	}
	
    // Overriding the toString of Point class 
    @Override
    public String toString() { 
        return String.format("{%d, %d}", this.x, this.y);
    }

    // Overriding the equals of Point class 
	@Override
	public boolean equals(final Object obj) {
		if (this == obj) 
			return true;
		else if (null == obj || obj.getClass() != this.getClass())
			return false;
		
		final Point pt = (Point)obj;
		return this.x == pt.x && this.y == pt.y;
	} 
	
	// Overriding the hashCode() of Point class 
	@Override
	public int hashCode() {
	   int result = 7;
	   result = 31 * result + this.x;    
	   result = 31 * result + this.y; 
	   return result;
	}
}


class Employee implements Cloneable {
	private String name;
	private double salary;
	private Date hireDay;
	
	public Employee(String name, double salary) {
		this.name = name;
		this.salary = salary;
		hireDay = new Date();
	}

	@Override
	public Employee clone() throws CloneNotSupportedException {
		// call Object.clone()
		Employee cloned = (Employee) super.clone();
		// clone mutable fields
		cloned.hireDay = (Date) hireDay.clone();
		return cloned;
	}
	
	/**
	* Set the hire day to a given date.
	* @param year the year of the hire day
	* @param month the month of the hire day
	* @param day the day of the hire day
	*/
	public void setHireDay(int year, int month, int day) {
		Date newHireDay = new GregorianCalendar(year, month - 1, day).getTime();
		// Example of instance field mutation
		hireDay.setTime(newHireDay.getTime());
	}
	
	public void raiseSalary(double byPercent) {
		double raise = salary * byPercent / 100;
		salary += raise;
	}
	
	@Override
	public String toString() {
		return "Employee[name=" + name + ",salary=" + salary + ",hireDay=" + hireDay + "]";
	}
}





public class Cloneable_Tests {
	public static void main(String[] args) throws CloneNotSupportedException {
		
		Point pt1 = new Point(10, 10);
		System.out.println(pt1);

		Point pt2 = pt1.clone();
		System.out.println(pt2);
		
		System.out.println(pt2.equals(pt1));
		System.out.println(pt2 == pt1);
	}
}
