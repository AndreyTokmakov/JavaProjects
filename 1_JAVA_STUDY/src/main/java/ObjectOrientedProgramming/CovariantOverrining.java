package ObjectOrientedProgramming;

class TestBase {
	public TestBase Create() {
		return new TestBase();
	}
};

class Derived1 extends TestBase {
	@Override
	public Derived1 Create() {
		return new Derived1();
	}
};

class Derived2 extends TestBase {
	@Override
	public TestBase Create() {
		return new Derived2();
	}
};


public class CovariantOverrining {
	
	protected void Test() {
		TestBase base1 = new Derived1();
		System.out.println(base1.getClass().getName());
		
		
		TestBase base2 = new Derived2();
		System.out.println(base2.getClass().getName());
	}
	
	public static void main(String[] args) {
		CovariantOverrining tests = new CovariantOverrining();
		tests.Test();
	}
}
