package currency_excange_rates_bot_bad_2;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ConfigurableApplicationContext;
import org.telegram.telegrambots.meta.TelegramBotsApi;
import org.telegram.telegrambots.meta.exceptions.TelegramApiException;
import org.telegram.telegrambots.updatesreceivers.DefaultBotSession;

@SpringBootApplication
public class AppMain
{
    public static void main(String[] args)
    {
        ConfigurableApplicationContext ctx = SpringApplication.run(AppMain.class, args);
        try {
            TelegramBotsApi botsApi = new TelegramBotsApi(DefaultBotSession.class);
            botsApi.registerBot(new ExchangeRatesBot());
        } catch (TelegramApiException e) {
            throw new RuntimeException(e);
        }
    }
}