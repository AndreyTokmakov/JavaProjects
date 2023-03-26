package ObjectOrientedProgramming.Compare;

class Base { 
    public static void show() { 
       System.out.println("Base::show() called"); 
    } 
} 
   
class Derived extends Base { 
    public static void show() { 
       System.out.println("Derived::show() called"); 
    } 
}

public class StaticMethodOverride {
	public static void main(String[] args) {
		Base b = new Base();
		b.show();
		
		Base d = new Derived();
		d.show();

	}
}
