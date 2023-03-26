package Function;

import java.util.function.Function;

/********************** Test interfaces: *********************/

interface Printable {
    public void print(final String text);
}

interface Operationable {
	public int calculate(int x, int y);
}

interface TemplateOperationable<T> {
	public T calculate(T x, T y);
}

interface Validator {
	public boolean isEven(int n);
}

/********************** User class: *********************/

class User {
    private String name;
    
    public User(final String name){
    	System.out.println(this.getClass().getName() + " constructed");
        this.name = name;
    }
    
    public String getName(){
        return this.name;
    }
}

interface UserBuilder {
    public User create(final String name);
}

/***************** LambdaFunctions test class: ****************/

public class LambdaFunctions {
	
    public void Lambda_TEST() {
       final Function<Integer, String> convert = x-> String.valueOf(x + " Dollars");
       System.out.println(convert.apply(5));
    }
    
    public void Operationable_Lambda_Tests() {
    	Operationable operation1 = (int x, int y)-> x + y;
    	Operationable operation2 = (int x, int y)-> x - y;
    	Operationable operation3 = (int x, int y)-> x * y;
    	         
    	System.out.println(operation1.calculate(20, 10)); //30
    	System.out.println(operation2.calculate(20, 10)); //10
    	System.out.println(operation3.calculate(20, 10)); //200
    }
    
    public void TemplateOperationable_Lambda_Tests() {
    	TemplateOperationable<Integer> operation1 = (x, y)-> x + y;
    	TemplateOperationable<String> operation2 = (x, y) -> x + y;
         
        System.out.println(operation1.calculate(20, 10)); //30
        System.out.println(operation2.calculate("20", "10")); //2010
    }
    
    public void Printable_Lambda_Tests() {
    	 Printable printer = (str) -> System.out.println(str);
    	 printer.print("TEST+TEST");
    }
    
    
    private int sum(final int[] numbers, Validator validator) {
        int result = 0;
        for(int i : numbers) {
        	if (validator.isEven(i))
        		result += i;
        }
        return result;
    }
    
    public void Sum_EVEN_Lambda() {
    	final int[] nums = {1,2,3,4,5,6,7,8,9};
    	int result = sum(nums, (int v) -> {return v % 2 == 0 ? true: false;} );
    	System.out.println(result);
    }
    
    public void Sum_ODD_Lambda() {
    	final int[] nums = {1,2,3,4,5,6,7,8,9};
    	int result = sum(nums, (int v) -> {return v % 2 != 0 ? true: false;}) ;
    	System.out.println(result);
    }
    
    public void Lambda_Constructor() {
        UserBuilder userBuilder = User::new;
        User user = userBuilder.create("Tom");
        System.out.println(user.getName());
    }
    
	///////////////////////////// MAIN //////////////////////////////
	public static void main(String[] args) {
		LambdaFunctions tests = new LambdaFunctions();
		
		 tests.Lambda_TEST ();
		// tests.Operationable_Lambda_Tests();
		// tests.TemplateOperationable_Lambda_Tests();
		// tests.Printable_Lambda_Tests();
		
		// tests.Sum_EVEN_Lambda();
		// tests.Sum_ODD_Lambda();
		
		// tests.Lambda_Constructor();
	}
}
