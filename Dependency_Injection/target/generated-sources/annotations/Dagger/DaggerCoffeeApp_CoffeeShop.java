package Dagger;

import dagger.internal.DaggerGenerated;
import dagger.internal.DoubleCheck;
import javax.annotation.processing.Generated;
import javax.inject.Provider;

@DaggerGenerated
@Generated(
    value = "dagger.internal.codegen.ComponentProcessor",
    comments = "https://dagger.dev"
)
@SuppressWarnings({
    "unchecked",
    "rawtypes"
})
public final class DaggerCoffeeApp_CoffeeShop {
  private DaggerCoffeeApp_CoffeeShop() {
  }

  public static Builder builder() {
    return new Builder();
  }

  public static CoffeeApp.CoffeeShop create() {
    return new Builder().build();
  }

  public static final class Builder {
    private Builder() {
    }

    public CoffeeApp.CoffeeShop build() {
      return new CoffeeShopImpl();
    }
  }

  private static final class CoffeeShopImpl implements CoffeeApp.CoffeeShop {
    private final CoffeeShopImpl coffeeShopImpl = this;

    private Provider<CoffeeLogger> coffeeLoggerProvider;

    private Provider<ElectricHeater> electricHeaterProvider;

    private Provider<Heater> bindHeaterProvider;

    private CoffeeShopImpl() {

      initialize();

    }

    private Thermosiphon thermosiphon() {
      return new Thermosiphon(coffeeLoggerProvider.get(), bindHeaterProvider.get());
    }

    @SuppressWarnings("unchecked")
    private void initialize() {
      this.coffeeLoggerProvider = DoubleCheck.provider(CoffeeLogger_Factory.create());
      this.electricHeaterProvider = ElectricHeater_Factory.create(coffeeLoggerProvider);
      this.bindHeaterProvider = DoubleCheck.provider((Provider) electricHeaterProvider);
    }

    @Override
    public CoffeeMaker maker() {
      return new CoffeeMaker(coffeeLoggerProvider.get(), DoubleCheck.lazy(bindHeaterProvider), thermosiphon());
    }

    @Override
    public CoffeeLogger logger() {
      return coffeeLoggerProvider.get();
    }
  }
}
