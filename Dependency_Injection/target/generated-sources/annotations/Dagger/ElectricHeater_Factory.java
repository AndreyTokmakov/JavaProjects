package Dagger;

import dagger.internal.DaggerGenerated;
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
    "rawtypes",
    "KotlinInternal",
    "KotlinInternalInJava"
})
public final class ElectricHeater_Factory implements Factory<ElectricHeater> {
  private final Provider<CoffeeLogger> loggerProvider;

  public ElectricHeater_Factory(Provider<CoffeeLogger> loggerProvider) {
    this.loggerProvider = loggerProvider;
  }

  @Override
  public ElectricHeater get() {
    return newInstance(loggerProvider.get());
  }

  public static ElectricHeater_Factory create(Provider<CoffeeLogger> loggerProvider) {
    return new ElectricHeater_Factory(loggerProvider);
  }

  public static ElectricHeater newInstance(CoffeeLogger logger) {
    return new ElectricHeater(logger);
  }
}
