package currency_excange_rates_bot;

import org.telegram.telegrambots.meta.TelegramBotsApi;
import org.telegram.telegrambots.meta.exceptions.TelegramApiException;
import org.telegram.telegrambots.updatesreceivers.DefaultBotSession;

public class AppMain
{
    public static void main(String[] args)
    {
        try {
            TelegramBotsApi botsApi = new TelegramBotsApi(DefaultBotSession.class);
            botsApi.registerBot(new ExchangeRatesBot());
        } catch (final TelegramApiException exc) {
            System.err.println(exc.getMessage());
        }
    }
}