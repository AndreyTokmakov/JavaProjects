package currency_excange_rates_bot;

import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;
import okhttp3.ResponseBody;

import com.github.benmanes.caffeine.cache.Cache;
import com.github.benmanes.caffeine.cache.Caffeine;

import java.io.IOException;
import java.util.Optional;
import java.util.concurrent.TimeUnit;

public class CbrClient
{
    private final OkHttpClient client = new OkHttpClient();

    private final Cache<String, String> cache;

    private static final String cbrCurrencyRatesXmlUrl = "http://www.cbr.ru/scripts/XML_daily.asp";

    public CbrClient()
    {
        this.cache = Caffeine.newBuilder()
                .expireAfterWrite(10, TimeUnit.MINUTES)
                .maximumSize(10)
                .build();
    }

    public Optional<String> getCurrencyRatesXML() throws ServiceException
    {
        final String cachedResponse = cache.getIfPresent(cbrCurrencyRatesXmlUrl);
        if (null != cachedResponse) {
            return Optional.of(cachedResponse);
        }

        final Request request = new Request.Builder().url(cbrCurrencyRatesXmlUrl).build();
        try (Response response = client.newCall(request).execute()) {
            final String bodyString = response.body().string();

            cache.put(cbrCurrencyRatesXmlUrl, bodyString);
            return Optional.of(bodyString);
        } catch (IOException e) {
            throw new ServiceException("Ошибка получения курсов валют от ЦБ РФ", e);
        }
    }
}