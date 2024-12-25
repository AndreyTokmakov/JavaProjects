package Singleton;

class SingletonWithKeeper
{
    private SingletonWithKeeper() {
        System.out.println(this.getClass().getName() + " has been created");
    }

    private static class SingletonKeeper
    {
        private static final SingletonWithKeeper INSTANCE = new SingletonWithKeeper();
    }

    public static SingletonWithKeeper getInstance() {
        return SingletonKeeper.INSTANCE;
    }
}


public class SingletonHolder
{
    public static void main(String[] args)
    {
        System.out.println("Starting application");

        SingletonWithKeeper s2 = SingletonWithKeeper.getInstance();
        SingletonWithKeeper s1 = SingletonWithKeeper.getInstance();

        System.out.println(s1.hashCode());
        System.out.println(s2.hashCode());
        System.out.println(s2 == s1);
    }
}