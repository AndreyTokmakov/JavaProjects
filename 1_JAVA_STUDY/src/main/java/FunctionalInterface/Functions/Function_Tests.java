package FunctionalInterface.Functions;

import java.util.function.Function;

public class Function_Tests {

	public static void main(String[] args) {
		
		System.out.println("------------------------------------- Test1 ---------------------------------");

		Function<Integer, String> intToString = new Function<Integer, String>() {
			@Override public String apply(Integer from) {
				return from.toString();
			}
		};
		
		String result = intToString.apply(9000);
		System.out.println(result);
		
		System.out.println("------------------------------------- Test2 ---------------------------------");
		
		Function<String, Integer> valueConverter = x -> Integer.valueOf(x);
		Integer intTesult = valueConverter.apply("9000");
		System.out.println(intTesult);
		
		System.out.println("------------------------------------- Test3 ---------------------------------");
		
		Function<String, String> f1 = s -> s + "1";
        Function<String, String> f2 = s -> s + "2";
        Function<String, String> f3 = s -> s + "3";
        Function<String, String> f4 = s -> s + "4";
        System.out.println(f1.andThen(f2).compose(f3).compose(f4).apply("Compose"));
        System.out.println(f1.andThen(f2).andThen(f3).apply("AndThen"));
        
		System.out.println("------------------------------------- Test4 ---------------------------------");
		
        Function<String, String> f = Function.identity();
        System.out.println(f.apply("Some Value"));
        
        
        System.out.println("------------------------------------- Test5 ---------------------------------");
        
        Function<String, String> function1 = s -> s.toLowerCase();
        System.out.println(function1.apply("OCPJP 8"));

        Function<String, String> function2 = String::toLowerCase;
        System.out.println(function2.apply("OCPJP 8"));
        
        System.out.println("------------------------------------- Test6 ---------------------------------");
        
        Function<String, Integer> function = Integer::new;
        System.out.println(function.apply("4"));
	}
}
