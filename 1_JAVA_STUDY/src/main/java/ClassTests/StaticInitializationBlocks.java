package ClassTests;

import java.util.LinkedList;
import java.util.List;

class TestClass1 {
	protected static List<Character> chars;
	
	static {
		chars = new LinkedList<Character>();
		for (char c = 'A'; c <= 'Z'; ++c) {
			chars.add( c );
		}
		System.out.println("Chars List initialized: Size = " + chars.size());
	}
	
	public TestClass1() {
		System.out.println("TestClass1::TestClass1()");
	}
}

class Bar {
	{
		System.out.println("Bar. OK ");
	}
	
	public Bar() {
		System.out.println("Bar::Bar()");
	}
}


public class StaticInitializationBlocks {
	public static void main(String[] args) {
		TestClass1 T = new TestClass1();
		Bar b = new Bar();
	}
}
