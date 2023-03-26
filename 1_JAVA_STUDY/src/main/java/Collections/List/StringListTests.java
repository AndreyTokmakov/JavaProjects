package Collections.List;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collection;


class StringList extends ArrayList<String> {
	/** **/
	private static final long serialVersionUID = -6261575504743605806L;
	
    /**
     * Constructs an empty list with an initial capacity of ten.
     */
    public StringList() {
    	super();
    }
	
    /**
     * Constructs an empty list with the specified initial capacity.
     *
     * @param  initialCapacity  the initial capacity of the list
     * @throws IllegalArgumentException if the specified initial capacity
     *         is negative
     */
    public StringList(int initialCapacity) {
        super(initialCapacity);
    }

    /**
     * Constructs a list containing the elements of the specified
     * collection, in the order they are returned by the collection's
     * iterator.
     *
     * @param c the collection whose elements are to be placed into this list
     * @throws NullPointerException if the specified collection is null
     */
    public StringList(final Collection<String> c) {
    	super(c);
    }
}


public class StringListTests {
	public void StrListTest() {
		StringList originalList = new StringList(Arrays.asList("Value1", "Value2","Value3"));
		
		for (final String str: originalList) 
			System.out.println(str);
		System.err.println(originalList.size());
	}
	
	/*************** TEST  **************/
	public static void main(String[] args) throws InterruptedException {
		StringListTests tests = new StringListTests();
		

		tests.StrListTest();
    }
}
