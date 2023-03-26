package Lambdas;

interface Operationable {
    int calculate(int x, int y);
}

interface Expression {
    boolean isEqual(int n);
}

interface DefaultValue {
	public String getDefault();
}

class ExpressionHelper{
    static boolean isEven(int n){
        return n % 2 == 0;
    }
    static boolean isPositive(int n){
        return n > 0;
    }
}

public class LambdaTestsClass {
	
    public void Test_LambdaStyle() {
        Operationable operation  = (x, y) -> x + y;
         
        int result = operation.calculate(10, 20);
        System.out.println(result); //30
    } 
    
    public void Test_OldStyle() {
    	Operationable operation = new Operationable(){
             public int calculate(int x, int y) {
                 return x + y;
             }
        };
        int result = operation.calculate(20, 30);
        System.out.println(result); //30
    }
    
    private int sum(int[] numbers, Expression func) {
        int result = 0;
        for(int i : numbers) {
            if (func.isEqual(i))
                result += i;
        }
        return result;
    }
    
    public void SumTest() {
    	Expression func = (n) -> n % 2 == 0;
        int[] nums = { 1, 2, 3, 4, 5, 6, 7, 8, 9 };
        System.out.println(sum(nums, func)); // 20
    }
    
    public void SumTest2() {
        int[] nums = { 1, 2, 3, 4, 5, 6, 7, 8, 9 };
        int x = sum(nums, (n)-> n > 5); // sum numbers greated than 5
        System.out.println(x);  // 30 
    }    
    
    public void SumTest3() {
        int[] nums = { -5, -4, -3, -2, -1, 0, 1, 2, 3, 4, 5};
        System.out.println(sum(nums, ExpressionHelper::isEven));
         
        Expression expr = ExpressionHelper::isPositive;
        System.out.println(sum(nums, expr));
    } 

    public void invoke(boolean run, DefaultValue defaultCallback) {
    	if (run) {
    		String s = defaultCallback.getDefault();
    		System.out.println(s);
    	}
    	else {
    		System.out.println("We ve got some!");
    	}
    }
    
    public void Use_LambdaAsCallback() {
    	invoke(false, () -> {
    			System.out.println("Callback object created!");
    			return "Default Value";
    		}
    	);
    	
    	invoke(true, () -> {
			System.out.println("Callback object created!");
			return "Default Value";
		}
	);
    }
    
	///////////////////////////// MAIN //////////////////////////////
    
	public static void main(String[] args) {
		
		LambdaTestsClass tests = new LambdaTestsClass();
		
		//tests.Test_LambdaStyle();
		//tests.Test_OldStyle();
		
		// tests.SumTest();
		// tests.SumTest2();
		// tests.SumTest3();
		
		tests.Use_LambdaAsCallback();
	}
}
