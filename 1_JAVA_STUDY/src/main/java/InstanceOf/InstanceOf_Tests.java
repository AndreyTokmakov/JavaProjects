/****************************************************************************
* Copyright 2021 (C) Andrey Tokmakov
* InstanceOf_Tests.java class
*
* @name    : InstanceOf_Tests.java
* @author  : Tokmakov Andrey
* @version : 1.0
* @since   : Jan 30, 2021
****************************************************************************/

package InstanceOf;

public class InstanceOf_Tests {

	private abstract static class Base {
		protected abstract void handle();
	}
	
	private final static class DerivedOne extends Base {
		
		@Override
		protected void handle() {
			System.out.println(this.getClass().getName());
		}
	}
	
	private final static class DerivedTwo extends Base {
		
		@Override
		protected void handle() {
			System.out.println(this.getClass().getName());
		}
	}
	
	@SuppressWarnings("preview")
	public static void main(String[] args) {
		Base obj = new DerivedOne();

		/* FAILED TO COMPILE IN IDEA
		if (obj instanceof DerivedOne d1 && d1.handle()) {
			d1.handle();
		}
		
		if (obj instanceof DerivedTwo d1 && d1.handle()) {
			d1.handle();
		}
		*/
	}
}
