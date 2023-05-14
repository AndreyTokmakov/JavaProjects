public abstract class DomesticAnimal {

    // Метод будет возвращать звук животного
    public abstract String getSound();

    // Метод будет возвращать кличку животного
    public abstract String getName();

    // Метод будет возвращать возраст животного
    public abstract int getAge();

    public static void main(String... args) {
        var dog = new Dog();
        var cat = new Cat();
    }
}

class Dog extends DomesticAnimal {

    @Override
    public String getSound() {
        return "Гав";
    }

    @Override
    public String getName() {
        return "Шарик";
    }

    @Override
    public int getAge() {
        return 5;
    }
}

class Cat extends DomesticAnimal {

    @Override
    public String getSound() {
        return "Мяу";
    }

    @Override
    public String getName() {
        return "Мурка";
    }

    @Override
    public int getAge() {
        return 3;
    }
}
