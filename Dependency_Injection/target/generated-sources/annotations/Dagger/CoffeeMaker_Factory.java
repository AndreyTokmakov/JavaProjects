package Dagger;

import dagger.Lazy;
import dagger.internal.DaggerGenerated;
import dagger.internal.DoubleCheck;
import dagger.internal.Factory;
import dagger.internal.QualifierMetadata;
import dagger.internal.ScopeMetadata;
import javax.annotation.processing.Generated;
import javax.inject.Provider;

@ScopeMetadata
@QualifierMetadata
@DaggerGenerated
@Generated(
    value = "dagger.internal.codegen.ComponentProcessor",
    comments = "https://dagger.dev"
)
@SuppressWarnings({
    "unchecked",
    "rawtypes"
})
public final class CoffeeMaker_Factory implements Factory<CoffeeMaker> {
  private final Provider<CoffeeLogger> loggerProvider;

  private final Provider<Heater> heaterProvider;

  private final Provider<Pump> pumpProvider;

  public CoffeeMaker_Factory(Provider<CoffeeLogger> loggerProvider, Provider<Heater> heaterProvider,
      Provider<Pump> pumpProvider) {
    this.loggerProvider = loggerProvider;
    this.heaterProvider = heaterProvider;
    this.pumpProvider = pumpProvider;
  }

  @Override
  public CoffeeMaker get() {
    return newInstance(loggerProvider.get(), DoubleCheck.lazy(heaterProvider), pumpProvider.get());
  }

  public static CoffeeMaker_Factory create(Provider<CoffeeLogger> loggerProvider,
      Provider<Heater> heaterProvider, Provider<Pump> pumpProvider) {
    return new CoffeeMaker_Factory(loggerProvider, heaterProvider, pumpProvider);
  }

  public static CoffeeMaker newInstance(CoffeeLogger logger, Lazy<Heater> heater, Pump pump) {
    return new CoffeeMaker(logger, heater, pump);
  }
}
