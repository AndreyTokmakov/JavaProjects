package Generics.TestClasses;

public class Pair<K, V> {
    private K first = null;
    private V second = null;

    private Pair(K first, V second) {
        this.first = first;
        this.second = second;
    }

    public K getFirst() {
        return first;
    }

    public V getSecond() {
        return second;
    }

    public void setFirst(K newValue) {
        first = newValue;
    }

    public void setSecond(V newValue) {
        second = newValue;
    }

    public static <K,V> Pair<K, V> of(K first, V second) {
        return new Pair<K, V>(first, second);
    }

    @Override
    public String toString() {
        return String.format("{%s, %s}", this.first, this.second);
    }

    // Overriding the equals of BaseObject class
    @Override
    public boolean equals(final Object obj) {
        if (this == obj)
            return true;
        else if (null == obj || obj.getClass() != this.getClass())
            return false;

        Pair<K, V> right = (Pair<K, V>) obj;

        if (null == right.first || null == this.first) {
            if (right.first != this.first)
                return false;
        } else {
            if (!this.first.equals(right.first))
                return false;
        }

        if (null == right.second || null == this.second) {
            if (right.second != this.second)
                return false;
        } else {
            if (!this.second.equals(right.second))
                return false;
        }

        return true;
    }

    @Override
    public int hashCode() {
        return (null == first ? 0 : first.hashCode()) +
                (null == second ? 0 : second.hashCode());
    }

    //-----------------------------------------------------------------------------

    public static void main(String[] args)
    {
        /*
        Pair<Integer, String> pair = Pair.of(1, "hello");
        Integer i = pair.getFirst(); // 1
        String s = pair.getSecond(); // "hello"

        System.out.println(i);
        System.out.println(s);


        Pair<Integer, String> pair2 = Pair.of(1, "hello");
        boolean mustBeTrue = pair.equals(pair2); // true!
        boolean mustAlsoBeTrue = pair.hashCode() == pair2.hashCode(); // true!

        System.out.println(mustBeTrue);
        System.out.println(mustAlsoBeTrue);
        */

        Pair<Integer, String> pair1 = Pair.of(1, null);
        Pair<Integer, String> pair2 = Pair.of(4, null);

        boolean b1 = pair1.equals(pair2);
        boolean b2 = pair1.hashCode() == pair2.hashCode();

        System.out.println(b1);
        System.out.println(b2);

    }
}
