package Decorator;


/** The interface Coffee defines the functionality of Coffee implemented by decorator **/
interface Coffee {
    // Returns the cost of the coffee
    public double getCost();

    // Returns the ingredients of the coffee
    public String getIngredients();
}


/** Extension of a simple coffee without any extra ingredients **/
class SimpleCoffee implements Coffee {
    @Override
    public double getCost() {
        return 1;
    }

    @Override
    public String getIngredients() {
        return "Coffee";
    }
}


/** Abstract decorator class - note that it implements Coffee interface **/
abstract class CoffeeDecorator implements Coffee {
    private final Coffee decoratedCoffee;

    public CoffeeDecorator(Coffee coffee) {
        this.decoratedCoffee = coffee;
    }

    @Override
    public double getCost() {
        // Implementing methods of the interface
        return decoratedCoffee.getCost();
    }

    @Override
    public String getIngredients() {
        return decoratedCoffee.getIngredients();
    }
}

/** Decorator WithMilk mixes milk into coffee. **/
class WithMilk extends CoffeeDecorator {
    public WithMilk(Coffee coffee) {
        super(coffee);
    }

    @Override
    public double getCost() {
        // Overriding methods defined in the abstract superclass
        return super.getCost() + 0.5;
    }

    @Override
    public String getIngredients() {
        return super.getIngredients() + " + Milk";
    }
}


// Decorator WithSprinkles mixes sprinkles onto coffee.
class WithSprinkles extends CoffeeDecorator {
    public WithSprinkles(Coffee coffee) {
        super(coffee);
    }

    @Override
    public double getCost() {
        return super.getCost() + 0.2;
    }

    @Override
    public String getIngredients() {
        return super.getIngredients() + " + Sprinkles";
    }
}


public class CoffeeDecoratorDemo
{
    public static void printInfo(Coffee c) {
        System.out.println(c.getClass().getName() + " | Cost: " + c.getCost() + "; Ingredients: " + c.getIngredients());
    }

    public static void main(String[] args) {
        Coffee c = new SimpleCoffee();
        printInfo(c);

        c = new WithMilk(c);
        printInfo(c);

        c = new WithSprinkles(c);
        printInfo(c);
    }
}
