package ObjectOrientedProgramming.Equal_Override;

public class IsAssinable_Tests {
	
	static class Base {
		protected String name = "";
		
		public Base(String name) {
			this.name = name;
		}

		public String getName() {
			return name;
		}

		public void setName(String name) {
			this.name = name;
		}
	}; 
	
	static class Derived extends Base {

		public Derived(String name) {
			super(name);
		}
	}
	
	
	public static void main(String[] args) {
		Base base = new Base("BaseObject");
		Derived derived = new Derived("DerivedObject");
		
		System.out.println("is Base assignable from Derived: " + base.getClass().isAssignableFrom(derived.getClass()));
		System.out.println("is Derived assignable from Base: " + derived.getClass().isAssignableFrom(base.getClass()));
		
		System.out.println();
		
		System.out.println("is derived instance of Base: " + ((derived instanceof Base)));
		System.out.println("is base instance of Derived: " + ((base instanceof Derived)));
	}
}
