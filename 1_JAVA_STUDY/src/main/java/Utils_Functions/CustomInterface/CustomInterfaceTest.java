package Utils_Functions.CustomInterface;

import java.util.Objects;

@FunctionalInterface
interface Calculator {
	long calculate(long num1, long num2);
} 


class Utility {
    public static long add(long num1, long num2) {
    	return num1 + num2;
    }
    public static long multiply(long num1, long num2) {
    	return num1 * num2;
    }  
} 

/////////////////////////////////////////////////

@FunctionalInterface
interface Worship {
	
	void chant(String name);
  
	default Worship again(Worship w) {
		return (name) -> { 
			Objects.requireNonNull(w);  
			chant(name);
			w.chant(name);
		};
	}
} 

/////////////////////////////////////////////////

@FunctionalInterface
interface DataCombiner<T> {
   String combine(T t);
} 

@FunctionalInterface
interface TaskHandler {
	void get(String tname);
} 

@FunctionalInterface
interface DataReceiver<T> extends DataCombiner<T> {
	default void receive(TaskHandler handler, T t) {
		Objects.requireNonNull(handler);
		handler.get(combine(t));
	}
} 

class Employee {
    private int id;
    private String name;
    
    public Employee(int id, String name) {
	    this.id = id;
	    this.name = name;
    }

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}
} 

/** ------------- Tests class -----------------------**/
public class CustomInterfaceTest {
	
	protected static void Calc_Test() {
		System.out.println("------------------------ Test1: \n");
		Calculator calc = (n1, n2) -> n1 + n2; 
		System.out.println(calc.calculate(30, 50)); 
	}
	
	protected static void Calc_As_AnotherClassHandler() {
		System.out.println("\n------------------------ Test2: \n");
		Calculator calc = Utility::add;
		System.out.println(calc.calculate(30, 50));
	}
	
	protected static void Default_Method() {
		Worship worship = (name) ->  {
			System.out.println("[ " + name + " ]");
		};
		worship.again(worship).again(worship).chant("Ram"); 
	}
	
	protected static void Inheritance_With_Generic() {
		DataReceiver<Employee> dataReceiver = (Employee emp) -> emp.getId() + "-" + emp.getName();

		TaskHandler tskHandler = (res) -> System.out.println(res); 

		dataReceiver.receive(tskHandler, new Employee(101, "Krishna")); 
	} 
	
	public static void main(String[] args) {
		// Calc_Test();
		// Calc_As_AnotherClassHandler();
		
		// Default_Method();
		
		Inheritance_With_Generic();
	}
}
