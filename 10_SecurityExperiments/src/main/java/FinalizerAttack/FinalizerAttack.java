package FinalizerAttack;

import java.util.concurrent.TimeUnit;

public class FinalizerAttack
{
    public static class AccountOperations {
        public AccountOperations() {
            if (!isAuthorized()) {
                throw new SecurityException("You can't access the account");
            }
        }

        public boolean isAuthorized() {
            return false;
        }

        public void transferMoney(double amount) {
            System.out.println("Transferring " + amount + " to beneficiary");
        }
    }

    public static class FakeAccountOperations extends AccountOperations {

        @Override
        protected void finalize() {
            System.out.println("Still I can transfer money");
            this.transferMoney(100);
            System.exit(0);
        }
    }

    public static void main(String[] args) throws InterruptedException {
        NormalBehavior();
        // Attack();

        System.gc();
        TimeUnit.SECONDS.sleep(10);
    }

    protected static void NormalBehavior() {
        try {
            AccountOperations accOperations = new AccountOperations();
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
    }

    protected static void Attack() {
        try {
            AccountOperations accOperations = new FakeAccountOperations();
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
    }
}
