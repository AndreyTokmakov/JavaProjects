/****************************************************************************
* Copyright 2020 (C) Andrey Tokmakov
* Java Factory Pattern Factory is used for creating instances for your classes
* Factory, as the name suggests, is a place to create some different products
* which are somehow similar in features yet divided into categories.
*
* @name    : CarFactoryDemo.java
* @author  : Tokmakov Andrey
* @version : 1.0
* @since   : December 01, 2020
****************************************************************************/

package Factory;

enum CarType {
    SMALL, 
    SEDAN, 
    LUXURY
}

abstract class Car {
    private CarType model = null;
	
    public Car(CarType model) {
        this.model = model;
        arrangeParts();
    }
 
    private void arrangeParts() {
        // Do one time processing here
    }
 
    // Do subclass level processing in this method
    protected abstract void construct();

    public CarType getModel() {
        return model;
    }
 
    public void setModel(CarType model) {
        this.model = model;
    }
}

class LuxuryCar extends Car {
    LuxuryCar() {
        super(CarType.LUXURY);
        construct();
    }
 
    @Override
    protected void construct() {
        System.out.println("Building luxury car");
    }
}

class SmallCar extends Car {
    SmallCar() {
        super(CarType.SMALL);
        construct();
    }
 
    @Override
    protected void construct() {
        System.out.println("Building small car");
    }
}

class SedanCar extends Car {
    SedanCar() {
        super(CarType.SEDAN);
        construct();
    }
 
    @Override
    protected void construct() {
        System.out.println("Building sedan car");
    }
}

class CarFactory {
    public static Car buildCar(CarType model) {
        Car car = null;
        switch (model) {
	        case SMALL  -> car = new SmallCar();
	        case SEDAN  -> car = new SedanCar();
	        case LUXURY -> car = new LuxuryCar();
	        default -> {
	        	throw new IllegalArgumentException("Unsupported car model " + model);
	        }
        }
        return car;
    }
}

public class CarFactoryDemo {
	public static void main(String[] args) 
	{
		System.out.println(CarFactory.buildCar(CarType.SMALL));
        System.out.println(CarFactory.buildCar(CarType.SEDAN));
        System.out.println(CarFactory.buildCar(CarType.LUXURY));
	}
}
