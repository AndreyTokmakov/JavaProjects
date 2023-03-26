package ObjectOrientedProgramming.Inheritance;

public class CallBaseClassConstructor
{
    private static class Base {
        public Base() {
            System.out.println("Base::Base()");
        }
    }

    private static class Derived extends Base {
        public Derived() {
            super();
            System.out.println("Derived::Derived()");
        }
    }

    public static void main(String[] args) {
        Base obj = new Derived();
    }
}
