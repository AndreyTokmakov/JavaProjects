package currency_excange_rates_bot_bad_2;

import okhttp3.OkHttpClient;
import org.springframework.cache.annotation.EnableCaching;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.scheduling.annotation.EnableScheduling;

@Configuration
@EnableCaching
@EnableScheduling
public class ExchangeRatesBotConfiguration
{
    @Bean
    public OkHttpClient okHttpClient() {
        return new OkHttpClient();
    }
}