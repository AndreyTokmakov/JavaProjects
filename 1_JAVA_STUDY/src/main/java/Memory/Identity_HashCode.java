/****************************************************************************
* Copyright 2021 (C) Andrey Tokmakov
* Identity_HashCode.java class
*
* @name    : Identity_HashCode.java
* @author  : Tokmakov Andrey
* @version : 1.0
* @since   : Feb 22, 2021
****************************************************************************/

package Memory;

public class Identity_HashCode {

	private final static class MyClass {
		private String name = "Unnamed";
		
		public MyClass(String name) {
			this.name = name;
		}

		public String getName() {
			return name;
		}

		public void setName(String name) {
			this.name = name;
		}

		@Override
		public int hashCode() {
			return name.hashCode();
		}

		@Override
		public boolean equals(Object obj) {
			if (this == obj) 
				return true;
			else if (null == obj || obj.getClass() != this.getClass())
				return false;
			
			MyClass x = (MyClass)obj;
			return name.equals(x.name);
		}

		@Override
		public String toString() {
			return "MyClass(" + this.name + ")";
		}
		
	}
	
	public static void main(String[] args) {
		MyClass obj1 = new MyClass("Test1");
		MyClass obj2 = new MyClass("Test2");
		MyClass obj3 = new MyClass("Test1");
		MyClass obj4 = obj1;
		
		System.out.println("[ obj1 == obj2 ] = " + (obj1 == obj2));
		System.out.println("[ obj1 == obj3 ] = " + (obj1 == obj3 ));
		
		System.out.println();
		
		System.out.println("[ obj1.equals(obj2) ] = " + (obj1.equals(obj2)));
		System.out.println("[ obj1.equals(obj3) ] = " + (obj1.equals(obj3)));
		
		System.out.println();
		
		System.out.println("[ obj1 == obj1 ] = " + (obj1 == obj1));
		System.out.println("[ obj1.equals(obj1) ] = " + (obj1.equals(obj1)));
		
		
		System.out.println();
		
		System.out.println("[ obj1.hashCode() ] = " + obj1.hashCode());
		System.out.println("[ obj2.hashCode() ] = " + obj2.hashCode());
		System.out.println("[ obj3.hashCode() ] = " + obj3.hashCode());
		System.out.println("[ obj4.hashCode() ] = " + obj4.hashCode());
		
		System.out.println();
		
		System.out.println("[ obj1.identityHashCode() ] = " + System.identityHashCode(obj1));
		System.out.println("[ obj2.identityHashCode() ] = " + System.identityHashCode(obj2));
		System.out.println("[ obj3.identityHashCode() ] = " + System.identityHashCode(obj3));
		System.out.println("[ obj4.identityHashCode() ] = " + System.identityHashCode(obj4));
	}
}
