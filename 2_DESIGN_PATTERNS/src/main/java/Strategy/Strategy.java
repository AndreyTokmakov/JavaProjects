package Strategy;

interface IStrategy {
    int execute(int a, int b); 
}

class ConcreteStrategyAdd implements IStrategy {

    public int execute(int a, int b) {
        System.out.println("Called ConcreteStrategyAdd's execute()");
        return a + b;  // Do an addition with a and b
    }
}
 
class ConcreteStrategySubtract implements IStrategy {
    public int execute(int a, int b) {
        System.out.println("Called ConcreteStrategySubtract's execute()");
        return a - b;  // Do a subtraction with a and b
    }
}
 
class ConcreteStrategyMultiply implements IStrategy {
    public int execute(int a, int b) {
        System.out.println("Called ConcreteStrategyMultiply's execute()");
        return a * b;   // Do a multiplication with a and b
    }    
}

class Context {
    private IStrategy strategy;

    public Context() {
    }

    public void setStrategy(IStrategy strategy) {
        this.strategy = strategy;
    }

    public int executeStrategy(int a, int b) {
        return strategy.execute(a, b);
    }
}


public class Strategy {
	public static void main(String[] args) {
        Context context = new Context();
 
        context.setStrategy(new ConcreteStrategyAdd());
        System.out.println("Result : " + context.executeStrategy(3, 4));
 
        context.setStrategy(new ConcreteStrategySubtract());
        System.out.println("Result : " + context.executeStrategy(3, 4));
 
        context.setStrategy(new ConcreteStrategyMultiply());
        System.out.println("Result : " + context.executeStrategy(3, 4));
	}
}
