package SwitchExpressions;

import java.util.List;
import java.util.stream.Collectors;

public class Exceptions_Tests {
	public static void main(String[] args) 
	{
		List<StackWalker.StackFrame> stack = StackWalker.getInstance(StackWalker.Option.RETAIN_CLASS_REFERENCE).walk((s) -> s.collect(Collectors.toList()));
		stack.forEach(System.out::println);
	}
}
