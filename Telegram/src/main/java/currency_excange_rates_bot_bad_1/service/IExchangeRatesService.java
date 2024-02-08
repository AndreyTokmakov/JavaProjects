package currency_excange_rates_bot_bad_1.service;


import currency_excange_rates_bot_bad_1.exception.ServiceException;

public interface IExchangeRatesService {

    String getUSDExchangeRate() throws ServiceException;

    String getEURExchangeRate() throws ServiceException;

    void clearUSDCache();

    void clearEURCache();

}