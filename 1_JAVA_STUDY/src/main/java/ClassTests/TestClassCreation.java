package ClassTests;


class Test2 {
	/** Static Test2 class instance : **/
	private static volatile Test2 __instance = null;
	
	/** Test2 the singleton table instance : **/  
	public static Test2 getInstance() {
		if (null == __instance) {
			synchronized (Test2.class) {
				if (null == __instance) {
					__instance = new Test2();
				};
			}
		} return __instance;
	}
	
	private Test2() {
		System.out.println("Test2 class constructed()");
	}
}


class Test1 {
	
	private final static Test2 t2 = Test2.getInstance();
	
	public Test1() {
		System.out.println("Test1 class constructed()");
	}
}

public class TestClassCreation {
	public static void main(String[] args) {
		Test1 t = new Test1();
	}
}


