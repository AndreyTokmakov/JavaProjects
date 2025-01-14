package Singleton;


import lombok.Getter;

public class StaticBlockSingleton
{
    public static class SingletonImpl
    {
        @Getter
        private static SingletonImpl instance = null;

        private SingletonImpl() {
            System.out.println(this.getClass().getName() + " created");
        }

        static {
            try {
                instance = new SingletonImpl();
            } catch (Exception e) {
                throw new RuntimeException("Exception occurred in creating singleton instance");
            }
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
