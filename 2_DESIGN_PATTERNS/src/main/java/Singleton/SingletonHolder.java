package Singleton;

public class SingletonHolder
{
    static class SingletonImpl
    {
        private SingletonImpl() {
            System.out.println(this.getClass().getName() + " has been created");
        }

        private static class SingletonKeeper
        {
            private static final SingletonImpl INSTANCE = new SingletonImpl();
        }

        public static SingletonImpl getInstance() {
            return SingletonKeeper.INSTANCE;
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