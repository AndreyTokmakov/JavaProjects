package ObjectOrientedProgramming.SealedClasses.DemoOne;

sealed class Animal permits Cat, Dog {
    // Class definition
}

final class Cat extends Animal {
    // Class definition
}

final class Dog extends Animal {
    // Class definition
}

/*
// 'Puma' is not allowed in the sealed hierarchy
final class Puma extends Animal {

}
*/

public class DemoOne
{
    public static void main(String[] args) {
        System.out.println(DemoOne.class.getSimpleName());
    }
}