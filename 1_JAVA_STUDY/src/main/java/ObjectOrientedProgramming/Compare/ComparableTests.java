package ObjectOrientedProgramming.Compare;

import java.util.TreeSet;

class Value implements Comparable<Object> {
    protected String name;
    protected long   value;
    
    private final static String TEMPLATE = "num = %d, str = '%s'";
    
    public Value(final String str,
    			 long num)  {
        this.name = str;
        this.value = num;
    }

    @Override
    public int compareTo(Object obj)
    {
    	final Value entry = (Value) obj;
        int result = name.compareTo(entry.name);
        if (result != 0)
            return result;

        if (this.value == entry.value)
        	return 0;
        return this.value > entry.value ? 1 : -1;
    }
    
    @Override
    public String toString() {
        return String.format(Value.TEMPLATE, value, name);
    }
}


public class ComparableTests {
	public static void main(String[] args) {
		TreeSet<Value> data = new TreeSet<Value>();
		data.add(new Value("Value1", 7));
		data.add(new Value("Value2", 4));
		data.add(new Value("Value3", 5));
		data.add(new Value("Value4", 1));
		data.add(new Value("Value4", 2));
		for (Value e : data)
			System.out.println(e.toString());
	}
}
