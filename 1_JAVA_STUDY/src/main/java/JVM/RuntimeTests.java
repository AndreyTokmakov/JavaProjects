package JVM;

public class RuntimeTests {
	public static void main(String[] args) {
		Runtime r = Runtime.getRuntime();
		System.out.println("version: " + Runtime.version());
		
		System.out.println("\ntotalMemory: " + Runtime.getRuntime().totalMemory());
		System.out.println("freeMemory: " + Runtime.getRuntime().freeMemory());
		System.out.println("maxMemory: " + Runtime.getRuntime().maxMemory());
		
		System.out.println("\navailableProcessors: " + Runtime.getRuntime().availableProcessors());
	}
}
