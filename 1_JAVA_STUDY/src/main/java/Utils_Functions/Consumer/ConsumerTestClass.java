package Utils_Functions.Consumer;

import java.util.ArrayList;
import java.util.List;
import java.util.function.Consumer;

public class ConsumerTestClass {
	
	private final Consumer<String> printer = value-> System.out.printf("%s dollars\n", value);
	
	public void Consumer_TEST()
	{
		List<String> values = new ArrayList<String>();
		values.add("10");
		values.add("20");
		values.add("50");
		
		values.forEach(printer);
	}
	
	public void Consumer_lambda()
	{
		List<String> values = new ArrayList<String>();
		values.add("10");
		values.add("20");
		values.add("50");
		
		values.forEach((final String value)-> System.out.printf("%s dollars\n", value));
	}
	
	///////////////////////////// MAIN //////////////////////////////
	public static void main(String[] args) {
		ConsumerTestClass tests = new ConsumerTestClass();
		//tests.Consumer_TEST ();\
		tests.Consumer_lambda();
	}
}
