package Map;

import java.util.Comparator;
import java.util.Map;
import java.util.TreeMap;

class Person {
	protected String firstName;
	protected String lastName;
	protected int age;

	public Person(final String firstName,
				  final String lastName, 
				  int age) {
		this.firstName = firstName;
		this.lastName = lastName;
		this.age = age;
	}

	public String getFirstName() {
		return this.firstName;
	}

	public void setFirstName(String firstName) {
		this.firstName = firstName;
	}

	public String getLastName() {
		return this.lastName;
	}

	public void setLastName(String lastName) {
		this.lastName = lastName;
	}

	public int getAge() {
		return this.age;
	}

	public void setAge(int age) {
		this.age = age;
	}

	@Override
	public String toString() {
		return String.format("%s %s. Age: %d", this.firstName, this.lastName, this.age);
	}
}


class Value implements Comparable<Value> {
    protected String name;
    protected long   value;
    private final static String TEMPLATE = "num = %d, str = '%s'";
    
    public Value(final String str,
    			 long num)  {
        this.name = str;
        this.value = num;
    }

    @Override
    public int compareTo(Value obj)
    {
    	final Value entry = (Value) obj;
        int result = name.compareTo(entry.name);
        if(result != 0)
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

///////////////////////////////////////////////////////////////////////////////////////////

class TreeMapTesting {
	
	public void SimpleTest() {
		TreeMap<Integer, String> map = new TreeMap<Integer, String>();
		

		map.put(11, "Value_11");
		map.put(22, "Value_22");
		map.put(33, "Value_33");
		map.put(44, "Value_44");
		
		for (Map.Entry<Integer, String> entry: map.entrySet()) {
			System.out.println(entry);
		}
	}
	
	public void ComparatorTests() {
		TreeMap<Person, Integer> map = new TreeMap<>(new Comparator<Person>() {
			@Override
			public int compare(Person o1, Person o2) {
				return o1.age - o2.age;
			}
		});
		
		
		map.put(new Person("John", "Smith", 17), 0);
		map.put(new Person("Ivan", "Petrenko", 65), 0);
		map.put(new Person("Pedro", "Escobar", 32), 0);
		map.put(new Person("Radion", "Pyatkin", 14), 0);
		map.put(new Person("Sergey", "Vashkevich", 19), 0);
		
		System.out.println("Initial map:");
		map.keySet().forEach(p -> System.out.println("  " + p));

		
		Person firstAdultPerson = map.navigableKeySet().stream().filter(person -> person.age>18).findFirst().get();
		System.out.println("\nfirstAdultPerson = " + firstAdultPerson);
		
		Map<Person, Integer> youngPeopleMap = map.headMap(firstAdultPerson, false);
	    Map<Person, Integer> adultPeopleMap = map.tailMap(firstAdultPerson, true);
	    
	    System.out.println("\nYoungPeopleMap:");
	    youngPeopleMap.keySet().forEach(p -> System.out.println("  " + p));
		
		System.out.println("\nAdultPeopleMap:");
		adultPeopleMap.keySet().forEach(p -> System.out.println("  " + p));
	}
	
	public void HeadMap_TailMap() {
		TreeMap<Person, Integer> map = new TreeMap<>(new Comparator<Person>() {
			@Override
			public int compare(Person o1, Person o2) {
				return o1.age - o2.age;
			}
		});
		
		
		map.put(new Person("John", "Smith", 17), 0);
		map.put(new Person("Ivan", "Petrenko", 65), 0);
		map.put(new Person("Pedro", "Escobar", 32), 0);
		map.put(new Person("Radion", "Pyatkin", 14), 0);
		map.put(new Person("Sergey", "Vashkevich", 19), 0);
		
		System.out.println("Initial map:");
		map.keySet().forEach(p -> System.out.println("  " + p));

		
		Person firstAdultPerson = map.navigableKeySet().stream().filter(person -> person.age>18).findFirst().get();
		System.out.println("\nfirstAdultPerson = " + firstAdultPerson);
		
		Map<Person, Integer> youngPeopleMap = map.headMap(firstAdultPerson, false);
	    Map<Person, Integer> adultPeopleMap = map.tailMap(firstAdultPerson, true);
	    
	    System.out.println("\nYoungPeopleMap:");
	    youngPeopleMap.keySet().forEach(p -> System.out.println("  " + p));
		
		System.out.println("\nAdultPeopleMap:");
		adultPeopleMap.keySet().forEach(p -> System.out.println("  " + p));
	}
	
	public void Comparable_Test() {
		TreeMap<Value, String> map = new TreeMap<Value, String>();
		
		map.put(new Value("Name3", 1), "Value_33");
		map.put(new Value("Name2", 1), "Value_22");
		map.put(new Value("Name1", 1), "Value_11");

		for (Map.Entry<Value, String> entry: map.entrySet()) {
			System.out.println(entry);
		}
	}
}

public class TreeMapTests {
	public static void main(String[] args) {
		TreeMapTesting tests = new TreeMapTesting();
		
		 tests.SimpleTest();
		// tests.ComparatorTests();
		// tests.HeadMap_TailMap();
		// tests.Comparable_Test();
	}
}
