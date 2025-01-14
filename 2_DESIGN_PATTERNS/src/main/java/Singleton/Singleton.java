package Singleton;

public class Singleton
{
	static class SingletonImpl
	{
		private static SingletonImpl __instance = null;

		protected SingletonImpl() {
			System.out.println(this.getClass().getName() + " created");
		}

		public static SingletonImpl getInstance()
		{
			if (null == SingletonImpl.__instance) {
				synchronized (SingletonImpl.class) {
					if (null == SingletonImpl.__instance) {
						SingletonImpl.__instance = new SingletonImpl();
					}
				}
			}
			return SingletonImpl.__instance;
		}
	}

	public static void main(String[] args)
	{
		System.out.println("Starting application");

		SingletonImpl s2 = SingletonImpl.getInstance();
		SingletonImpl s1 = SingletonImpl.getInstance();

		System.out.println(s1.hashCode());
		System.out.println(s2.hashCode());
		System.out.println(s2 == s1);
	}
}
