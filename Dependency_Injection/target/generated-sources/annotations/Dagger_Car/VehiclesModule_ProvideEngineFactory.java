package Dagger_Car;

import dagger.internal.DaggerGenerated;
import dagger.internal.Factory;
import dagger.internal.Preconditions;
import dagger.internal.QualifierMetadata;
import dagger.internal.ScopeMetadata;
import javax.annotation.processing.Generated;

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
public final class VehiclesModule_ProvideEngineFactory implements Factory<Engine> {
  private final VehiclesModule module;

  public VehiclesModule_ProvideEngineFactory(VehiclesModule module) {
    this.module = module;
  }

  @Override
  public Engine get() {
    return provideEngine(module);
  }

  public static VehiclesModule_ProvideEngineFactory create(VehiclesModule module) {
    return new VehiclesModule_ProvideEngineFactory(module);
  }

  public static Engine provideEngine(VehiclesModule instance) {
    return Preconditions.checkNotNullFromProvides(instance.provideEngine());
  }
}
