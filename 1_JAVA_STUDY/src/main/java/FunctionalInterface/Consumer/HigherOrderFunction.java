package FunctionalInterface.Consumer;

import java.util.Arrays;
import java.util.List;
import java.util.function.Consumer;

public class HigherOrderFunction {

	private static<T> void forEach(List<T> list, Consumer<T> consumer) {
        for (final T t : list) {
            consumer.accept(t);
        }
    }
    
    private static void Consumer_Test() {
        List<String> list = Arrays.asList("One", "Two", "Three");
        
        forEach(list, System.out::println);
        forEach(list, (String x) -> {System.out.println("[" + x + "]");});
    }
	
    //---------------------------------------------------------------------------
    
    interface Operationable<T>  {
    	public int hadleRecords(T x, T y);
    }

	
    public static void main(String[] args) 
    {
    	Consumer_Test();
    }
}
