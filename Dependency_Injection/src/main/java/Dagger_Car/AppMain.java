package Dagger_Car;

public class AppMain
{
    public static void main(String[] args) {
        VehiclesComponent component = DaggerVehiclesComponent.create();

        Car carOne = component.buildCar();
        Car carTwo = component.buildCar();

        System.out.println(carOne);
        System.out.println(carTwo);

        System.out.println(carOne.getEngine());
        System.out.println(carTwo.getEngine());
        System.out.println(carOne.getEngine() != carTwo.getEngine());

        System.out.println(carOne.getBrand());
        System.out.println(carTwo.getBrand());
        /** Brand Should be singleton **/
        System.out.println(carOne.getBrand() == carTwo.getBrand());
    }
}
