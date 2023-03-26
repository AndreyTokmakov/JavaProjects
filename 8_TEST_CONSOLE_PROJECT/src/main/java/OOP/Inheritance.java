package OOP;

public class Inheritance
{
    static class A {
        public A() {
            System.out.println("A");
        }

    }
    static class B extends  A {
        public B() {
            super();
            System.out.println("B");
        }

        public B(String s) {
            super();
            System.out.println(s);
        }
    }


    public static void main(String[] args) {
        new B();
    }
}
