package Dagger_Car;

import dagger.internal.DaggerGenerated;
import dagger.internal.DoubleCheck;
import dagger.internal.Preconditions;
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
public final class DaggerVehiclesComponent {
  private DaggerVehiclesComponent() {
  }

  public static Builder builder() {
    return new Builder();
  }

  public static VehiclesComponent create() {
    return new Builder().build();
  }

  public static final class Builder {
    private VehiclesModule vehiclesModule;

    private Builder() {
    }

    public Builder vehiclesModule(VehiclesModule vehiclesModule) {
      this.vehiclesModule = Preconditions.checkNotNull(vehiclesModule);
      return this;
    }

    public VehiclesComponent build() {
      if (vehiclesModule == null) {
        this.vehiclesModule = new VehiclesModule();
      }
      return new VehiclesComponentImpl(vehiclesModule);
    }
  }

  private static final class VehiclesComponentImpl implements VehiclesComponent {
    private final VehiclesModule vehiclesModule;

    private final VehiclesComponentImpl vehiclesComponentImpl = this;

    private Provider<Brand> provideBrandProvider;

    private VehiclesComponentImpl(VehiclesModule vehiclesModuleParam) {
      this.vehiclesModule = vehiclesModuleParam;
      initialize(vehiclesModuleParam);

    }

    @SuppressWarnings("unchecked")
    private void initialize(final VehiclesModule vehiclesModuleParam) {
      this.provideBrandProvider = DoubleCheck.provider(VehiclesModule_ProvideBrandFactory.create(vehiclesModuleParam));
    }

    @Override
    public Car buildCar() {
      return new Car(VehiclesModule_ProvideEngineFactory.provideEngine(vehiclesModule), provideBrandProvider.get());
    }
  }
}
