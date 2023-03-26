package FunctionalInterface;


@FunctionalInterface
interface SimpleFunInterface {
    public void doWork();
}

@FunctionalInterface
interface SimpleFuncInterface {
    public void doWork();
    public String toString();
    public boolean equals(Object o);
}

@FunctionalInterface
interface ComplexFunctionalInterface extends SimpleFuncInterface {
    default public void doSomeWork() {
        System.out.println("Doing some work in interface impl...");
    }
}

class InterfaceTester {
	protected void carryOutWork(SimpleFuncInterface sfi) {
        sfi.doWork();
	}
	
	protected void Simple_Interface_Test() {
		carryOutWork(new SimpleFuncInterface() {
            @Override
            public void doWork() {
                System.out.println("Do work in SimpleFun impl...");
            }
        });
        carryOutWork(() -> System.out.println("Do work in lambda exp impl..."));
	}
}


public class Custom_Interfaces {
	private static InterfaceTester tester = new InterfaceTester();
	
	public static void main(String[] args) {
        tester.Simple_Interface_Test();
	}
}
