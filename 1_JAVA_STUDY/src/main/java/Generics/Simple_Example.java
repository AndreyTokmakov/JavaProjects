/****************************************************************************
* Copyright 2020 (C) Andrey Tokmakov
* Java generic example tests
*
* @name    : Simple_Example.java
* @author  : Tokmakov Andrey
* @version : 1.0
* @since   : October 24, 2020
* 
****************************************************************************/ 

package Generics;

final class Pair<T> {
	private T first;
	private T second;
	
	public Pair() { 
		first = null; 
		second = null;
	}
	
	public Pair(T first, T second) {
		this.first = first;
		this.second = second; 
	}
	
	public T getFirst() { 
		return first;
	}
	
	public T getSecond() { 
		return second;
	}
	
	public void setFirst(T newValue) { 
		first = newValue;
	}
	
	public void setSecond(T newValue) { 
		second = newValue;
	}
	
    // Overriding the toString of BaseObject class 
    @Override
    public String toString() { 
        return String.format("{%s, %s}", this.first, this.second);
    }

    // Overriding the equals of BaseObject class 
	@Override
	public boolean equals(final Object obj) {
		if (this == obj) 
			return true;
		else if (null == obj || obj.getClass() != this.getClass())
			return false;
		
		@SuppressWarnings("unchecked")
		Pair<T> right = (Pair<T>) obj;
		return this.first.equals(right.first) && this.second.equals(right.second);
	} 
	
	@Override
	public int hashCode() {
	   return first.hashCode() + second.hashCode();
	}
}

public class Simple_Example {
	public static void main(String[] args) {
		Pair<String>  strPair1 = new Pair<String>("First", "Second");
		Pair<String>  strPair2 = new Pair<String>("First", "Second");
		
		Pair<Integer> intPair1 = new Pair<Integer>(1, 2);
		Pair<Integer> intPair2 = new Pair<Integer>(1, 2);
		
		System.out.println(strPair1);
		System.out.println(strPair2);
		
		System.out.println(intPair1);
		System.out.println(intPair2);
		
		System.out.println(strPair2.equals(strPair1));
		System.out.println(intPair1.equals(intPair2));
		System.out.println(intPair1.equals(strPair1));
	}
}
