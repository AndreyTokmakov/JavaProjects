package currency_excange_rates_bot;

import java.util.Optional;

public class CbrClientTests
{
    private final static CbrClient client = new CbrClient();

    public static void main(String[] args) throws ServiceException
    {

        for (int i = 0; i < 2; ++i) {
            final Optional<String> rates = client.getCurrencyRatesXML();
            rates.ifPresent(System.out::println);
        }
    }
}
