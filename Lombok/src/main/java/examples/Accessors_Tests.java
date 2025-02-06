package examples;

import lombok.Getter;
import lombok.Setter;
import lombok.experimental.Accessors;

import java.math.BigDecimal;

public class Accessors_Tests
{
    @Getter
    @Setter
    private final static class StandardAccount
    {
        private String name;
        private BigDecimal balance;
    }

    @Accessors(fluent = true, chain = false)
    @Getter
    @Setter
    private final static class FluentAccount
    {
        private String name;
        private BigDecimal balance;
    }

    @Accessors(fluent = true, chain = true)
    @Getter
    @Setter
    private final static class ChainedFluentAccount
    {
        private String name;
        private BigDecimal balance;
    }

    @Accessors(prefix = {"s", "bd"}, fluent = true, chain = true)
    @Getter
    @Setter
    private final static class PrefixedChainedFluentAccount
    {
        private String sName;
        private BigDecimal bdBalance;
    }

    public static void StandardTest()
    {
        StandardAccount account = new StandardAccount();
        account.setName("Standard Accessors");
        account.setBalance(BigDecimal.TEN);

        System.out.println("Standard Accessors = " + account.getName());
        System.out.println(BigDecimal.TEN + " " +  account.getBalance());
    }

    public static void FluentTest()
    {
        FluentAccount account = new FluentAccount();
        account.name("Fluent Account");
        account.balance(BigDecimal.TEN);

        System.out.println("Standard Accessors = " + account.name());
        System.out.println(BigDecimal.TEN + " " +  account.balance());
    }

    public static void ChainedFluentTest()
    {
        ChainedFluentAccount account = new ChainedFluentAccount()
                .name("Chained Fluent Account")
                .balance(BigDecimal.TEN);

        System.out.println("Standard Accessors = " + account.name());
        System.out.println(BigDecimal.TEN + " " +  account.balance());
    }

    public static void PrefixedChainedFluentTest()
    {
        // Despite fields have extra chars: sName and bdBalance
        PrefixedChainedFluentAccount account = new PrefixedChainedFluentAccount()
                .name("Chained Fluent Account")
                .balance(BigDecimal.TEN);

        System.out.println("Standard Accessors = " + account.name());
        System.out.println(BigDecimal.TEN + " " +  account.balance());
    }

    public static void main(String[] args)
    {
        StandardTest();
        FluentTest();
        ChainedFluentTest();
        PrefixedChainedFluentTest();
    }
}
