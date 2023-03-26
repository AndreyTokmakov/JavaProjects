package ObjectOrientedProgramming.Inheritance;

interface IFace1 {
	public default void info1() {
		System.out.println("IFace1::info1()");
	}
}

interface IFace2 {
	public default void info2() {
		System.out.println("IFace2::info2()");
	}
}

class Base_1 {
	public void BaseInfo1() {
		System.out.println("Base_1::BaseInfo1()");
	}
}

class Base_2 {
	public void BaseInfo2() {
		System.out.println("Base_2::BaseInfo2()");
	}
}

// Error here --> because 'extends Base_1, Base_2 '
// class Derived extends Base_1, Base_2 implements IFace1, IFace2 { }

class Derived extends Base_1 implements IFace1, IFace2 {
	
}

public class InheritanceTests {
	public static void main(String[] args) {
		Derived d = new Derived();
		d.info1();
		d.info2();
		d.BaseInfo1();
	}
}
