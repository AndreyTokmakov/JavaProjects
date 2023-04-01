package Dagger_Car;

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
public final class Car_Factory implements Factory<Car> {
  private final Provider<Engine> engineProvider;

  private final Provider<Brand> brandProvider;

  public Car_Factory(Provider<Engine> engineProvider, Provider<Brand> brandProvider) {
    this.engineProvider = engineProvider;
    this.brandProvider = brandProvider;
  }

  @Override
  public Car get() {
    return newInstance(engineProvider.get(), brandProvider.get());
  }

  public static Car_Factory create(Provider<Engine> engineProvider, Provider<Brand> brandProvider) {
    return new Car_Factory(engineProvider, brandProvider);
  }

  public static Car newInstance(Engine engine, Brand brand) {
    return new Car(engine, brand);
  }
}
