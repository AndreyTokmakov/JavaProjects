package VariadicParameters;

import java.util.List;

class Tester {
	
	protected int sumNumber(int ... args){
		System.out.println("argument length: " + args.length);
		int sum = 0;
		for(int x: args){
			sum += x;
		}
		return sum;
	}
	
	protected void handleParams(int value, final String text, String ... args){
		System.out.println("Value: " + value);
		System.out.println("Text : " + text);
		
		System.out.print("Strings: ");
		for(final String str: args)
			System.out.print(str + ", ");
		System.out.println();
	}
	
	/////////////////////////////////////////
	
	protected void Test() {
		System.out.println(sumNumber(1,2,3,4,5,6,7,8,9));	
		
		System.out.println();
		handleParams(123, "Test", "One", "Two", "Three", "Four", "Five");
	}

	List<String> makeList(String ... args) {
		return List.of(args);
	}

	void Pass_Variadic_to_Function()
	{
		var list = makeList("1", "2", "3");
		System.out.println(list);
	}
}


public class VariadicParameters
{
	public static void main(String[] args)
	{
		Tester tester = new Tester();
		// tester.Test();
		tester.Pass_Variadic_to_Function();
	}
}
