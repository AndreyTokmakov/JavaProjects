package Generics;

import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.Arrays;

///////////////////////////////////////////

class Base {
	protected String name;
	protected int value;
	
	public Base(final String name, int value) {
		super();
		this.name = name;
		this.value = value;
	}

	public String getName() {
		return this.name;
	}
	
	public void setName(String name) {
		this.name = name;
	}
	
	public int getValue() {
		return this.value;
	}
	
	public void setValue(int value) {
		this.value = value;
	}
	
	public void Info() {
		System.out.println(String.format("Base. {%s, %d}", name, value));
	}
};

class Derived1 extends Base {
	
	public Derived1(final String name, int value) {
		super(name, value);
	}
	
	@Override
	public void Info() {
		System.out.println(String.format("Derived1. {%s, %d}", name, value));
	}
}

class Derived2 extends Base {
	
	public Derived2(final String name, int value) {
		super(name, value);
	}
	
	@Override
	public void Info() {
		System.out.println(String.format("Derived2. {%s, %d}", name, value));
	}
}

class Derived3 {
	protected String name;
	protected int value;
	
	public Derived3(final String name, int value) {
		super();
		this.name = name;
		this.value = value;
	}
	
	public void Info() {
		System.out.println(String.format("Derived3. {%s, %d}", name, value));
	}
}

class Storage<Type extends Base> extends ArrayList<Type>{
	/** */
	private static final long serialVersionUID = -8751416004731550500L;
}

///////////////////////////////////////////

class Variable<T> { 
    protected T val; 
    
    public Variable(final T val) {  
    	this.val = val; 
    } 
    
    public T getValue()  { 
    	return this.val; 
    }
    
    public void setValue(final T val)  { 
    	this.val = val; 
    } 
    
    @Override
    public String toString() {
		return String.valueOf(this.val);
    }
} 


public class GenericsTests {
	
	protected <T> void gereric_printer(final T element)  { 
        System.out.println(element.getClass().getName() + " = " + element); 
    } 
	
	protected void Generics_Extends() {
		Storage<Derived2> storage = new Storage<Derived2>();
		storage.add(new Derived2("Val1", 1));
		storage.add(new Derived2("Val2", 2));
		storage.add(new Derived2("Val3", 3));
		
		for (final Derived2 obj: storage)
			obj.Info();
	}
	
	protected void Generics_Extends_ERROR() {
		/*
		WE'LL GET ERROR HERE

		Storage<Derived3> storage = new Storage<Derived3>();
		*/
	}
	
	/*
	@SuppressWarnings("unchecked")
	private <T extends Object> T[] CreateGenericIntArray(int size) {
		T[] numbers = (T[]) Array.newInstance(T., 3);
		return numbers;
	}
	*/
	
	public static void main(String[] args) {
		GenericsTests tests = new GenericsTests();

		tests.gereric_printer("Testssss");
		tests.gereric_printer(12345);
		tests.gereric_printer(new Variable("SomeTestValue"));

		
		// tests.Generics_Extends();
	}
}
