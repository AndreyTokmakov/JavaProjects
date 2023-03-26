package UnmodifiableCollections;


import java.util.Arrays;
import java.util.Collections;
import java.util.List;

class Tester {
	protected void Test() {
		List<Integer> inner = Arrays.asList(1,2,3,4,5);
		List<Integer> list1 = Collections.unmodifiableList(inner); 
		List<Integer> list2 = List.of(1,2,3,4,5);
		
		// inner.remove(3); //-- ERROR!! java.lang.UnsupportedOperationException
		
		// list1.add(12);   //-- ERROR!! java.lang.UnsupportedOperationException
		
		// list2.add(33);; // -- ERROR!! java.lang.UnsupportedOperationException
	}
}

public class ListTests {
	public static void main(String[] args) {
		Tester tester = new Tester();
		tester.Test();
	}
}
