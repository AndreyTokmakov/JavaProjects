package State;

public class VendingMachine
{
    private  interface IState
    {
        void insertDollar(VendingMachineImpl context);
        void ejectMoney(VendingMachineImpl context);
        void dispense(VendingMachineImpl context);
    }

    private final static class HasMoneyState implements IState
    {
        @Override
        public void insertDollar(VendingMachineImpl context) {
            System.out.println("Already have a dollar");
        }

        @Override
        public void ejectMoney(VendingMachineImpl context) {
            System.out.println("Returning dollar");
            context.setState(new NoMoneyState());
        }

        @Override
        public void dispense(VendingMachineImpl context) {
            System.out.println("Releasing product");
            context.setState(new NoMoneyState());
        }
    }

    private final static class NoMoneyState implements IState
    {
        @Override
        public void insertDollar(VendingMachineImpl context) {
            System.out.println("Dollar inserted");
            context.setState(new HasMoneyState());
        }

        @Override
        public void ejectMoney(VendingMachineImpl context) {
            System.out.println("No money to return");
        }

        @Override
        public void dispense(VendingMachineImpl context) {
            System.out.println("Payment required");
        }
    }


    private final static class VendingMachineImpl
    {
        private IState currentState;

        public VendingMachineImpl() {
            currentState = new NoMoneyState();
        }

        public void setState(IState state) {
            this.currentState = state;
        }

        public void insertDollar() {
            currentState.insertDollar(this);
        }

        public void ejectMoney() {
            currentState.ejectMoney(this);
        }

        public void dispense() {
            currentState.dispense(this);
        }
    }

    public static void main(String[] args)
    {
        VendingMachineImpl vendingMachine = new VendingMachineImpl();

        vendingMachine.insertDollar();
        vendingMachine.dispense();

        System.out.println("----");

        vendingMachine.insertDollar();
        vendingMachine.insertDollar();
        vendingMachine.ejectMoney();
        vendingMachine.dispense();

        System.out.println("----");

        vendingMachine.insertDollar();
        vendingMachine.dispense();
        vendingMachine.ejectMoney();
    }
}
