package Dagger_Car;

import javax.inject.Singleton;
import dagger.Module;
import dagger.Provides;

@Module
public class VehiclesModule {
    /**
     * Creates an {@link Engine}.
     * @return an {@link Engine}
     */
    @Provides
    public Engine provideEngine() {
        return new Engine();
    }

    /**
     * Creates a {@link Brand}.
     * @return a {@link Brand}
     */
    @Provides
    @Singleton
    public Brand provideBrand() {
        return new Brand("Baeldung");
    }
}