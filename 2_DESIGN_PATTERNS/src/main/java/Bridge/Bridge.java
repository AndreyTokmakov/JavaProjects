package Bridge;

//abstraction in bridge pattern 
abstract class Vehicle { 
	protected Workshop workShop1; 
	protected Workshop workShop2; 

	protected Vehicle(Workshop workShop1,  Workshop workShop2) { 
		this.workShop1 = workShop1; 
		this.workShop2 = workShop2; 
	} 

	abstract public void manufacture(); 
} 

//Refine abstraction 1 in bridge pattern 
class Car extends Vehicle { 
	
	public Car(Workshop workShop1, Workshop workShop2)  { 
		super(workShop1, workShop2); 
	} 

	@Override
	public void manufacture() { 
		workShop1.work(this); 
		workShop2.work(this); 
	} 
} 

//Refine abstraction 2 in bridge pattern 
class Bike extends Vehicle { 
	
	public Bike(Workshop workShop1,
			    Workshop workShop2) { 
		super(workShop1, workShop2); 
	} 

	@Override
	public void manufacture() {  
		workShop1.work(this); 
		workShop2.work(this); 
	} 
} 


interface Workshop { 
	abstract public void work(Vehicle vehicle); 
} 

class Produce implements Workshop { 
	@Override
	public void work(Vehicle vehicle) { 
		System.out.println(vehicle.getClass().getSimpleName() +  " produced"); 
	} 
} 

class Assemble implements Workshop { 
	@Override
	public void work(Vehicle vehicle) { 
		System.out.println(vehicle.getClass().getSimpleName() +  " assembled."); 
	} 
} 

public class Bridge
{
	public static void main(String[] args) 
	{ 
		Vehicle vehicle1 = new Car(new Produce(), new Assemble()); 
		vehicle1.manufacture(); 
		
		Vehicle vehicle2 = new Bike(new Produce(), new Assemble()); 
		vehicle2.manufacture(); 
	} 
}
