package ObjectOrientedProgramming;

interface Animal {
    public String move();
    
    default String sleep() {
        return ("sleep");
    }
}

class Cat implements Animal {
    @Override
    public String move() {
        return "run";
    }
}

class Dog implements Animal {
    @Override
    public String move() {
        return "run";
    }
    
    @Override
    public String sleep() {
        return "Dog sleep";
    }
}

/////////////////////////////////////////////

interface IObjectWithMethods {
	
	default public void Test() {
		System.out.println("Methods defined in IObjectWithMethods inteface");
		
	}
}


public class InterfacesTests {
	
	protected void TestDefaultMethod() {
		Cat cat = new Cat();
		System.out.println(cat.move());
		System.out.println(cat.sleep());
		
		Dog dog = new Dog();
		System.out.println(dog.move());
		System.out.println(dog.sleep());
	}
	
	/******************  MAIN  *****************/
	public static void main(String[] args) {
		InterfacesTests tests = new InterfacesTests();
		tests.TestDefaultMethod();
	}
}
