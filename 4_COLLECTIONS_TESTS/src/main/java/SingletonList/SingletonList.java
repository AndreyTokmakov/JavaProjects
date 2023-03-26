package SingletonList;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;

public class SingletonList {
	
    public void Test() {
        String initList[] = { "One", "Two", "Four", "One",};
        List<String> list = new ArrayList<String>(Arrays.asList(initList));

        System.out.println("List value before: " + list);
        
        // create singleton list
        list = Collections.singletonList("OnlyOneElement");
        System.out.println("List value after: " + list);

        list.add("five"); //throws UnsupportedOperationException  
    }

    
    
	public static void main(String[] args) {
		SingletonList tests = new SingletonList();
		tests.Test();

	}
}
