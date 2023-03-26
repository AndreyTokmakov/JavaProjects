package FunctionalInterface.UnaryOperator;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.function.Function;
import java.util.function.UnaryOperator;

public class UnaryOperatorTest {
	
    public  void UnaryOperator_TEST() {
        UnaryOperator<Integer> square = x -> x*x;
        System.out.println(square.apply(5)); // 25
    }
    
	public void ToUpperCase() {
		UnaryOperator<String> up = s -> s.toUpperCase();
		System.out.print(up.apply("Java 8"));
	}
	
	public void Mult() {
        Function<Integer, Integer> func = x -> x * 2;
        Integer result = func.apply(2);

        System.out.println(result);         // 4
        UnaryOperator<Integer> func2 = x -> x * 2;

        Integer result2 = func2.apply(2);
        System.out.println(result2);        // 4
	}
	
	
    public <T> List<T> math(List<T> list, UnaryOperator<T> uo) {
        List<T> result = new ArrayList<>();
        for (T t : list) {
            result.add(uo.apply(t));
        }
        return result;
    }
    
    
    public void Call_Func() {
        List<Integer> list = Arrays.asList(10, 20, 30, 40, 50);
        UnaryOperator<Integer> unaryOpt = i->i*i; 
        unaryOperatorFun(unaryOpt, list).forEach(x->System.out.println(x));       
    }
    
	private List<Integer> unaryOperatorFun(UnaryOperator<Integer> unaryOpt, List<Integer> list){
		List<Integer> uniList = new ArrayList<>();
		list.forEach(i->uniList.add(unaryOpt.apply(i))); 
		return uniList;
	}
	
	
	public void Doube_List() {
        List<Integer> list = Arrays.asList(1, 2, 3, 4, 5, 6, 7, 8, 9, 10);
        List<Integer> result = math(list, x -> x * 2);
        System.out.println(result); // [2, 4, 6, 8, 10, 12, 14, 16, 18, 20]
	}

	public static void main(String[] args) {
		UnaryOperatorTest tests = new UnaryOperatorTest();
		// tests.UnaryOperator_TEST();
		// tests.ToUpperCase();
		// tests.Mult();
		// tests.Doube_List();;
		tests.Call_Func();
	}
}
