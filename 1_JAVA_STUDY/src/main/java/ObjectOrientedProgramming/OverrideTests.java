package ObjectOrientedProgramming;

public class OverrideTests
{
    public static class Base
    {
        protected void info() {
            System.out.println(this.getClass().getSimpleName() + "::info()");
        }
    }

    public static class Derived extends Base
    {
        @Override
        protected void info() {
            System.out.println(this.getClass().getSimpleName() + "::info()");
        }
    }

    public static void main(String[] args) {
        Base b = new Derived();
        b.info();
    }
}
