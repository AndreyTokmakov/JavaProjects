/****************************************************************************
* Copyright 2020 (C) Andrey Tokmakov
* OverrideEquals tests
*
* @name    : OverrideEqualsTests.java
* @author  : Tokmakov Andrey
* @version : 1.0
* @since   : October 22, 2020
****************************************************************************/ 

package ObjectOrientedProgramming.Equal_Override;

class BaseObject {
	protected String value;
	
	public BaseObject(final String str) {
		this.value = str;
	}
	
    // Copy constructor 
    public BaseObject(final BaseObject obj) { 
        this.value = obj.value; 
    } 
       
	public String getValue() {
		return this.value;
	}

	public void setValue(final String name) {
		this.value = name;
	} 
	
    // Overriding the toString of BaseObject class 
    @Override
    public String toString() { 
        return String.format("BaseObject(%s)", this.value);
    }

    // Overriding the equals of BaseObject class 
	@Override
	public boolean equals(final Object obj) {
		if (this == obj) 
			return true;
		else if (null == obj || obj.getClass() != this.getClass())
			return false;

		/*
		// BAD: BaseClass could be equal DerivedClass but they are not
		if (false == (obj instanceof BaseObject))
			return false;
		*/
		
		/*
		 * if (null == obj || false == this.getClass().isAssignableFrom(obj.getClass())) 
            return false;
		*/
		return this.value.equals(((BaseObject)obj).value);
	} 
	
	@Override
	public int hashCode() {
	   int result = value.hashCode();  
	   // result = 31 * result + id;    
	   // result = 31 * result + value; 
	   return result;
	}
}


class DerivedObject extends BaseObject {

	public DerivedObject(final String str) {
		super(str);
	}
	
	public DerivedObject(final DerivedObject obj) {
		super(obj);
	}
	
    // Overriding the equals of BaseObject class 
	@Override
	public boolean equals(final Object obj) {
		if (false == (obj instanceof DerivedObject))
			return false;
		return this.value.equals(((BaseObject)obj).value);
	} 
}

/////////////////////////////////////////////////////////////////////////////

public class OverrideEqualsTests {
	
	protected static void SimpleTest() {
		
		BaseObject obj1 = new BaseObject("12345");
		BaseObject obj2 = new BaseObject("12345");
		BaseObject obj3 = new BaseObject("54321");
		
		System.out.println("Compare three BaseObject objects:");
		
		System.out.println(obj1.equals(obj1));
		System.out.println(obj1.equals(obj2));
		System.out.println(obj1.equals(obj3));
		System.out.println(obj1.equals(null));
		
		System.out.println("\nCompare DerivedObject and BaseObject objects:");
		
		DerivedObject obj4 = new DerivedObject("12345");
		System.out.println(obj1.equals(obj4));
	}
	
	public static void main(String[] args) {
		SimpleTest();
	}
}
