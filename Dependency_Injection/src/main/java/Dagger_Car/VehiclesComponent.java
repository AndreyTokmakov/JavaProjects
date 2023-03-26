package Dagger_Car;

import javax.inject.Singleton;
import dagger.Component;

@Singleton
@Component(modules = VehiclesModule.class)
public interface VehiclesComponent {
    /**
     * Builds a {@link Car}.
     * @return a {@link Car}
     */
    public Car buildCar();
}