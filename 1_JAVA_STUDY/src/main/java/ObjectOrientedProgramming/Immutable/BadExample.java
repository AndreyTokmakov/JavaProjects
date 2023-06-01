package ObjectOrientedProgramming.Immutable;

public class BadExample
{
    private final static class NotImmutable {
        private final int age;
        private final String name;

        private NotImmutable(int age, String name) {
            this.age = age;
            this.name = name;
        }


        public String getName() {
            return name;
        }

        public int getAge() {
            return age;
        }

        @Override
        public String toString() {
            return String.format("NotImmutable(age: %d, name: %s)", this.age, this.name);
        }
    }

    public static void main(String[] args)
    {
        // NotImmutable obj = new NotImmutable(24, "Jonh");
        String obj = "Jonh";
        System.out.println(obj);

        obj.intern().getBytes()[1]= 's';

        System.out.println(obj);

    }
}
