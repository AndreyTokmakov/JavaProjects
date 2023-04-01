package Dagger;

import dagger.internal.DaggerGenerated;
import dagger.internal.Factory;
import dagger.internal.QualifierMetadata;
import dagger.internal.ScopeMetadata;
import javax.annotation.processing.Generated;

@ScopeMetadata("javax.inject.Singleton")
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
public final class CoffeeLogger_Factory implements Factory<CoffeeLogger> {
  @Override
  public CoffeeLogger get() {
    return newInstance();
  }

  public static CoffeeLogger_Factory create() {
    return InstanceHolder.INSTANCE;
  }

  public static CoffeeLogger newInstance() {
    return new CoffeeLogger();
  }

  private static final class InstanceHolder {
    private static final CoffeeLogger_Factory INSTANCE = new CoffeeLogger_Factory();
  }
}
