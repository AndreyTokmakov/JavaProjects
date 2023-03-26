package Singleton;


/** @class Singleton : */
 class Singleton {
	/** Singleton static instance. **/
	private static Singleton __instance = null;

	/** Singleton class constructor: **/
	protected Singleton() {
		System.out.println("Singleton created");
	}

	/**  **/
	public static Singleton getInstance() {
		if (null == Singleton.__instance) {
			synchronized (Singleton.class) {
				if (null == Singleton.__instance) {
					Singleton.__instance = new Singleton();
				}
			}
		}
		return Singleton.__instance;
	}
}
	
public class SingletonTests {
	public static void main(String[] args) {
		Singleton s2 = Singleton.getInstance();
		Singleton s1 = Singleton.getInstance();

		System.out.println(s1.hashCode());
		System.out.println(s2.hashCode());
		System.out.println(s2 == s1);
	}
}
