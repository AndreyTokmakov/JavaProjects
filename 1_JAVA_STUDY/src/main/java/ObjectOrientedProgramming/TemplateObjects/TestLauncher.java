package ObjectOrientedProgramming.TemplateObjects;

public class TestLauncher {
	
	protected static TestLauncher launcher = new TestLauncher();

	protected void EmployeeClass_Tests() {
		Employee bob  = new Employee("Bob", 30);
		Employee john = new Employee("John", 31);
		
		System.out.println(bob);
		System.out.println(john);
		System.out.println("john == bob : " + john.equals(bob));
		System.out.println("bob == bob : " + bob.equals(bob));
	}
	
	public static void main(String[] args) {
		launcher.EmployeeClass_Tests();
	}
}
