package MethodReferences;

import java.util.Arrays;
import java.util.List;

class Transformer2 {
	public void toUpperAndPrint(String str) {
		System.out.println(str.toUpperCase());
	}
}

public class ObjectMethodRef {
	
	protected static Transformer2 transformer = new Transformer2();

	public static void main(String[] args) {
		List<String> messages = Arrays.asList("one", "two", "three");
		messages.forEach(transformer::toUpperAndPrint);
	}
}
