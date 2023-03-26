package Collections.Set;

import java.util.Arrays;
import java.util.Set;
import java.util.TreeSet;

/** BAD Value class: **/
class ValueBad {
    protected long value;
    
    public ValueBad(long num)  {
        this.value = num;
    }

    @Override
    public String toString() {
        return String.valueOf(this.value);
    }
}

/** Value class: **/
class Value implements Comparable<Value> {
    protected long value;
    
    public Value(long num)  {
        this.value = num;
    }

    @Override
    public int compareTo(Value obj) {
        if (this.value == obj.value)
        	return 0;
        return this.value > obj.value ? 1 : -1;
    }
    
    @Override
    public String toString() {
        return String.valueOf(this.value);
    }
}


public class TreeSet_Tests {
	
	private void PrintSet(final Set<?> set, final String text) {
		if (false == text.isEmpty() || true == text.isBlank())
			System.out.print(text);
		for (final Object value : set)
			System.out.print(value + " ");
		System.out.println();
	}
	
	protected void Create() {
		Set<Integer> numbers = new TreeSet<Integer>(Arrays.asList(51, 2, 3, 6, 2, 3));
		PrintSet(numbers, "");
	}

	protected void Create_Values_BAD() { // !!!!!!!!!!!!!!! We'll get RUN TIME ERROR here
		Set<ValueBad> numbers = new TreeSet<ValueBad>();
		
		numbers.add(new ValueBad(51));
		numbers.add(new ValueBad(2));
		numbers.add(new ValueBad(3));
		
		PrintSet(numbers, "");
	}
	protected void Create_Values() { // !!!!!!!!!!!!!!! We'll get RUN TIME ERROR here
		Set<Value> numbers = new TreeSet<Value>();
		
		numbers.add(new Value(51));
		numbers.add(new Value(2));
		numbers.add(new Value(3));
		
		PrintSet(numbers, "");
	}

	/************************** MAIN *****************************/
	public static void main(String[] args) {
		TreeSet_Tests tests = new TreeSet_Tests();
		
		// tests.Create();
		tests.Create_Values();
		// tests.Create_Values_BAD();
	}
}
