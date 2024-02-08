package currency_excange_rates_bot_bad_2;

import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;
import okhttp3.ResponseBody;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.io.IOException;
import java.util.Optional;

@Component
public class CbrClient
{
    @Autowired
    private OkHttpClient client;

    // TODO | @Value("${cbr.currency.rates.xml.url}")
    private final static String cbrCurrencyRatesXmlUrl = "http://www.cbr.ru/scripts/XML_daily.asp";

    public Optional<String> getCurrencyRatesXML() throws ServiceException {
        Request request = new Request.Builder().url(cbrCurrencyRatesXmlUrl).build();
        try (Response response = client.newCall(request).execute()) {
            final ResponseBody body = response.body();
            return Optional.of(body.string());
        } catch (IOException e) {
            throw new ServiceException("Ошибка получения курсов валют от ЦБ РФ", e);
        }
    }
}