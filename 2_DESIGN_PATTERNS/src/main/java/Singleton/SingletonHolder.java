package Singleton;

class SingletonWithKeeper {
    private SingletonWithKeeper() {}

    private static class SingletonKeeper {
        private static final SingletonWithKeeper INSTANCE = new SingletonWithKeeper();
    }

    public static SingletonWithKeeper getInstance() {
        return SingletonKeeper.INSTANCE;
    }
}


public class SingletonHolder {
    public static void main(String[] args) {
        SingletonWithKeeper s2 = SingletonWithKeeper.getInstance();
        SingletonWithKeeper s1 = SingletonWithKeeper.getInstance();

        System.out.println(s1.hashCode());
        System.out.println(s2.hashCode());
        System.out.println(s2 == s1);
    }
}