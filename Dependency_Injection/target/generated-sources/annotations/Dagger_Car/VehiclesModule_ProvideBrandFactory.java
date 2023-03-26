package Dagger_Car;

import dagger.internal.DaggerGenerated;
import dagger.internal.Factory;
import dagger.internal.Preconditions;
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
    "rawtypes"
})
public final class VehiclesModule_ProvideBrandFactory implements Factory<Brand> {
  private final VehiclesModule module;

  public VehiclesModule_ProvideBrandFactory(VehiclesModule module) {
    this.module = module;
  }

  @Override
  public Brand get() {
    return provideBrand(module);
  }

  public static VehiclesModule_ProvideBrandFactory create(VehiclesModule module) {
    return new VehiclesModule_ProvideBrandFactory(module);
  }

  public static Brand provideBrand(VehiclesModule instance) {
    return Preconditions.checkNotNullFromProvides(instance.provideBrand());
  }
}
