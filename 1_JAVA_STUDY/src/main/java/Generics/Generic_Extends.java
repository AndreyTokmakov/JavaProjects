package Generics;

import java.io.Serializable;
import java.util.ArrayList;

class BasePair<T> {
	private T first;
	private T second;
	
	public BasePair() { 
		first = null; 
		second = null;
	}
	
	public BasePair(T first, T second) {
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
		BasePair<T> right = (BasePair<T>) obj;
		return this.first.equals(right.first) && this.second.equals(right.second);
	} 
	
	@Override
	public int hashCode() {
	   return first.hashCode() + second.hashCode();  
	}
}

class PairStorage1<Type extends BasePair<?>> extends ArrayList<Type> {
	/** */
	private static final long serialVersionUID = -8751416004731550500L;
}

class PairStorageEx<Type extends BasePair<?> & Serializable> extends ArrayList<Type>{
	/** */
	private static final long serialVersionUID = -8751416004731550500L;
}


public class Generic_Extends {
	// T extends Comparable & Serializable
	public static void main(String[] args) {

		PairStorage1<BasePair<String>> strPairList = new PairStorage1<BasePair<String>>();
		
		// TODO: ERROR HERE
		// PairStorageEx<BasePair<String>> strPairList_Serializable= new PairStorageEx<BasePair<String>>();
	}
}
